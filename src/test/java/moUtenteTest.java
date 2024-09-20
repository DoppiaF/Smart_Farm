

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import java.sql.Timestamp;
import java.time.LocalDate;

import com.unife.project.model.mo.Utente;

//questa classe contiene 2 test: 1 per il costruttore e l'altro per i getter e setter
public class moUtenteTest {
    @Test
    public void testUserCreation() {

        
        Timestamp createTime = new Timestamp(System.currentTimeMillis());
        LocalDate dataNascita = LocalDate.of(1990, 1, 1);

        Utente utente = new Utente(1, "Gandalf", "pw", "gandalf@email.com", createTime, dataNascita, false, false, false, true);
        
        //Person person = new Person("John Doe", 30);
        assertEquals("Gandalf", utente.getUserName());
        assertEquals("pw", utente.getPassword());
        assertEquals(createTime,utente.getCreateTime());
        assertEquals(dataNascita,utente.getDataNascita());
        assertEquals(false, utente.getRuolo_raccolta());
        assertEquals(false, utente.getRuolo_irrigazione());
        assertEquals(false, utente.getRuolo_pastore());
        assertEquals(true, utente.getRuolo_admin());
    }

    @Test
    public void testGettersAndSetters() {
        // Create a Timestamp and LocalDate for testing
        Timestamp createTime = new Timestamp(System.currentTimeMillis());
        LocalDate dataNascita = LocalDate.of(1990, 1, 1);

        // Create an instance of Utente
        Utente utente = new Utente();
        utente.setId(1);
        utente.setUserName("testUser");
        utente.setPassword("testPassword");
        utente.setEmail("test@example.com");
        utente.setCreateTime(createTime);
        utente.setDataNascita(dataNascita);
        utente.setRuolo_raccolta(true);
        utente.setRuolo_irrigazione(false);
        utente.setRuolo_pastore(true);
        utente.setRuolo_admin(false);

        // Test getters
        assertEquals(1, utente.getId());
        assertEquals("testUser", utente.getUserName());
        assertEquals("testPassword", utente.getPassword());
        assertEquals("test@example.com", utente.getEmail());
        assertEquals(createTime, utente.getCreateTime());
        assertEquals(dataNascita, utente.getDataNascita());
        assertTrue(utente.getRuolo_raccolta());
        assertFalse(utente.getRuolo_irrigazione());
        assertTrue(utente.getRuolo_pastore());
        assertFalse(utente.getRuolo_admin());
    }
}
