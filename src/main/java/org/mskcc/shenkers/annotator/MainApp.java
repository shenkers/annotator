package org.mskcc.shenkers.annotator;

import com.google.inject.Guice;
import com.google.inject.Injector;
import java.util.ResourceBundle;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

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
        
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/DatasetSelecter.fxml"));

        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);

        stage.setTitle("Select dataset");
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

}
