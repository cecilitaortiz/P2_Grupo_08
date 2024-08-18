/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

import static controllers.App.pathArchivos;
import static controllers.App.preguntas;
import static controllers.App.respuestas;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import principal.Fichero;

/**
 * FXML Controller class
 *
 * @author Cecy
 */
public class PrincipalController implements Initializable {

    @FXML
    private ImageView img;
    @FXML
    private Label lbl;
    @FXML
    private Button btn;
    @FXML
    private Button btna;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            // TODO
        inicio();
        btn.setOnAction(e -> {
            try {
                App.setRoot("contador");
            } catch (IOException ex) {
            }
        });
        btna.setOnAction(e -> {
            try {
                App.setRoot("aumentar");
            } catch (IOException ex) {
            }
        });
    }

    public void inicio() {
        try {
            Image gif = new Image(new FileInputStream(App.pathGif + "genio.gif"));

            img.setImage(gif);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        String t = "Hola, soy tu \ngenio preferido, y como \nestoy aburrido, voy a \nadivinar cuál es \ntu animal preferido";
        lbl.setText(t);

    }

    public String strTohtml(String text) {
        return "<html>" + text + "<html>";
    }
}
