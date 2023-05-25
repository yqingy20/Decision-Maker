package ui;

import exception.EmptyStringException;
import model.Decision;
import model.DecisionList;
import model.EventLog;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.Image;
import java.sql.SQLOutput;

// Represent the application's main window frame
public class DecisionMakerUI extends JFrame {
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 620;
    private JComboBox<String> printCombo;
    private DecisionList dc;
    JFrame frame;
    JSplitPane splitPane;
    JPanel subPanel1;
    JPanel subPanel2;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/decisions.json";
    ImageIcon imageBear;
    JLabel displayFieldBear;
    ImageIcon imageRabbit;
    JLabel displayFieldRabbit;
    JLabel title;
    private JTextArea logArea;


    // constructor
    // MODIFY: this
    public DecisionMakerUI() {
        // new everything
        this.frame = new JFrame();
        this.dc = new DecisionList();
        this.jsonWriter = new JsonWriter(JSON_STORE);
        this.jsonReader = new JsonReader(JSON_STORE);

        this.splitPane = new JSplitPane();
        this.subPanel1 = new JPanel();
        this.subPanel2 = new JPanel();

        setLayout(new FlowLayout());

        // setup picture helper
        setUpImageBear();
        setUpImageRabbit();

        // set up helper
        titleSetting();
        subPanelSetting();
        addButtonPanel();
        splitPaneSetting();

        // add things and setup frame
        frame.setSize(WIDTH, HEIGHT);
        frame.setTitle("Decision Maker");
        frame.add(splitPane);
        subPanel1.add(title);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // set up another frame


    }

    // MODIFY: this
    // EFFECT: helper to set up the sub panel
    private void subPanelSetting() {
        subPanel1.setBackground(new Color(151, 167, 190));
        subPanel2.setBackground(new Color(234, 223, 219));
        subPanel1.setSize(WIDTH / 2 - 10, HEIGHT / 2 - 10);
        subPanel2.setSize(WIDTH / 2 - 10, HEIGHT / 2 - 10);
        subPanel1.setLayout(new FlowLayout());
    }

    // MODIFY: this
    // EFFECT: helper to set up the picture bear
    private void setUpImageBear() {
        try {
            this.imageBear = new ImageIcon("./data/bearImage.PNG");
            ImageIcon saleImage = new ImageIcon(imageBear.getImage().getScaledInstance(400, 450,
                    Image.SCALE_DEFAULT));
            this.displayFieldBear = new JLabel(saleImage);
            subPanel2.add(displayFieldBear);
        } catch (Exception e) {
            System.out.println("Image cannot be found");
        }
    }

    // MODIFY: this
    // EFFECT: helper to set up the picture rabbit
    private void setUpImageRabbit() {
        try {
            this.imageRabbit = new ImageIcon("./data/rabitImage.PNG");
            ImageIcon saleImage = new ImageIcon(imageRabbit.getImage().getScaledInstance(300, 100,
                    Image.SCALE_DEFAULT));
            this.displayFieldRabbit = new JLabel(saleImage);
            subPanel1.add(displayFieldRabbit);
        } catch (Exception e) {
            System.out.println("Image cannot be found");
        }
    }

    // MODIFY: this
    // EFFECT: helper to set up the title
    private void titleSetting() {
        this.title = new JLabel("Decision Maker");
        title.setFont(new Font("Serif", Font.PLAIN, 100));
        title.setForeground(new Color(255, 210, 189));
        title.setLocation(30, 40);
    }

    // MODIFY: this
    // EFFECT: helper to set up the splitPane
    private void splitPaneSetting() {
        splitPane.setSize(WIDTH, HEIGHT);
        splitPane.setTopComponent(subPanel1);
        splitPane.setBottomComponent(subPanel2);

        splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        splitPane.setDividerLocation(HEIGHT / 2 - 200);
        splitPane.setOneTouchExpandable(true);
    }


