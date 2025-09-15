package org.example;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // Ruta al archivo Excel
        String filePath = "data/input.xlsx";

        // Verificar si el archivo existe
        if (!ExcelReader.isFileExists(filePath)) {
            System.out.println("El archivo no existe o no es válido. Verifica la ruta.");
            return;
        }

        try {
            // Leer los datos del archivo Excel
            List<Map<String, String>> contacts = ExcelReader.readExcelFile(filePath);

            // Detectar duplicados en los contactos
            List<Map<String, String>> duplicates = DuplicateDetector.findDuplicates(contacts);

            // Mostrar los duplicados encontrados con formato similar al esperado
            if (!duplicates.isEmpty()) {
                System.out.println("Salida:");
                System.out.println("| ContactIDOrigen | ContactIDCoincidencia | Precision |");
                for (Map<String, String> duplicate : duplicates) {
                    System.out.println("| " + duplicate.get("ContactIDOrigen") + " | " +
                            duplicate.get("ContactIDCoincidencia") + " | " +
                            duplicate.get("Precision") + " |");
                }
            } else {
                System.out.println("No se encontraron duplicados.");
            }
        } catch (IOException e) {
            System.out.println("Error al procesar el archivo: " + e.getMessage());
        }
    }
}
