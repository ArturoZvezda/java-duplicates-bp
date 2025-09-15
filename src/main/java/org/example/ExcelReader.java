package org.example;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class ExcelReader {

    // Método para leer el archivo Excel y convertirlo a una lista de mapas
    public static List<Map<String, String>> readExcelFile(String filePath) throws IOException {
        List<Map<String, String>> contacts = new ArrayList<>();
        FileInputStream file = new FileInputStream(new File(filePath));

        // Crear instancia de Workbook para leer el archivo Excel
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheetAt(0);  // Tomamos la primera hoja

        // Iterar sobre las filas del archivo
        for (Row row : sheet) {
            Map<String, String> contact = new HashMap<>();
            for (Cell cell : row) {
                String columnName = "Column" + cell.getColumnIndex(); // Asignar un nombre a cada columna
                contact.put(columnName, cell.toString());
            }
            contacts.add(contact);
        }

        workbook.close();
        file.close();
        return contacts;
    }

    // Método para verificar si el archivo existe
    public static boolean isFileExists(String filePath) {
        File file = new File(filePath);
        return file.exists() && file.isFile();
    }
}
