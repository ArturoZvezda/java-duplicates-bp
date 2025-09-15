import org.example.DuplicateDetector;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

public class DuplicateDetectorTest {

    @Test
    public void testCompareContacts() {
        Map<String, String> contact1 = new HashMap<>();
        contact1.put("name", "John Doe");
        contact1.put("email", "john.doe@example.com");

        Map<String, String> contact2 = new HashMap<>();
        contact2.put("name", "John Doe");
        contact2.put("email", "john.doe@example.com");

        double score = DuplicateDetector.compareContacts(contact1, contact2);

        assertEquals(1.0, score, "La puntuación debe ser 1.0 para coincidencias exactas.");
    }

    @Test
    public void testFindDuplicates() {
        List<Map<String, String>> contacts = new ArrayList<>();

        Map<String, String> contact1 = new HashMap<>();
        contact1.put("name", "John Doe");
        contact1.put("email", "john.doe@example.com");

        Map<String, String> contact2 = new HashMap<>();
        contact2.put("name", "Jane Smith");
        contact2.put("email", "jane.smith@example.com");

        Map<String, String> contact3 = new HashMap<>();
        contact3.put("name", "John Doe");
        contact3.put("email", "john.doe@example.com");

        contacts.add(contact1);
        contacts.add(contact2);
        contacts.add(contact3);

        List<Map<String, String>> duplicates = DuplicateDetector.findDuplicates(contacts);

        assertTrue(duplicates.size() > 0, "Debe encontrar duplicados.");
    }
}
