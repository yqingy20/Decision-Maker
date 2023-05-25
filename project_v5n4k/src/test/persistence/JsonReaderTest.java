package persistence;

import model.Decision;
import model.DecisionList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            DecisionList dl = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyDecisionList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyDecisionList.json");
        try {
            DecisionList dl = reader.read();
            assertEquals(0, dl.decisionSize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralDecisionList() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralDecisionList.json");
        try {
            DecisionList dl = reader.read();
            assertEquals(2,dl.decisionSize());
            assertEquals("study chemistry", dl.getDecision(1).getDecisionName());
            assertEquals("ikb",dl.getDecision(1).getDecisionAddress());
            assertEquals("sleeping", dl.getDecision(2).getDecisionName());
            assertEquals("home",dl.getDecision(2).getDecisionAddress());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
