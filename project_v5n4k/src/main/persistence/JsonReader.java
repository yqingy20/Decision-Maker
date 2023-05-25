package persistence;

import model.DecisionList;
import model.Decision;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads DecisionList from file and returns it;
    // throws IOException if an error occurs reading data from file
    // Method taken from JsonSerializationDemo which website is
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    public DecisionList read() throws IOException {
        String jsonData = readFile(source);
        JSONArray jsonArray = new JSONArray(jsonData);
        return parseDecisionList(jsonArray);
    }

    // EFFECTS: reads source file as string and returns it
    // Method taken from JsonSerializationDemo which website is
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }
//
//    // EFFECTS: parses decisionlist from JSON object and returns it
//    private DecisionList parseDecisionList(JSONObject jsonObject) {
//        DecisionList dl = new DecisionList();
//        addDecision(dl, jsonObject);
//        return dl;
//    }
//
//    // MODIFIES: dl
//    // EFFECTS: parses decision from JSON object and adds it to workroom
//    private void addDecision(DecisionList dl, JSONObject jsonObject) {
//        String name = jsonObject.getString("decisionName");
//        String address = jsonObject.getString("decisionAddress");
//        Decision decision = new Decision(name,address);
//        dl.addDecision(decision);
//    }

    // EFFECTS: parses decisionList from JSON object and returns it
    // Method taken from JsonSerializationDemo which website is
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    private DecisionList parseDecisionList(JSONArray jsonArray) {
        DecisionList dl = new DecisionList();
        for (Object d : jsonArray) {
            JSONObject json = (JSONObject) d;
            addDecision(dl,json);
        }
        return dl;
    }

//    // MODIFIES: wr
//    // EFFECTS: parses thingies from JSON object and adds them to workroom
//    private void addDecisions(DecisionList dl, JSONObject jsonObject) {
//        JSONArray jsonArray = jsonObject.getJSONArray("thingies");
//        for (Object json : jsonArray) {
//            JSONObject nextThingy = (JSONObject) json;
//            addDecision(dl, nextThingy);
//        }
//    }

    // EFFECTS: parses decision from JSON object and adds it to DecisionList
    // Method taken from JsonSerializationDemo which website is
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    private void addDecision(DecisionList dl, JSONObject jsonObject) {
        String decisionName = jsonObject.getString("decisionName");
        String decisionAddress = jsonObject.getString("decisionAddress");
        int decisionID = jsonObject.getInt("decisionID");
        Decision d = new Decision(decisionName,decisionAddress,decisionID);
        dl.addDecision(d);
    }
}
