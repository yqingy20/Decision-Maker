package persistence;

import org.json.JSONObject;

public interface Writable {
    // Method taken from JsonSerializationDemo which website is
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
