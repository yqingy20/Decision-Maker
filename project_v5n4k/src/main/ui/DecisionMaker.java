package ui;

import exception.EmptyStringException;
import model.Decision;
import model.DecisionList;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;
import java.util.Scanner;
import java.util.List;
import java.io.FileNotFoundException;

// the Decision Maker app
public class DecisionMaker {
    private static final String JSON_STORE = "./data/decisions.json";
    private Scanner input;
    private Decision decision;
    private DecisionList decisionList;
    private String decisionAddress;
    private String decisionName;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: constructs decisionList and runs application
    public DecisionMaker() throws FileNotFoundException {
        input = new Scanner(System.in);
        decisionList = new DecisionList();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runDecisionMaker();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runDecisionMaker() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.nextLine();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }


    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("a")) {
            addDecision();
        } else if (command.equals("r")) {
            removeADecision();
        } else if (command.equals("g")) {
            getADecision();
        } else if (command.equals("v")) {
            viewDecision();
        } else if (command.equals("s")) {
            saveDecisionList();
        } else if (command.equals("l")) {
            loadDecisionList();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes the Decision
    private void init() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> add a decision");
        System.out.println("\tr -> remove a decision");
        System.out.println("\tv -> view your decision");
        System.out.println("\tg -> get a decision");
        System.out.println("\ts -> save work room to file");
        System.out.println("\tl -> load work room from file");
        System.out.println("\tq -> quit");
    }

    // EFFECTS: saves the decisionList to file
    // Method taken from JsonSerializationDemo which website is
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    private void saveDecisionList() {
        try {
            jsonWriter.open();
            jsonWriter.write(decisionList);
            jsonWriter.close();
            System.out.println("Saved your decisionList to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads decisionList from file
    // Method taken from JsonSerializationDemo which website is
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    private void loadDecisionList() {
        try {
            decisionList = jsonReader.read();
            System.out.println("Loaded your decisionList from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // MODIFY: this
    // EFFECT: create a new decision with the user input decisionName and decisionAddress
    // REQUIRES: the input for decisionName and decisionAddress cannot be empty string
    private void addDecision() {
        System.out.println("Please enter the name of the decision you would like to make:");
        this.decisionName = input.nextLine();

        System.out.println("Please enter the address that your decision takes place:");
        this.decisionAddress = input.nextLine();

        try {
            this.decision = new Decision(decisionName, decisionAddress);
        } catch (EmptyStringException e) {
            System.out.println("The Decision Name and Decision Address cannot be null!");
        }

        decisionList.addDecision(decision);
    }

    // MODIFY: this
    // EFFECT: remove a decision after input a decisionID
    private void removeADecision() {
        System.out.println("Please enter the decision ID of the decision you would like to remove:");
        int decisionID = input.nextInt();
        decisionList.removeDecision(decisionID);
    }

    // EFFECT: random output a decision from the decisionList
    private void getADecision() {
        Decision randomDecision = decisionList.randomDecision();
        printDecision(randomDecision);
    }

    // EFFECT: print out all decisions in the list
    private void viewDecision() {
        printWholeDecision();
    }

    // EFFECT: print the random decision to the screen
    private void printDecision(Decision d) {
        System.out.println("You are going to " + d.getDecisionName() + " at " + d.getDecisionAddress());
    }

    // EFFECT: print the whole decisionList
    private void printWholeDecision() {
        for (int i = 0; i < decisionList.decisionSize(); i++) {
            System.out.println("Decision: " + decisionList.getEachDecision(i).getDecisionName()
                    + ". Address: " + decisionList.getEachDecision(i).getDecisionAddress()
                    + ". Decision ID: " + decisionList.getEachDecision(i).getDecisionID());
        }
    }
}
