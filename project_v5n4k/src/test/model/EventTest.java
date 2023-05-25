package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

// represent the Event test
// this class is taken from the CPSC 210 Alarm System
public class EventTest {
    private Event e;
    private Event f;
    private Date d;
    private Date a;

    //NOTE: these tests might fail if time at which line (2) below is executed
    //is different from time that line (1) is executed.  Lines (1) and (2) must
    //run in same millisecond for this test to make sense and pass.

    @BeforeEach
    public void runBefore() {
        e = new Event("Decision added");   // (1)
        d = Calendar.getInstance().getTime();   // (2)
        f = new Event("Decision removed");
        a = Calendar.getInstance().getTime();
    }

    @Test
    public void testEvent() {
        assertEquals("Decision added", e.getDescription());
    }

    @Test
    public void testToString() {
        assertEquals(d.toString() + "\n" + "Decision added", e.toString());
    }

    @Test
    public void testHashcode() {
        int result = e.hashCode();
        int result2 = f.hashCode();
        int result3 = e.hashCode();

        assertNotEquals(result,result2);
        assertEquals(result, result3);
    }

    @Test
    public void testGetDate() {
        assertEquals(a,d);
    }

    @Test
    public void testEquals() {
        boolean result = e.equals(f);
        boolean result2 = e.equals(a);
        boolean result3 = e.equals(e);

        assertTrue(result3);
        assertFalse(result2);
        assertFalse(result);

    }
}
