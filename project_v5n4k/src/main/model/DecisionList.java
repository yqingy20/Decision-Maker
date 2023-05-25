package model;

import exception.EmptyStringException;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// represent a list of Decision
public class DecisionList {
    private List<Decision> listOfDecision;


    // constructor
    // EFFECT: create a decisionList with no decision in it
    // Modify: this.
    public DecisionList() {
        this.listOfDecision = new ArrayList<>();
    }

    // getDecision
    // REQUIRE: the decisionList must contain at least one element
    // EFFECT: get a decision after input a decisionID
    //         -  if there is no such decision in the list, return -1
    public Decision getDecision(int decisionID) {
        for (Decision d : listOfDecision) {
            if (decisionID == d.getDecisionID()) {
                return d;
            }
        }
        return null;
    }

    // addDecision
    // EFFECT:adding one decision into the decision list after input a decision
    //        - if the object is already in the list, do nothing
    //        - if the object is not in the list, add the object into the list
    // MODIFY: this.
    public void addDecision(Decision d) {
        if (!listOfDecision.contains(d)) {
            this.listOfDecision.add(d);
            EventLog.getInstance().logEvent(new Event("Added Decision"));
        }
    }

    // removing decisions
    // REQUIRE: the decisionList must already have at least one element, cannot be an empty list
    // MODIFY: this.
    // EFFECT: removing one Decision from the decisionList after input a decisionID and a decisionList
    //         - if find the decisionID matches with one decision in the decisionList
    //           remove it and return true
    //         - if does not find the matches of the decisionID, return false
    public Boolean removeDecision(int id) {
        for (Decision d : this.listOfDecision) {
            if (id == d.getDecisionID()) {
                this.listOfDecision.remove(d);
                EventLog.getInstance().logEvent(new Event("Removed Decision"));
                return true;
            }
        }
        return false;
    }

    // randomly assigned decision
    // REQUIRE: the decisionList input must at least has one element
    // EFFECT: randomly return a Decision that is in the decisionList
    public Decision randomDecision() {
        Random rand = new Random();
        Decision result = listOfDecision.get(rand.nextInt(listOfDecision.size()));
        EventLog.getInstance().logEvent(new Event("Get Random Decision"));
        return result;
    }

    // return the size of the DecisionList
    // EFFECT: return the size of the decisionList
    public int decisionSize() {
        return this.listOfDecision.size();
    }

    // determine whether the list contain one Decision
    // EFFECT: return true if the list contains such Decision
    public Boolean containDecision(Decision d) {
        if (this.listOfDecision.contains(d)) {
            return true;
        }
        return false;
    }

//    // return the whole decisionList
//    public List<Decision> viewDecision() {
//        return this.listOfDecision;
//    }

    // return the item after inputting the int
    // return the first item in the list if input 0
    // 0 base indexing
    public Decision getEachDecision(int i) {
        return listOfDecision.get(i);
    }

//    //
//    public String printAllDecision() {
//        StringBuilder ans = new StringBuilder();
//        for (Decision d : this.listOfDecision) {
//            ans.append(d.toString()).append("/n");
//        }
//        return ans.toString();
//    }

    // EFFECTS: returns decisions in this decisionList as a JSON array
    public JSONArray decisionsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Decision d : this.listOfDecision) {
            jsonArray.put(d.toJson());
        }

        return jsonArray;
    }

    // EFFECTS: return the string of all decisions, including the decision name/address/id
    public String printWholeDecision() {
        String result = "";
        for (int i = 0; i < decisionSize(); i++) {
            String s = "Decision: " + getEachDecision(i).getDecisionName()
                    + ". Address: " + getEachDecision(i).getDecisionAddress()
                    + ". Decision ID: " + getEachDecision(i).getDecisionID();
            result = result + "\n" + s;
        }
        EventLog.getInstance().logEvent(new Event("Viewed Decision"));
        return result;
    }
}
