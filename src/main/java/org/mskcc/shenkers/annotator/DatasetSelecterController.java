/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mskcc.shenkers.annotator;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
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
import org.apache.commons.validator.routines.UrlValidator;

/**
 * FXML Controller class
 *
 * @author sol
 */
public class DatasetSelecterController implements Initializable {
    
    Property<Optional<String>> user, source;
    
     
    PersistenceService persistenceService;
    
    FXMLLoader rootLoader;
    
    @FXML
    Button addUser, annotate;
    
    @FXML
    Label selectSource, selectUser;
    
    @FXML
    TextField selectedUser, selectedSource;
    
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
                    userSelected(userName);
                });
    }
    
    public void userSelected(String userName) {
        user.setValue(Optional.of(userName));
        selectedUser.setText(userName);
    }
    
    @FXML
    public void sourceSelected(ActionEvent e) {
        Optional.ofNullable(sources.getSelectionModel().getSelectedItem())
                .ifPresent((sourceName) -> {
                    source.setValue(Optional.of(sourceName));
                    selectedSource.setText(sourceName);
                });
    }
    
    public void sourceSelected(String sourceName) {
        source.setValue(Optional.of(sourceName));
        selectedSource.setText(sourceName);
    }
    
    @Inject
    public void setLoader(FXMLLoader loader) {
        this.rootLoader = loader;
    }
    
    @Inject
    public void setSharedProperties(SharedProperties properties){
        this.user = properties.getUserProperty();
        this.source = properties.getSourceProperty();
    }
    @Inject
    public void setPersistenceService(PersistenceService persistenceService){
        this.persistenceService = persistenceService;
    }
    
    @FXML
    public void startAnnotation(ActionEvent e) throws IOException {
        System.out.println("starting annotation");
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Scene.fxml"));
        fxmlLoader.setControllerFactory(rootLoader.getControllerFactory());
        
        Parent root = fxmlLoader.load();
        FXMLController controller = fxmlLoader.<FXMLController>getController();
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        
        Stage stage = new Stage();
        stage.setTitle("Annotator");
        stage.setScene(scene);
        // save the current state when the editor is closed
        stage.setOnCloseRequest(evt -> {
            controller.persistCurrentState();
        });
        stage.show();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO load datasets and users from database

        canAnnotate = Bindings.createBooleanBinding(() -> {
            UrlValidator uri = UrlValidator.getInstance();
            
            return !(user.getValue().isPresent() 
                    && source.getValue().isPresent()
                    );
        }, user, source);
        
        annotate.disableProperty().bind(canAnnotate);
        
        users.getSelectionModel().selectedItemProperty().addListener(
                (ob, o, userName) -> {
                    userSelected(userName);
                });
        
        sources.getSelectionModel().selectedItemProperty().addListener(
                (ob, o, sourceName) -> {
                    sourceSelected(sourceName);
                });
        // read annotations from the database
        List<Locus> annotations = persistenceService.queryLoci();
        
        Set<String> userSet = annotations
                .stream()
                .flatMap(l
                        -> StreamSupport.stream(
                                Spliterators.spliterator(
                                        l.getAnnotations(), 0), false))
                .map(a -> a.getUsername())
                .collect(Collectors.toSet());
        
        Set<String> sourceSet = annotations
                .stream()
                .map(a -> a.getSource())
                .collect(Collectors.toSet());

        // set the gui based on these
        this.users.getItems().setAll(userSet);
        this.sources.getItems().setAll(sourceSet);
    }
    
}
