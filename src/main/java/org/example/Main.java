package org.example;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String filePath = "data/input.xlsx";  // Ruta al archivo Excel

        // Verificar si el archivo existe
        if (!ExcelReader.isFileExists(filePath)) {
            System.out.println("El archivo no existe o no es válido. Verifica la ruta.");
            return;
        }

        try {
            // Leer los datos del archivo
            List<Map<String, String>> contacts = ExcelReader.readExcelFile(filePath);

            // Detectar duplicados en los contactos
            List<Map<String, String>> duplicates = DuplicateDetector.findDuplicates(contacts);

            // Mostrar los duplicados encontrados
            if (!duplicates.isEmpty()) {
                System.out.println("Se encontraron duplicados:");
                for (Map<String, String> duplicate : duplicates) {
                    System.out.println(duplicate);
                }
            } else {
                System.out.println("No se encontraron duplicados.");
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}
