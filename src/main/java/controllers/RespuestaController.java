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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import principal.ArbolBinario;

public class RespuestaController implements Initializable{

    @FXML
    private Button btnBack;

    @FXML
    private ImageView img;

    @FXML
    private Label lbl;

    @FXML
    private Pane pane;
    
    public static ArbolBinario<ArrayList<Object>> arbol;
    public static ArrayList<Object> res=new ArrayList<>();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        inicio();
        lbl.setText("Tu respuesta es: "+arbol.encontrarAnimal(res));
        btnBack.setOnAction(e -> {
            res=new ArrayList<>();
            try {
                App.setRoot("principal");
            } catch (IOException ex) {
                // ex.printStackTrace();
            }
        });
        

    }

    public void inicio() {
        try {
            Image gif = new Image(new FileInputStream(App.pathGif + "bien.gif"));
            img.setImage(gif);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
