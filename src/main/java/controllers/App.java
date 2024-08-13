package controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import principal.*;

/**
 * JavaFX App
 */
public class App extends Application {
    private static Scene scene;
    public static String pathImg = "src/main/resources/images/";
    public static String pathGif = "src/main/resources/gifs/";
    public static String pathArchivos = "src/main/resources/archivos/";
    public static ArrayList<ArrayList<Object>> preguntas;
    public static ArrayList<ArrayList<Object>> respuestas;
    

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("principal"));
        stage.setScene(scene);
        stage.show();
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
        crearArbol();
    }
    
    public static ArbolBinario<ArrayList<Object>> crearArbol(){
        try {
            preguntas=Fichero.leerPreguntas(pathArchivos+"preguntas.txt");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try {
            preguntas=Fichero.leerPreguntas(pathArchivos+"respuestas.txt");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        
        ArbolBinario<ArrayList<Object>> arbol = new ArbolBinario<>(preguntas.get(0));
        ArbolBinario<ArrayList<Object>> izq = new ArbolBinario<>(preguntas.get(1));
        ArbolBinario<ArrayList<Object>> izqizq = new ArbolBinario<>(preguntas.get(2));
        ArbolBinario<ArrayList<Object>> izqizqizq = new ArbolBinario<>(respuestas.get(0));
        ArbolBinario<ArrayList<Object>> izqizqder = new ArbolBinario<>();
        ArbolBinario<ArrayList<Object>> izqder = new ArbolBinario<>(preguntas.get(2));
        ArbolBinario<ArrayList<Object>> izqderizq = new ArbolBinario<>(respuestas.get(2));
        ArbolBinario<ArrayList<Object>> izqderder = new ArbolBinario<>();
        ArbolBinario<ArrayList<Object>> der = new ArbolBinario<>(preguntas.get(1));
        ArbolBinario<ArrayList<Object>> derizq = new ArbolBinario<>(preguntas.get(2));
        ArbolBinario<ArrayList<Object>> derizqizq = new ArbolBinario<>();
        ArbolBinario<ArrayList<Object>> derizqder = new ArbolBinario<>(respuestas.get(1));
        ArbolBinario<ArrayList<Object>> derder = new ArbolBinario<>(preguntas.get(2));
        ArbolBinario<ArrayList<Object>> derderizq = new ArbolBinario<>();
        ArbolBinario<ArrayList<Object>> derderder = new ArbolBinario<>(respuestas.get(3));
        
        
        arbol.addLeft(izq);
        arbol.addRight(der);
        izq.addLeft(izqizq);
        izq.addRight(izqder);
        izqizq.addLeft(izqizqizq);
        izqizq.addRight(izqizqder);
        izqder.addLeft(izqderizq);
        izqder.addRight(izqderder);
        der.addLeft(derizq);
        der.addRight(derder);
        derizq.addLeft(derizqizq);
        derizq.addRight(derizqder);
        derder.addLeft(derderizq);
        derder.addRight(derderder);
        
        
        
        return arbol;
    }

}
