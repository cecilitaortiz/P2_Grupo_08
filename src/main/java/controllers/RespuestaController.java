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
        //El entero cantidadRespuestas indica la cantidad maxima de animales que se van a mostrar en la lista
        int cantidadRespuestas=3;
        //Para obtener un String con todos los animales separados por ", " reemplazar el parametro cantidadRespuesta con 0
        String animales=stringAnimales(cantidadRespuestas,arbol.encontrarAnimal(res));
        lbl.setText("Tu respuesta es: "+animales);
        System.out.println("Tu respuesta es: "+animales);
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
    
    
    /**
     * Formatea una lista de nombres de animales, separados por espacios, en una cadena
     * que contiene solo la cantidad especificada de animales, separados por comas.
     * 
     * @param cantidad La cantidad de animales a incluir en el resultado.
     * @param animales Una cadena con nombres de animales separados por espacios.
     * @return Una cadena con los nombres de animales separados por comas.
     */
    public static String stringAnimales(int cantidad, String animales) {        
        //Si la cantidad ingresada es -1 se retorna todo el string de animales
        if(cantidad==0) return animales;
        
        // Dividir la cadena en un arreglo de nombres de animales usando el espacio como delimitador
        String[] listaAnimales = animales.split(", ");
        
        // Verificar que la cantidad no sea mayor que el tamaño del arreglo
        if (cantidad > listaAnimales.length) {
            cantidad = listaAnimales.length;
        }
        
        // Usar un StringBuilder para construir la cadena final
        StringBuilder resultado = new StringBuilder();
        
        for (int i = 0; i < cantidad; i++) {
            // Añadir el nombre del animal al resultado
            resultado.append(listaAnimales[i]);
            
            // Añadir una coma si no es el último animal
            if (i < cantidad - 1) {
                resultado.append(",");
            }
        }
        
        return resultado.toString();
    }
}
