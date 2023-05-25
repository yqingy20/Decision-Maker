package persistence;

import exception.EmptyStringException;
import model.DecisionList;
import model.Decision;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
public class JsonWriterTest {
    Decision testDecision1;
    Decision testDecision2;

    @Test
    void testWriterInvalidFile() {
        try {
            DecisionList dl = new DecisionList();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyDecision() {
        try {
            DecisionList dl = new DecisionList();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyDecisionList.json");
            writer.open();
            writer.write(dl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyDecisionList.json");
            dl = reader.read();
            assertEquals(0, dl.decisionSize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralDecisionList() {
        try {
            DecisionList dl = new DecisionList();
            try {
                this.testDecision1 = new Decision("study chemistry", "ikb");
                this.testDecision2 = new Decision("sleeping", "home");
            } catch (EmptyStringException e) {

            }
            dl.addDecision(testDecision1);
            dl.addDecision(testDecision2);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralDecisionList.json");
            writer.open();
            writer.write(dl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralDecisionList.json");
            dl = reader.read();
            assertEquals(2, dl.decisionSize());
            assertEquals("study chemistry",dl.getDecision(testDecision1.getDecisionID()).getDecisionName());
            assertEquals("ikb", dl.getDecision(1).getDecisionAddress());
            assertEquals("sleeping",dl.getDecision(2).getDecisionName());
            assertEquals("home",dl.getDecision(2).getDecisionAddress());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}

