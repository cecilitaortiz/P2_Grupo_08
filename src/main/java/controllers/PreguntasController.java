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

public class PreguntasController implements Initializable {

    @FXML
    private Button btnBack;

    @FXML
    private Button btnNo;

    @FXML
    private Button btnSi;

    @FXML
    private ImageView img;

    @FXML
    private Label lbl;

    @FXML
    private Pane pane;
    
    public static int numero;
    public static int cont;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        inicio();
        btnBack.setOnAction(e -> {
            try {
                App.setRoot("principal");
            } catch (IOException ex) {
//                 ex.printStackTrace();
            }
        });
        cont=0;
        lbl.setText(App.preguntas.get(cont));
        btnSi.setOnAction(e -> {
            if(cont<numero-1){
                cont++;
                lbl.setText(App.preguntas.get(cont));
                RespuestaController.res.add(true);
            }else{
                RespuestaController.res.add(true);
                try {
                    App.setRoot("respuesta");
                } catch (IOException ex) {
                    // ex.printStackTrace();
                }                
            }
        });    
        btnNo.setOnAction(e -> {
            if(cont<numero-1){
                cont++;
                lbl.setText(App.preguntas.get(cont));
                RespuestaController.res.add(false);
            }else{
                RespuestaController.res.add(false);
                try {
                    App.setRoot("respuesta");
                } catch (IOException ex) {
                    // ex.printStackTrace();
                }                
            }
        });    
    }

    public void inicio() {
        try {
            Image gif = new Image(new FileInputStream(App.pathGif + "1.gif"));

            img.setImage(gif);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        
    }

    

}


