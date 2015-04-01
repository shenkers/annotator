package org.mskcc.shenkers.annotator;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.name.Names;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.beans.property.Property;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class MainApp extends Application {

    Injector inj;
//    @Override
//    public void start(Stage stage) throws Exception {
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Scene.fxml"));
//        
//        Parent root = fxmlLoader.load();
//        FXMLController controller = fxmlLoader.<FXMLController>getController();
//        
//        controller.getUserProperty().setValue("user-sol");
//        controller.getDatasourceProperty().setValue("dataset-1");
//        
//        Scene scene = new Scene(root);
//        scene.getStylesheets().add("/styles/Styles.css");
//        
//        stage.setTitle("Annotator");
//        stage.setScene(scene);
//        stage.show();
//    }
    /*
     Injector inj = Guice.createInjector(new TrackConfiguration(), new AlignmentConfiguration(), new CoordinateChangeModule(), new ServerModule());
     FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Scene.fxml"));
     loader.setControllerFactory((Class<?> type) -> {
     return inj.getInstance(type);
     });

     */

    @Override
    public void start(Stage stage) throws Exception {
        inj = Guice.createInjector(
                new PersistenceModule(),
                new FXModule(),
                new PropertyModule());

        FXMLLoader fxmlLoader = inj.getInstance(FXMLLoader.class);
        fxmlLoader.setLocation(getClass().getResource("/fxml/UriConfigurator.fxml"));

        fxmlLoader.setControllerFactory((Class<?> type) -> {
            return inj.getInstance(type);
        });

        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);

        stage.setTitle("Connect to annotation server");
        stage.setScene(scene);
        stage.show();

    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void stop() throws Exception {
        super.stop(); //To change body of generated methods, choose Tools | Templates.

        //close the database connection when the application exits
        System.out.println("closing entity manager...");
        PersistenceProperties emf = inj.getInstance(PersistenceProperties.class);
        System.out.println("got instance: " + emf);
        Property<EntityManagerFactory> emfProperty = emf.getEmfProperty();
        Optional.ofNullable(emfProperty.getValue()).ifPresent(f -> f.close());
    }

}
