package org.example;

import java.util.*;

public class DuplicateDetector {

    // Método para comparar dos contactos y asignar una puntuación
    public static double compareContacts(Map<String, String> contact1, Map<String, String> contact2) {
        double score = 0.0;

        // Comparar cada campo
        for (String key : contact1.keySet()) {
            String value1 = contact1.get(key);
            String value2 = contact2.get(key);

            // Puntuación exacta
            if (value1 != null && value2 != null && value1.equalsIgnoreCase(value2)) {
                score += 1.0;
            }
            // Puntuación parcial (similaridad)
            else if (value1 != null && value2 != null && value1.contains(value2)) {
                score += 0.5;
            }
        }

        // Normalizar la puntuación
        return score / contact1.size();
    }

    // Método para encontrar duplicados
    public static List<Map<String, String>> findDuplicates(List<Map<String, String>> contacts) {
        List<Map<String, String>> duplicates = new ArrayList<>();
        Set<Map<String, String>> checkedContacts = new HashSet<>();

        for (int i = 0; i < contacts.size(); i++) {
            for (int j = i + 1; j < contacts.size(); j++) {
                Map<String, String> contact1 = contacts.get(i);
                Map<String, String> contact2 = contacts.get(j);

                double score = compareContacts(contact1, contact2);

                // Si la puntuación es suficientemente alta, se considera duplicado
                if (score > 0.7) {
                    duplicates.add(contact1);  // Agregar el primer contacto
                    duplicates.add(contact2);  // Agregar el segundo contacto
                }
            }
        }

        return duplicates;
    }
}
