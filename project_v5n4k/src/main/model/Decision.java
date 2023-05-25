package model;

import exception.EmptyStringException;
import org.json.JSONObject;
import persistence.Writable;

import java.util.List;

// represent a decision with address and task
// each decision has its own id, and its never repeat
public class Decision implements Writable {
    private static int firstDecisionID = 1;   // the first decision will be assigned ID = 1
    private List<Decision> decisionList;      // represents the list of decision
    private String decisionAddress;           // where the decision will be done
    private String decisionName;              // represents the name of the decision
    private int decisionID;                   // the track ID of each decision

    // constructor
    // REQUIRE:  the decisionName / decision Address cannot be an empty string
    // EFFECTS:  create a Decisions with the decisionName and the decisionAddress
    //           the decision ID is a positive integer, and not assigned to other decision
    public Decision(String decisionName, String decisionAddress) throws EmptyStringException {
        if (decisionName == "" || decisionAddress == "") {
            throw new EmptyStringException();
        } else {
            this.decisionAddress = decisionAddress;
            this.decisionName = decisionName;
            decisionID = firstDecisionID++;
        }
    }

    // constructor
    // REQUIRE: the decisionName cannot be an empty string
    // EFFECTS: create a Decision with the decisionName, decision Address, and the
    //          decisionID that is given
    public Decision(String decisionName, String decisionAddress, int decisionID) {
        this.decisionAddress = decisionAddress;
        this.decisionName = decisionName;
        this.decisionID = decisionID;
    }

    // EFFECTS: return the decision address
    public String getDecisionAddress() {
        return decisionAddress;
    }

    // EFFECTS: return the decision name
    public String getDecisionName() {
        return decisionName;
    }

    // EFFECTS: return the decision ID
    public int getDecisionID() {
        return decisionID;
    }

//    public String toString() {
//        return "[Decision Name:" + this.decisionName + "\n" + "Decision Address:" + this.decisionAddress + "]\n";
//    }

    @Override
    // EFFECT: save the decision
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("decisionName", this.decisionName);
        json.put("decisionAddress", this.decisionAddress);
        json.put("decisionID", this.decisionID);
        return json;
    }
}
