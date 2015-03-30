/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mskcc.shenkers.annotator;

import com.google.inject.Inject;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.stream.Collectors;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author sol
 */
public class DatasetSelecterController implements Initializable {

    Property<Optional<String>> user = new SimpleObjectProperty(Optional.empty());
    Property<Optional<String>> source = new SimpleObjectProperty(Optional.empty());
    
    @Inject
    PersitenceService persistenceService;

    @FXML
    Button addUser, selectSource, selectUser, annotate;

    @FXML
    Label selectedUser, selectedSource;

    @FXML
    ListView<String> sources, users;

    @FXML
    TextField newUser;

    BooleanBinding canAnnotate;

    @FXML
    public void userAdded(ActionEvent e) {
        Optional.ofNullable(newUser.getText()).ifPresent((userName) -> {
            if (!users.getItems().contains(userName)) {
                // update the UI
                user.setValue(Optional.of(userName));
                selectedUser.setText(userName);
                users.getItems().add(userName);
            }
        }
        );
    }

    @FXML
    public void userSelected(ActionEvent e) {
        Optional.ofNullable(users.getSelectionModel().getSelectedItem())
                .ifPresent((userName) -> {
                    user.setValue(Optional.of(userName));
                    selectedUser.setText(userName);
                });
    }

    @FXML
    public void sourceSelected(ActionEvent e) {
        Optional.ofNullable(sources.getSelectionModel().getSelectedItem())
                .ifPresent((sourceName) -> {
                    source.setValue(Optional.of(sourceName));
                    selectedSource.setText(sourceName);
                });
    }

    @FXML
    public void startAnnotation(ActionEvent e) throws IOException {
        System.out.println("starting annotation");

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Scene.fxml"));

        Parent root = fxmlLoader.load();
        FXMLController controller = fxmlLoader.<FXMLController>getController();

        controller.getUserProperty().setValue(user.getValue().get());
        controller.getDatasourceProperty().setValue(source.getValue().get());

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");

        Stage stage = new Stage();
        stage.setTitle("Annotator");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO load datasets and users from database

        canAnnotate = Bindings.createBooleanBinding(() -> {
            return !(user.getValue().isPresent() && source.getValue().isPresent());
        }, user, source);
      
        annotate.disableProperty().bind(canAnnotate);
        
        // read annotations from the database
        List<Annotation> annotations = persistenceService.queryAnnotations();
        
        Set<String> userSet = annotations
                .stream()
                .map(a -> a.getId().getUsername())
                .collect(Collectors.toSet());
        
        Set<String> sourceSet = annotations
                .stream()
                .map(a -> a.getId().getUsername())
                .collect(Collectors.toSet());
        
        // set the gui based on these
        this.users.getItems().setAll(userSet);
        this.sources.getItems().setAll(sourceSet);
    }

}
