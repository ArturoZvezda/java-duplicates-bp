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

        // Obtener la primera fila (encabezados de columna)
        Row headerRow = sheet.getRow(0);
        List<String> columnNames = new ArrayList<>();

        // Recorrer la primera fila para obtener los nombres de las columnas
        for (Cell cell : headerRow) {
            columnNames.add(cell.toString());
        }

        // Iterar sobre las filas del archivo a partir de la segunda fila (saltando los encabezados)
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            Map<String, String> contact = new HashMap<>();

            // Asociar los valores de las celdas con los nombres de las columnas
            for (int j = 0; j < row.getPhysicalNumberOfCells(); j++) {
                String columnName = columnNames.get(j);
                String cellValue = row.getCell(j).toString();
                contact.put(columnName, cellValue);
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
