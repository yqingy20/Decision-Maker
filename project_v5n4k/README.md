# My Personal Project

## A Decision Maker

Everyone has to make different kinds of choices throughout their lives,
and some decisions are very hard to make. Thinking about what decision to make
takes a lot of time, and therefore that's why this decision maker is needed!
This decision maker can help you to deal with any kind of decisions that you
may have to make throughout your life.

*You may face these decisions in your life:*

- What to have for lunch today?
- Where to go for the weekend/holiday?
- Which offer to accept?
- Where to go for a date?

This project is designed for people who have a hard time making quick and easy
decisions. I have a hard time making decisions, especially with what to have for
lunch every day, therefore a decision maker is a good project for me to build. It can
help people to make **quick** and **easy** decisions.

## User Stories

- As a user, I want to be able to add list of possible decision into a list.
- As a user, I want to be able to remove a possible decision from the decision list.
- As a user, I want to be able to view my possible decision list.
- As a user, I want to be able to get a decision after start the decision maker.
- As a user, I want to be able to save my decision list to file.
- As a user, I want to be able to be able to load my decision list from file.

**Some codes in Phase 2 is referred to the JsonSerializationDemo**

## Instruction for Grading

- You can generate the first required event related to adding Xs to a Y by adding
  a decision to the decisionList
- You can generate the second required event related to adding Xs to a Y by getting
  a random decision from the decisionList
- You can locate my visual component by the PNG file "rabitImage" and "bearImage" both
  in the class folder and when running the UI
- You can save the state of my application by clicking the "Save the decision" button and
  save the decision
- You can reload the state of my application by clicking the button "Load the decision" button
  and load the decision

## Phase 4: Task 2

Mon Nov 28 11:37:01 PST 2022
Added Decision

Mon Nov 28 11:37:08 PST 2022
Added Decision

Mon Nov 28 11:37:11 PST 2022
Removed Decision

Mon Nov 28 11:37:12 PST 2022
Viewed Decision

## Phase 4: Task 3

***Design for the UML Diagram***

- **Main class:** Main class does not have any field, but it has newed a decisionMaker
  app, which means these two class are in dependency relationship.
- **DecisionMaker class:** DecisionMaker class has one field from the Decision class, the
  DecisionList class, the JsonReader class and the JsonWriter class, each of them has one object.
- **DecisionMaker UI class:** DecisionMaker UI class has one field from the decisionList class, the
  JsonReader class, the JsonWriter class respectively.
- **Decision class:** Decision class has one field of the DecisionList class, and has implement
  the writable class.
- **DecisionList class:** DecisionList class has field of the Decision class, which field type is
  List<Decision>, from 0 to *(infinity).
- *Note that the Decision class and DecisionList class are in the bidirectional relationship.*
- **JsonReader class:** JsonReader class has dependency relationship with the decisionList class and
  the decision class.
- **JsonWriter class:** JsonWriter class has dependency relationship with the decisionList class.
- **Writable:** Writable class is an interface which Decision class has implemented it.

***Future improvement for the project***

- If more time is given, the DecisionMaker UI could be improved by adding panels instead of J-OptionalPane
- Adding another function for the possible multiple decisionMaker can be running at the same time
- Adding another function that allows the user to upload image for the decision they want to make