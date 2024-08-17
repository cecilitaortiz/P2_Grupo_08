/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package principal;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Dario Anchundia Cobo
 */
public class Fichero {
    
    public static ArrayList<String> leerPreguntas(String filePath) throws IOException {
        ArrayList<String> preguntas = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"));
        
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.startsWith("¿") && line.endsWith("?")) {
                preguntas.add(line);
            } else {
                System.out.println("Línea en formato incorrecto: " + line);
            }
        }
        reader.close();

        return preguntas;
    }
    
    public static HashMap<ArrayList<Boolean>, ArrayList<String>> leerRespuestas(String filePath) throws IOException {
        HashMap<ArrayList<Boolean>, ArrayList<String>> result = new HashMap<>();

        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(" ");
            if (parts.length == controllers.App.preguntas.size()+1) {
                ArrayList<String> arrayS=new ArrayList<>();
                String value = parts[0];
                ArrayList<Boolean> booleans = new ArrayList<>();
                for (int i = 1; i < parts.length; i++) {
                    booleans.add(Boolean.parseBoolean(parts[i]));
                }
                if(result.get(booleans)==null){
                    arrayS.add(value);
                    result.put(booleans,arrayS);                                        
                }else result.get(booleans).add(value);
            } else {
                System.out.println("Línea en formato incorrecto: " + line);
            }
        }
        reader.close();

        return result;
    }
    
}