    // EFFECT: Helper to add control buttons
    // MODIFY: this
    private void addButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(7, 5));
        buttonPanel.add(new JButton(new AddDecision()));
        buttonPanel.add(new JButton(new RemoveDecision()));
        buttonPanel.add(new JButton(new ViewDecision()));
        buttonPanel.add(new JButton(new SaveDecision()));
        buttonPanel.add(new JButton(new LoadDecision()));
        buttonPanel.add(new JButton(new GetDecision()));
        buttonPanel.add(new JButton(new Quit()));

        subPanel2.add(buttonPanel, BorderLayout.WEST);
    }

    private class Quit extends AbstractAction {

        Quit() {
            super("Quit");
        }

        public void printLog(EventLog el) {
            for (model.Event next : el) {
                System.out.println(next.toString() + "\n\n");
            }
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            printLog(EventLog.getInstance());
            System.exit(0);
        }
    }

    // Represents the action to be taken when the user wants to add a new
    // decision to the system.
    private class AddDecision extends AbstractAction {

        AddDecision() {
            super("Add Decision");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String decisionName = JOptionPane.showInputDialog(null,
                    "What is the Decision you want to make?", "Enter the decision name",
                    JOptionPane.QUESTION_MESSAGE);
            String decisionAddress = JOptionPane.showInputDialog(null,
                    "Where is your decision take place?", "Enter the decision address",
                    JOptionPane.QUESTION_MESSAGE);
            try {
                if (decisionAddress == "" || decisionName == "") {
                    throw new EmptyStringException();
                }
                Decision d = new Decision(decisionName, decisionAddress);
                dc.addDecision(d);
                JOptionPane.showMessageDialog(subPanel2, "Your decision has been added!");
            } catch (EmptyStringException e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(), "Your Decision Cannot be Null",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    // Represents the action to be taken when the user wants to view decisions
    private class ViewDecision extends AbstractAction {

        ViewDecision() {
            super("View Decision");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(subPanel2, dc.printWholeDecision());

        }
    }

    // Represents the action to be taken when the user wants to remove a
// decision to the system.
    private class RemoveDecision extends AbstractAction {

        RemoveDecision() {
            super("Remove Decision");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String decisionID = JOptionPane.showInputDialog(null,
                    "What is the decisionID of the decision you want to delete?",
                    "Enter the decision ID",
                    JOptionPane.QUESTION_MESSAGE);

            if (decisionID != null) {
                dc.removeDecision(Integer.parseInt(decisionID));
            }
        }
    }

    // Represents the action to be taken when the user wants save the decision
    private class SaveDecision extends AbstractAction {

        SaveDecision() {
            super("Save Decision");
        }


        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(subPanel2, "Your Decision has been saved!");
            try {
                jsonWriter.open();
                jsonWriter.write(dc);
                jsonWriter.close();
                System.out.println("Saved your decisionList to " + JSON_STORE);
            } catch (FileNotFoundException e1) {
                System.out.println("Unable to write to file: " + JSON_STORE);
            }
        }
    }

    // Represents the action to be taken when the user wants load the decision
    private class LoadDecision extends AbstractAction {

        LoadDecision() {
            super("Load Decision");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(subPanel2, "You decision has been load!");
            try {
                dc = jsonReader.read();
                System.out.println("Loaded your decisionList from " + JSON_STORE);
            } catch (IOException e2) {
                System.out.println("Unable to read from file: " + JSON_STORE);
            }
        }


    }

    // Represents the action to be taken when the user wants to get one decision
    private class GetDecision extends AbstractAction {
        GetDecision() {
            super("Get A Decision!");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Decision random = dc.randomDecision();
            JOptionPane.showMessageDialog(subPanel2, "You are going to " + random.getDecisionName()
                    + " at " + random.getDecisionAddress() + "! ");
        }
    }


    // represents action to be taken when user clicks desktop to switch
// focus (needed for key handling)
// this method is taken from CPSC 210 AlarmSystem
    private class DesktopFocusAction extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            DecisionMakerUI.this.requestFocusInWindow();
        }

    }

    public static void main(String[] args) {
        new DecisionMakerUI();
    }
}
