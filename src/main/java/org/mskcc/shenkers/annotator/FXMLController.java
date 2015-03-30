/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mskcc.shenkers.annotator;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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

/**
 *
 * @author sol
 */
public class FXMLController implements Initializable {

    FeatureAnnotationAuthority featureAuthority = new FeatureAnnotationAuthorityImpl();

    final Property<Annotation> aProperty = new SimpleObjectProperty<>();

    private StringProperty datasourceProperty = new SimpleStringProperty();
    private StringProperty userProperty = new SimpleStringProperty();

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

    public void syncAnnotationControls(Annotation a) {
        status
                .getToggles()
                .stream()
                .forEach(t -> {
                    t.setSelected(t.getUserData() == a.getStatus());
                });

        notes.setText(a.getNotes());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        user.textProperty().bind(getUserProperty());
        datasource.textProperty().bind(getDatasourceProperty());

        true_pos.setUserData(Status.true_pos);
        false_pos.setUserData(Status.false_pos);
        undecided.setUserData(Status.undecided);

        FeatureAnnotation nxt = featureAuthority.getNext();
        aProperty.setValue(nxt.getAnnotation());
        syncAnnotationControls(nxt.getAnnotation());
        desc.setText(nxt.getFeatureDesc());

        next.setOnAction(e -> {
            // persist the current feature
            Annotation a = aProperty.getValue();
            a.setStatus((Status) status.getSelectedToggle().getUserData());
            a.setNotes(notes.getText());
            featureAuthority.update(a);
            // request the next
            FeatureAnnotation fa = featureAuthority.getNext();
            aProperty.setValue(fa.getAnnotation());
            syncAnnotationControls(fa.getAnnotation());
            desc.setText(fa.getFeatureDesc());
        });

        prev.setOnAction(e -> {
            // persist the current feature
            Annotation a = aProperty.getValue();
            a.setStatus((Status) status.getSelectedToggle().getUserData());
            a.setNotes(notes.getText());
            featureAuthority.update(a);
            // request the next
            FeatureAnnotation fa = featureAuthority.getPrev();
            aProperty.setValue(fa.getAnnotation());
            syncAnnotationControls(fa.getAnnotation());
            desc.setText(fa.getFeatureDesc());
        });
    }

    /**
     * @return the datasourceProperty
     */
    public StringProperty getDatasourceProperty() {
        return datasourceProperty;
    }

    /**
     * @return the userProperty
     */
    public StringProperty getUserProperty() {
        return userProperty;
    }

}
