package controllers;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class AumentarController implements Initializable{

    @FXML
    private Button btnBack;

    @FXML
    private Button btnNext;

    @FXML
    private ImageView img;


    @FXML
    private TextField txtf;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        inicio();
        btnBack.setOnAction(e -> {
            try {
                App.setRoot("principal");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        btnNext.setOnAction(e -> {
            if (validar(txtf)) {
                PreguntasController.numero = 20;
                ArrayList<String> preguntasarbol = new ArrayList<>();
                for (int i = 0; i < Integer.parseInt(txtf.getText()); i++) {
                    preguntasarbol.add(App.preguntas.get(i));
                }
                RespuestaController.arbol = principal.ArbolBinario.arboljuego(preguntasarbol, App.respuestas);
                try {
                    App.setRoot("preguntas");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
        });

    }

    public void inicio() {
        try {
            Image gif = new Image(new FileInputStream(App.pathGif + "pregunta.gif"));

            img.setImage(gif);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
    public boolean validar(TextField txtf) {
        if ("".equals(txtf.getText())) {
            return false;
        } else {
            return true;

        }

    }
}
