package model;

import exception.EmptyStringException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


import java.util.ArrayList;
import java.util.List;

class DecisionTest {
    private DecisionList testDecisionList1;
    private DecisionList testDecisionList2;
    private DecisionList testDecisionList3;
    private Decision testDecision1;
    private Decision testDecision2;
    private Decision testDecision3;
    private Decision constructorTestDecision1;
    private Decision constructorTestDecision2;

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
            fail("Unexpected Exception");
        }
    }

    @Test
    public void constructorTest1() {
        try {
            constructorTestDecision1 = new Decision("","");
            fail("Exception did not throw");
        } catch (EmptyStringException e) {

        }
    }

    @Test
    public void constructorTest2() {
        try {
            constructorTestDecision2 = new Decision("a","");
            fail("Exception did not throw");
        } catch (EmptyStringException e) {

        }
    }


    @Test
    public void constructorTest() {
        // test if each decision has a different decisionID
        assertFalse(testDecision1.getDecisionID() == testDecision2.getDecisionID());
        assertFalse(testDecision1.getDecisionID() == testDecision3.getDecisionID());
        assertFalse(testDecision3.getDecisionID() == testDecision2.getDecisionID());
    }

    @Test
    public void getDecisionNameTest() {
        assertEquals("Nori",testDecision1.getDecisionName());
        assertEquals("The Point",testDecision2.getDecisionName());
    }

    @Test
    public void getDecisionAddressTest() {
        assertEquals("UBC",testDecision1.getDecisionAddress());
        assertEquals("Place Vanier",testDecision2.getDecisionAddress());
    }
}

