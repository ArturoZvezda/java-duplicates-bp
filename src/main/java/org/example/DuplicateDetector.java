package org.example;

import java.util.*;

public class DuplicateDetector {

    // Método para comparar dos contactos y asignar una puntuación con peso a cada campo
    public static double compareContacts(Map<String, String> contact1, Map<String, String> contact2) {
        double score = 0.0;
        int totalWeight = 0;  // Para normalizar la puntuación

        // Definir pesos para cada campo
        Map<String, Integer> fieldWeights = new HashMap<>();
        fieldWeights.put("email", 2);  // Mayor peso para el email
        fieldWeights.put("name", 1);  // Peso estándar para el nombre
        fieldWeights.put("name1", 1); // Peso estándar para el apellido
        fieldWeights.put("address", 1); // Peso estándar para la dirección
        fieldWeights.put("postalZip", 1); // Peso estándar para el código postal

        // Comparar cada campo
        for (String key : contact1.keySet()) {
            String value1 = contact1.get(key);
            String value2 = contact2.get(key);

            // Obtenemos el peso del campo
            int weight = fieldWeights.getOrDefault(key, 1);  // Si no tiene peso definido, se asume peso 1
            totalWeight += weight;

            // Puntuación exacta
            if (value1 != null && value2 != null && value1.equalsIgnoreCase(value2)) {
                score += weight;
            }
            // Puntuación parcial (similaridad)
            else if (value1 != null && value2 != null && value1.contains(value2)) {
                score += 0.5 * weight;
            }
        }

        // Normalizar la puntuación según el peso total
        return score / totalWeight;
    }

    // Método para encontrar duplicados
    public static List<Map<String, String>> findDuplicates(List<Map<String, String>> contacts) {
        List<Map<String, String>> duplicates = new ArrayList<>();

        for (int i = 0; i < contacts.size(); i++) {
            for (int j = i + 1; j < contacts.size(); j++) {
                Map<String, String> contact1 = contacts.get(i);
                Map<String, String> contact2 = contacts.get(j);

                double score = compareContacts(contact1, contact2);

                // Si la puntuación es suficientemente alta, se considera duplicado
                if (score > 0.8) {
                    Map<String, String> duplicate = new HashMap<>();
                    duplicate.put("ContactIDOrigen", String.valueOf(contact1.get("contactID")));
                    duplicate.put("ContactIDCoincidencia", String.valueOf(contact2.get("contactID")));
                    duplicate.put("Precision", score > 0.9 ? "Alta" : "Baja");
                    duplicates.add(duplicate);
                }
            }
        }

        return duplicates;
    }
}
