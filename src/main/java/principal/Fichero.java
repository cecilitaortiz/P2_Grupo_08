/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package principal;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Dario Anchundia Cobo
 */
public class Fichero {
    public static ArrayList<ArrayList<Object>> leerPreguntas(String filePath) throws IOException {
        ArrayList<ArrayList<Object>> result = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(" ");
            if (parts.length == 2) {
                ArrayList<Object> entry = new ArrayList<>();
                entry.add(parts[0]);
                entry.add(Boolean.parseBoolean(parts[1]));
                result.add(entry);
            } else {
                System.out.println("Línea en formato incorrecto: " + line);
            }
        }
        reader.close();

        return result;
    }
    
    public static ArrayList<ArrayList<Object>> leerRespuestas(String filePath) throws IOException {
        ArrayList<ArrayList<Object>> result = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(" ");
            if (parts.length == 4) {
                ArrayList<Object> entry = new ArrayList<>();
                entry.add(parts[0]); // Añadir el String

                ArrayList<Boolean> booleans = new ArrayList<>();
                for (int i = 1; i < parts.length; i++) {
                    booleans.add(Boolean.parseBoolean(parts[i])); // Añadir los booleanos
                }
                entry.add(booleans); // Añadir la lista de booleanos al segundo índice

                result.add(entry);
            } else {
                System.out.println("Línea en formato incorrecto: " + line);
            }
        }
        reader.close();

        return result;
    }
    
}
