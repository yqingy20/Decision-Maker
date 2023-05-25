package ui;

import model.DecisionList;

import java.io.FileNotFoundException;

// run the decision Maker app
public class Main {
    public static void main(String[] args) {
        try {
            new DecisionMaker();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }
}
