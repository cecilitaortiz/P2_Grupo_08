package controllers;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
                ex.printStackTrace();
            }
        });
        btnNext.setOnAction(e -> {
            if (validar(txtf)) {
                PreguntasController.numero = Integer.parseInt(txtf.getText());
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
            Image gif = new Image(new FileInputStream(App.pathGif + "numeros.gif"));

            img.setImage(gif);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public boolean validar(TextField txtf) {
        if ("".equals(txtf.getText())) {
            lbl.setText("Si no me dices cuántas \npreguntas puedo \nhacer, no podemos \njugar >:(");
            return false;
        } else {
            try {
                int numero = Integer.parseInt(txtf.getText());
                if (numero == 0) {
                    throw new NumberFormatException();
                }
                if (numero >= 1 && numero <= App.preguntas.size()) {
                    return true;
                } else {
                    lbl.setText("¡Perdón, pero no puedo \ncon tantas preguntas, \nelige menos! :(");
                    return false;
                }

            } catch (NumberFormatException e) {
                lbl.setText("Por favor ingrese\nun numero valido\nde preguntas");
                return false;
            }

        }

    }

}
