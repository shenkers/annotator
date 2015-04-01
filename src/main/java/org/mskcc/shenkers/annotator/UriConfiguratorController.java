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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
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
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.apache.commons.validator.routines.UrlValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * FXML Controller class
 *
 * @author sol
 */
public class UriConfiguratorController implements Initializable {

    Logger logger = LoggerFactory.getLogger(UriConfiguratorController.class);

    Property<String> databaseUriProperty, browserUriProperty;

    PersistenceProperties persistenceProperties;

    FXMLLoader rootLoader;

    @FXML
    Button connect;

    @FXML
    TextField databaseUri, browserUri;

    BooleanProperty connected = new SimpleBooleanProperty(false);
    BooleanBinding canConnect;

    @FXML
    public void connect(ActionEvent e) throws IOException {
        connected.setValue(true);
        
        System.out.println("connecting to database");
        {
            Map<String, String> props = new HashMap<>();
            String databaseName = "loci";
            String databaseUrl = String.format("jdbc:derby://%s/%s", databaseUriProperty.getValue().replaceFirst("^https?://", ""), databaseName);
            props.put("javax.persistence.jdbc.url", databaseUrl);
            System.out.println("database properties: " + props);
            EntityManagerFactory emf = Persistence.createEntityManagerFactory(databaseName, props);
            EntityManager em = emf.createEntityManager();
            persistenceProperties.getEmfProperty().setValue(emf);
            persistenceProperties.getEmProperty().setValue(em);
        }

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/DatasetSelecter.fxml"));
        fxmlLoader.setControllerFactory(rootLoader.getControllerFactory());

        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);

        Stage stage = new Stage();
        stage.setTitle("Select Dataset");
        stage.setScene(scene);
        stage.show();
        
        ((Stage)connect.getScene().getWindow()).close();
    }

    @Inject
    public void setLoader(FXMLLoader loader) {
        this.rootLoader = loader;
    }

    @Inject
    public void setSharedProperties(SharedProperties properties) {
        this.browserUriProperty = properties.getBrowserUriProperty();
        this.databaseUriProperty = properties.getDatabaseUriProperty();
    }
    
    @Inject
    public void setSharedProperties(PersistenceProperties properties) {
        this.persistenceProperties = properties;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO load datasets and users from database

        databaseUriProperty.bind(databaseUri.textProperty());
        browserUriProperty.bind(browserUri.textProperty());

        canConnect = Bindings.createBooleanBinding(() -> {
            UrlValidator uri = new UrlValidator(UrlValidator.ALLOW_LOCAL_URLS);
            logger.info("database uri " + databaseUriProperty.getValue() + " valid ? " + uri.isValid(databaseUriProperty.getValue()));
            logger.info("browser uri " + browserUriProperty.getValue() + " valid ? " + uri.isValid(browserUriProperty.getValue()));
            return ! (!connected.getValue() 
                    && uri.isValid(databaseUriProperty.getValue())
                    && uri.isValid(browserUriProperty.getValue()));
        }, databaseUriProperty, browserUriProperty, connected);

        connect.disableProperty().bind(canConnect);
    }

}
