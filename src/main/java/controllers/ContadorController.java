package controllers;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class ContadorController implements Initializable {

    @FXML
    private Button btnBack;

    @FXML
    private Button btnNext;

    @FXML
    private ImageView img;

    @FXML
    private Label lbl;

    @FXML
    private TextField txtf;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        inicio();
        btnBack.setOnAction(e -> {
            try {
                App.setRoot("principal");
            } catch (IOException ex) {
                // ex.printStackTrace();
            }
        });
        btnNext.setOnAction(e -> {
            if (validar(txtf)) {
                lbl.setText("yey");
            }
        });

    }

    public void inicio() {
        try {
            Image gif = new Image(new FileInputStream(App.pathGif + "numeros.gif"));

            img.setImage(gif);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public boolean validar(TextField txtf) {
        if (txtf.getText() == "") {
            lbl.setText("Si no me dices cuántas \npreguntas puedo \nhacer, no podemos \njugar >:(");
            return false;
        } else {
            Integer num = Integer.valueOf(txtf.getText());
            if (num > App.preguntas.size()) {
                lbl.setText("¡Perdón, pero no puedo \ncon tantas preguntas, \nelige menos! :(");
                return false;
            }
        }
        return true;

    }

}
