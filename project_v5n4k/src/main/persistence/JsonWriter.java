package persistence;

import model.DecisionList;
import org.json.JSONArray;
import org.json.JSONObject;


import java.io.*;

// Represents a writer that writes JSON representation of decisionList to file
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination file
    // Method taken from JsonSerializationDemo which website is
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    // Method taken from JsonSerializationDemo which website is
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of decisionList to file
    // Method taken from JsonSerializationDemo which website is
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    public void write(DecisionList dl) {
        JSONArray json = dl.decisionsToJson();
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    // Method taken from JsonSerializationDemo which website is
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    // Method taken from JsonSerializationDemo which website is
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    private void saveToFile(String json) {
        writer.print(json);
    }
}

