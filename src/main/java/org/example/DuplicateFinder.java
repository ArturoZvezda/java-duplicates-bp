package org.example;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class DuplicateFinder {

    // Leer archivo Excel
    public List<Map<String, String>> readExcelFile(String filePath) throws IOException {
        List<Map<String, String>> contacts = new ArrayList<>();
        FileInputStream file = new FileInputStream(new File(filePath));
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheetAt(0);  // Leer la primera hoja

        // Obtener las cabeceras (asumiendo que están en la primera fila)
        Row headerRow = sheet.getRow(0);
        Map<Integer, String> headers = new HashMap<>();
        for (Cell cell : headerRow) {
            headers.put(cell.getColumnIndex(), cell.getStringCellValue());
        }

        // Leer los datos de contacto
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            Map<String, String> contact = new HashMap<>();
            for (Cell cell : row) {
                String header = headers.get(cell.getColumnIndex());
                String value = cell.toString();
                contact.put(header, value);
            }
            contacts.add(contact);
        }
        workbook.close();
        return contacts;
    }

    // Comparar contactos para encontrar posibles duplicados
    public List<Map<String, String>> findDuplicates(List<Map<String, String>> contacts) {
        List<Map<String, String>> duplicates = new ArrayList<>();
        for (int i = 0; i < contacts.size(); i++) {
            Map<String, String> contact1 = contacts.get(i);
            for (int j = i + 1; j < contacts.size(); j++) {
                Map<String, String> contact2 = contacts.get(j);

                // Comparar nombre, apellido y correo, con verificaciones de null
                int score = 0;
                if (contact1.get("Nombre") != null && contact2.get("Nombre") != null && contact1.get("Nombre").equals(contact2.get("Nombre"))) score++;
                if (contact1.get("Apellido") != null && contact2.get("Apellido") != null && contact1.get("Apellido").equals(contact2.get("Apellido"))) score++;
                if (contact1.get("Dirección de correo electrónico") != null && contact2.get("Dirección de correo electrónico") != null && contact1.get("Dirección de correo electrónico").equals(contact2.get("Dirección de correo electrónico"))) score++;

                // Si la puntuación es 2 o más, consideramos que son duplicados
                if (score >= 2) {
                    Map<String, String> duplicate = new HashMap<>();
                    duplicate.put("ContactID Origen", contact1.get("Contact ID"));
                    duplicate.put("ContactID Coincidencia", contact2.get("Contact ID"));
                    duplicate.put("Precisión", score == 3 ? "Alta" : "Baja");
                    duplicates.add(duplicate);
                }
            }
        }
        return duplicates;
    }

}
