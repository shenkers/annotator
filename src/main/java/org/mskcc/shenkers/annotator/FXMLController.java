/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mskcc.shenkers.annotator;

import com.google.inject.Inject;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

/**
 *
 * @author sol
 */
public class FXMLController implements Initializable {

    PersistenceService persistenceService;
    
    BrowserApi browserApi = new BrowserApi();

    Property<Optional<String>> userProperty;
    Property<Optional<String>> sourceProperty;
    
    Property<String> browserUriProperty;

    ObservableList<Locus> loci = FXCollections.observableArrayList();

    final Property<Locus> lProperty = new SimpleObjectProperty<>();
    final Property<Annotation> aProperty = new SimpleObjectProperty<>();

    private StringBinding sourceBinding;
    private StringBinding userBinding;

    @FXML
    Label progress;

    @FXML
    TextArea notes;

    @FXML
    RadioButton undecided, true_pos, false_pos;

    @FXML
    Button next, prev;

    @FXML
    ToggleGroup status;

    @FXML
    TextField datasource, user, desc;
//    @FXML
//    private Label label;
//    
//    @FXML
//    private void handleButtonAction(ActionEvent event) {
//        System.out.println("You clicked me!");
//        label.setText("Hello World!");
//    }

    @Inject
    public void setSharedState(SharedProperties properties) {
        userProperty = properties.getUserProperty();
        sourceProperty = properties.getSourceProperty();
        browserUriProperty = properties.getBrowserUriProperty();
    }

    @Inject
    public void setAnnotationAuthority(PersistenceService ps) {
        this.persistenceService = ps;
    }

    public void syncAnnotationControls(Annotation a) {
        status
                .getToggles()
                .stream()
                .forEach(t -> {
                    t.setSelected(t.getUserData() == a.getStatus());
                });

        notes.setText(a.getNotes());
    }

    StringBinding progressBinding;
    IntegerProperty index = new SimpleIntegerProperty(-1);

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        progressBinding = Bindings.createStringBinding(() -> {
            int decided = loci.stream()
                    .map(l -> l.getAnnotations()
                            .stream()
                            .filter(a
                                    -> a.getUsername().equals(userBinding.get()))
                            .filter(a
                                    -> a.getStatus() == Status.true_pos
                                    || a.getStatus() == Status.false_pos
                            )
                            .mapToInt(a -> 1)
                            .sum())
                    .mapToInt(i -> i.intValue())
                    .sum();
            return String.format("%d / %d (%d decided)", index.get() + 1, loci.size(), decided);
        }, loci, index, userProperty);
        progress.textProperty().bind(progressBinding);

        userBinding = Bindings.createStringBinding(
                () -> String.format("%s",
                        userProperty.getValue()
                        .orElse("ERROR")), userProperty);
        sourceBinding = Bindings.createStringBinding(
                () -> String.format("%s",
                        sourceProperty.getValue()
                        .orElse("ERROR")), sourceProperty);

        user.textProperty().bind(userBinding);
        datasource.textProperty().bind(sourceBinding);

        true_pos.setUserData(Status.true_pos);
        false_pos.setUserData(Status.false_pos);
        undecided.setUserData(Status.undecided);

        next.setOnAction(e -> {
            // persist the current feature
            persistCurrentState();
            // request the next
            Locus nxt = getNext();
            updateCurrentState(nxt);
            updateBrowser(nxt);
        });

        prev.setOnAction(e -> {
            // persist the current feature
            persistCurrentState();
            // request the previous
            Locus nxt = getPrev();
            updateCurrentState(nxt);
            updateBrowser(nxt);
        });
       
        loci.addAll(persistenceService.querySourceLoci(sourceBinding.getValue()));

        initializeGui();
    }

    public Locus getNext() {
        index.setValue((index.get() + 1) % loci.size());
        return loci.get(index.getValue());
    }

    public Locus getPrev() {
        index.setValue((index.get() + (loci.size() - 1)) % loci.size());
        return loci.get(index.getValue());
    }

    public void initializeGui() {
        Locus nxt = getNext();
        updateCurrentState(nxt);
        updateBrowser(nxt);
    }

    public void persistCurrentState() {
        Annotation a = aProperty.getValue();
        a.setStatus((Status) status.getSelectedToggle().getUserData());
        a.setNotes(notes.getText());
        Locus l = lProperty.getValue();
        persistenceService.persist(l);
    }

    public void updateBrowser(Locus nxt) {
        String uri = browserUriProperty.getValue();
        browserApi.setCoordinates(uri, nxt.getGRange().getChr(), nxt.getGRange().getS(), nxt.getGRange().getE());
        browserApi.addIntervalNamedTrack(uri, sourceBinding.get(), nxt.getGRange().getChr(), nxt.getGRange().getS(), nxt.getGRange().getE());
    }

    public void updateCurrentState(Locus nxt) {

        lProperty.setValue(nxt);
        Optional<Annotation> existingAnnotation = nxt.getAnnotations().stream().filter(a -> a.getUsername().equals(userBinding.get())).findAny();
        if (existingAnnotation.isPresent()) {
            System.out.println("found an annotation for user: " + userBinding.get());
            Annotation a = existingAnnotation.get();
            aProperty.setValue(a);
            syncAnnotationControls(a);
        } else {
            Annotation a = new Annotation(userBinding.get(), Status.undecided, "");
            aProperty.setValue(a);
            nxt.getAnnotations().add(a);
            syncAnnotationControls(a);
            System.out.println("didn't find an annotation for user: " + userBinding.get() + ", created new annotation");
        }
        desc.setText(nxt.getGRange().toString());
    }
}
