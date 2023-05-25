package model;

import exception.EmptyStringException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


import java.util.ArrayList;
import java.util.List;

// tests
public class DecisionListTest {
    private DecisionList testDecisionList1;
    private DecisionList testDecisionList2;
    private DecisionList testDecisionList3;
    private Decision testDecision1;
    private Decision testDecision2;
    private Decision testDecision3;

    @BeforeEach
    public void setUp() {
        testDecisionList1 = new DecisionList();
        testDecisionList2 = new DecisionList();
        testDecisionList3 = new DecisionList();
        try {
            testDecision1 = new Decision("Nori", "UBC");
            testDecision2 = new Decision("The Point", "Place Vanier");
            testDecision3 = new Decision("Pizza", "Student Nest");
        } catch (EmptyStringException e) {

        }
    }
    @Test
    public void addDecisionTest() {
        // test if the decisionList has no element, and add one element
        // call the method
        testDecisionList1.addDecision(testDecision2);
        // test
        assertTrue(testDecisionList1.containDecision(testDecision2));
        assertEquals(1,testDecisionList1.decisionSize());
        // test if the same decision will be added twice (if the item already exists in the list
        //                                                should not be added)
        // call the method
        testDecisionList1.addDecision(testDecision2);
        // test
        assertTrue(testDecisionList1.containDecision(testDecision2));
        assertEquals(1, testDecisionList1.decisionSize());
        // test if the decisionList has one element, add another element
        // call the method
        testDecisionList1.addDecision(testDecision1);
        // test
        assertTrue(testDecisionList1.containDecision(testDecision1));
        assertTrue(testDecisionList1.containDecision(testDecision2));
        assertEquals(2, testDecisionList1.decisionSize());
    }

    @Test
    public void removeDecisionTest() {
        // test if one element in the list, and remove the only element by giving the decisionID
        // add the element
        testDecisionList1.addDecision(testDecision1);
        // call the method
        Boolean result = testDecisionList1.removeDecision(testDecision1.getDecisionID());
        // test
        assertEquals(0,testDecisionList1.decisionSize());
        assertTrue(result);
        // test if two element is in the list, and only one of the element is deleted
        // add the element
        testDecisionList2.addDecision(testDecision1);
        testDecisionList2.addDecision(testDecision2);
        // call the method
        Boolean result1 = testDecisionList2.removeDecision(testDecision2.getDecisionID());
        // test
        assertTrue(testDecisionList2.containDecision(testDecision1));
        assertFalse(testDecisionList2.containDecision(testDecision2));
        assertEquals(1,testDecisionList2.decisionSize());
        assertTrue(result1);
        // test if no such ID/Decision exists in the list
        // add element
        testDecisionList3.addDecision(testDecision2);
        // call the method
        Boolean result2 = testDecisionList3.removeDecision(100);
        assertEquals(1, testDecisionList3.decisionSize());
        assertTrue(testDecisionList3.containDecision(testDecision2));
        assertFalse(result2);
    }

    @Test
    public void getDecisionTest() {
        // return a Decision after input a decisionID
        // test if there is such element in the list
        // add element
        testDecisionList1.addDecision(testDecision1);
        // call the method
        Decision result = testDecisionList1.getDecision(testDecision1.getDecisionID());
        // test
        assertEquals(testDecision1,result);
        // test if no such element in the list
        // call the method
        Decision result1 = testDecisionList1.getDecision(100);
        // test
        assertEquals(null, result1);
    }

    @Test
    public void randomDecisionTest() {
        // return a random decision
        // test if the return is Decision
        // add item into the testDecisionList
        testDecisionList1.addDecision(testDecision1);
        testDecisionList1.addDecision(testDecision2);
        testDecisionList1.addDecision(testDecision3);
        // test that the return is not null
        assertFalse(null == testDecisionList1.randomDecision());
        // test that the return value is in the list above
        // call to the method and save the result
        Decision result = testDecisionList1.randomDecision();
        // test if the result is in the list
        assertTrue(testDecisionList1.containDecision(result));
    }

    @Test
    public void getEachDecisionTest() {
        // add Decisions to the testDecisionList
        testDecisionList1.addDecision(testDecision3);
        testDecisionList1.addDecision(testDecision2);
        testDecisionList1.addDecision(testDecision1);
        // tests
        assertEquals(testDecision1,testDecisionList1.getEachDecision(2));
        assertEquals(testDecision2,testDecisionList1.getEachDecision(1));
        assertEquals(testDecision3,testDecisionList1.getEachDecision(0));
    }

    @Test
    public void printWholeDecisionTest() {
        // add Decisions to the testDecisionList
        testDecisionList1.addDecision(testDecision1);
        testDecisionList1.addDecision(testDecision2);
        testDecisionList1.addDecision(testDecision3);
        // tests
        assertEquals("Decision: Nori. Address: UBC. Decision ID: 1" +
                "Decision: The Point. Address: Place Vanier. " +
                "Decision ID: 2" +
                "Decision: Pizza. " +
                "Address: Student Nest. Decision ID: 3",testDecisionList1.printWholeDecision());
    }
}
