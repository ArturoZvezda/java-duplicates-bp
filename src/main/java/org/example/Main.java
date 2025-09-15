package org.example;

import java.io.IOException;
import java.util.List;
import java.util.Map;


public class Main {
    public static void main(String[] args) {
        DuplicateFinder finder = new DuplicateFinder();
        String filePath = "data/input.xlsx"; // Cambia la ruta a tu archivo de entrada

        try {
            // Leer los datos del archivo
            List<Map<String, String>> contacts = finder.readExcelFile(filePath);

            // Detectar duplicados
            List<Map<String, String>> duplicates = finder.findDuplicates(contacts); // Cambia el tipo aquí

            // Imprimir los duplicados encontrados
            System.out.println("Posibles duplicados encontrados:");
            for (Map<String, String> duplicate : duplicates) {
                // Imprime los detalles de cada duplicado
                System.out.println("ContactID Origen: " + duplicate.get("ContactID Origen"));
                System.out.println("ContactID Coincidencia: " + duplicate.get("ContactID Coincidencia"));
                System.out.println("Precisión: " + duplicate.get("Precisión"));
                System.out.println("----------------------------");
            }

        } catch (IOException e) {
            System.err.println("Error al leer el archivo Excel: " + e.getMessage());
        }
    }
}
