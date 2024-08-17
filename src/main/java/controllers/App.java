package controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import principal.*;

/**
 * JavaFX App
 */
public class App extends Application {
    private static Scene scene;
    public static String pathImg = "src/main/resources/images/";
    public static String pathGif = "src/main/resources/gifs/";
    public static String pathArchivos = "src/main/resources/archivos/";
    public static ArrayList<String> preguntas;
    public static HashMap<ArrayList<Boolean>, ArrayList<String>> respuestas;
    
    

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("principal"));
        stage.setScene(scene);
        try {
            preguntas=Fichero.leerPreguntas(pathArchivos+"preguntas.txt");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try {
            respuestas=Fichero.leerRespuestas(pathArchivos+"respuestas.txt");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        stage.show();
//        ArbolBinario<ArrayList<Object>> arbol=principal.ArbolBinario.crearArbolBinario(preguntas,respuestas);
//        for(ArrayList<Object> a:arbol.recorridoPreOrden()){
//            System.out.println(a);
//        }
    
        
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
        
    }

}
