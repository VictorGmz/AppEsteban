package es.ideas;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * JavaFX App
 */
public class MultiLenguajeUI extends Application {

    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        MultiLenguajeUI.primaryStage = stage;
        //Creamos un objeto de la clase FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        //Le decimos a nuestro loader la localización que queremos cargar
        loader.setLocation(getClass().getResource("primary.fxml"));
        //Le da a nuestro FXMLLoder la dirección del archivo .properties deseado
        loader.setResources(ResourceBundle.getBundle("es.ideas.i18n/cadenas",
                Locale.getDefault()));
        //Creamos un Parent con la localizacion anterior
        Parent raiz = loader.load();
        //Montamos y mostramos la escena
        Scene scene = new Scene(raiz);
        stage.setScene(scene);
        //Impide que se pueda modificar el tamaño de la ventana
        stage.setResizable(false);
        stage.show();
    }
    //Devuelve la vista 
    public static Stage getPrimaryStage(){
        return primaryStage;
    }
    public static void main(String[] args) {
        launch();
    }

}