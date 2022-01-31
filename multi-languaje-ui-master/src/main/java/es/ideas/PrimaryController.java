package es.ideas;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

public class PrimaryController implements Initializable {

    //Nodes del fxml que vamos a usar
    @FXML
    private ChoiceBox<String> cbSemana;
    @FXML
    private ToggleButton tgbEs;
    @FXML
    private ToggleButton tgbUSA;
    @FXML
    private ToggleButton tgbFr;
    @FXML
    private ToggleButton tgbIt;
    @FXML
    private ToggleButton tgbEn;

    //Método que selecciona el archivo de idioma debemos de usar
    private FXMLLoader getFXMLLoader() {
        FXMLLoader loader = new FXMLLoader();
        //Le da a nuestro FXMLLoder la dirección del archivo .properties
        loader.setResources(ResourceBundle.getBundle("es.ideas.i18n/cadenas",
                Locale.getDefault()));
        //Le cambia la location al fxml. Para que vuelva a cargarse en el idioma deseado
        loader.setLocation(getClass().getResource("primary.fxml"));
        return loader;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //Inicialización del ComboBox con los días de la semana
        //Cogemos de nuestro ResourceBundle la key de tipo String
        String dias_semana[] = {rb.getString("lunes"),
            rb.getString("martes"),
            rb.getString("miercoles"),
            rb.getString("jueves"),
            rb.getString("viernes"),
            rb.getString("sabado"),
            rb.getString("domingo")};
        //Añadimos el array anterior a nuestro ComboBox
        cbSemana.setItems(FXCollections.observableArrayList(dias_semana));
        //Muestra el primer dia de la semana
        cbSemana.getSelectionModel().select(0);
        //Creamos un ToggleGroup para agrupar nuestros ToogleButtons en él
        ToggleGroup tg = new ToggleGroup();
        tg.getToggles().addAll(tgbEs, tgbUSA, tgbFr, tgbIt, tgbEn);
        //Listener ligado a nuestro ToogleGroup
        tg.selectedToggleProperty().addListener((obs, oldValue, newValue) -> {
            //Nos aseguramos de que se ha seleccionado algo, para evitar errores
            if (newValue != null) {
                //Asignamos el ToogleButton seleccionado a un nuevo Toogle
                ToggleButton tb = (ToggleButton) newValue.getToggleGroup()
                        .getSelectedToggle();
                //Comparamos el texto del ToggleButton        
                switch (tb.getText()) {
                    //En cada caso asigna la localización. Para saber que archivo properties usar
                    case "Inglaterra":
                        Locale.setDefault(Locale.ENGLISH);
                        System.out.println("Has seleccionado idioma INGLÉS");
                        break;
                    case "Francia":
                        Locale.setDefault(Locale.FRENCH);
                        System.out.println("Has seleccionado idioma FRANCÉS");
                        break;
                    case "Italia":
                        Locale.setDefault(Locale.ITALIAN);
                        System.out.println("Has seleccionado idioma ITALIANO");
                        break;
                    case "Español":
                        Locale.setDefault(new Locale("es"));
                        System.out.println("Has seleccionado idioma ESPAÑOL");
                        break;
                    case "EEUU":
                        Locale.setDefault(Locale.US);
                        System.out.println("Has seleccionado idioma INGLÉS AMERICANO");
                        break;
                }
                try {
                    //Creamos un nuevo Parent con la nueva Localización
                    Parent pane = getFXMLLoader().load();
                    //Cargamos este parent en nuestra vista
                    MultiLenguajeUI.getPrimaryStage().getScene().setRoot(pane);
                } catch (IOException ieo) {
                }
                //Mostramos nuestra vista
                MultiLenguajeUI.getPrimaryStage().show();
            }

        });

    }
}
