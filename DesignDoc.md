# **I. BOWLING MANAGEMENT SYSTEM - DESIGN DOCUMENT** :

**Title :** Bowling Management System
**Date of Submission :** April 9, 2020

**Team Members :**

|SNo.| Team Member | Hours of Work/day | Contributions  |
|------------|------------|------------|------------|
|1.|Mallika Subramanian|15| <ul><li>Refactoring to reduce cyclomatic complexity</li> <li>Increasing cohesion amongst classes and methods</li><li>Analysing and identifying responsibilites of major classes as well as the interlinked classes.</li><li>Creating UML class and sequence diagrams for the original as well as refactored code</li><ul>|
|2.|Aryamaan Jain|15| <ul><li>Refactoring to reduce cyclomatic complexity</li> <li>Reducing number of methods per class</li><li>Understanding the metrics to be measured and documenting potential changes that can be made to improve them</li><li>Implementing the Database layer for ad-hoc queries and the save-quit and restart feature.</li><ul>|
|3.|E Nikhil|15| <ul><li>Identifying the critical code smells in the code</li> <li>Refactoring to get rid of redundant code</li><li>Implementing the pause/resume</li><li>Creating Sequence diagrams</li><ul>|

# **II. BRIEF OVERVIEW** :

The Bowling Management System is a game that is entirely developed in Java. It is a virtual game that enables players to enjoy the fun of bowling from their laptops. The game simulates several features that add to the overall appeal of the game. Some of the significant features included in the game are : 

* Control Desk : The control desk operator has the ability to monitor the scores of any active lane. A configurable display option will allow the operator to view the score of an individual scoring station or multiple scoring stations.

* Creating a new player : A *NewPatron* can be created to play the game. This player is then added to the Bolwers database file. The new player will then be eligable to join a party and play a round of the game. 

* Adding a new party  : Selected number of bowlers can be added to a party which is then assigned to one of the free lanes to begin playing a game. In case all the lanes are occupied - the party is then added to the *Queue* that keeps track of the parties that are registered but yet to play. 

* Viewing the Pinsetter : For a particular lane, the user can also view the pinsetter window which simulates the pins dropped on each ball-throw. The pinsetter will re-rack the pins (places all ten down) after two consecutive throws have been detected. 

* Viewing the Scoreboard : The scoreboard keeps track of the score gained by each player in the party after their respective turns. It uses the normal score calculation technique as used in a regular game of bowling - ie for a spare : score = 10 + pins dropped on next ball and for a strike : score = 10 + pins dropped on next 2 balls.

* Maintenance Call : This is essentially a simulation of some repair work  – ball not returned, pinsetter did not re-rack, etc.– that is to take place for a particular lane. The game play is halted for the time the lane is being repaired. 
 
# **III. UML CLASS DIAGRAMS (Before Refactoring)** :

Below are UML diagrams describing some of the major functionalities of the game.

There are several relationships exhibited by the different member classes that make up the components of the game. Some of these are :

1. Association : Shows a relationship between the two classes. One of the classes may have objects of another class being used within it. This is shown by a bold arrow line.

2. Dependency : In some cases it can also show a dependency between two or more classes. Any changes made to a class may cause changes in the class that is dependent on it.

2. Composition: This depicts the relationship between two classes where one class "is entirely made of another class" ie: One of the classes cannot exist if the parent/main class object doesn't exist. This is represented by an arrow with a darkened diamond at the end of the parent class.

3. Aggregation : This resembles the "part of" relationship between 2 classes. ie : One class is "a part" of another class.

4. Generalization : This is used one one class generalises the functionalities of all its subclasses. That is it is an umbrella class for other classes which inherit all properties from the parent as well have some other specifi properties unique to them.

5. Specialisation : This is the exact opposite of generalisation. A specialised class is a subclass which inherits all properties from its parent class and also adds certain specific properties that are unique to it. All specific classes will be under a particular main/parent class.

**A. Functionality : This UML diagram represents the functionality of creating a new party for a game and assigning a particular lane for the same. The bowlers can be chosen from an existing list or a NewPatron can be added**

* The classes involved in this are :
  - ControlDesk
  - ControlDeskObserver (Interface)
  - ControlDeskEvent
  - ControlDeskView
  - Bowler
  - Party
  - NewPatronView
  - AddPartyView
  - EndGameReport
  - EndGamePrompt
  - Lane
  - Alley
  - Drive


* The cardinalities - *number of participating objects in any association between two classes* - are also mentioned on the relationship arrows of the classes.

* To further indicate the creation of classes, it has also been specified as to which class is create from which parent class. The ```create``` written above the arrows indicates the parent-child relationship.

* The ```ControlDeskObserver``` class is an interface class. Essentially serves as an interface between two or more classes that may not be able to interact otherwise. For eg: the ```ControlDeskView``` and ```ControlDeskEvent``` classes are interfaced to be able to share infromation and perform functions. This is also a demonstration of the *Adapter Design Pattern* 

* The ```drive``` class is the driver module for the game to begin. It is linked to the ```ControlDesk``` class that carries out all the functions to control the game. 

* All the methods and attributes associated with each class are shown in the UML class diagram as well. The private attributes and methods are represented by **-** and the public ones are represented by **+**.

The several arrows in the diagram represent different relationships between the multiple classes. 

- **Associations and Dependencies** : 
    - ```AddPartyView``` and ```Bowler``` class are also related via association. The ```AddPartyView``` class has a list of all the Bowlers and hence depends on the Bowler class in order to obtain the Names and Nicknames of the bowlers. 
    - The ```ControlDesk``` and ```ControlDeskView``` classes are associated as the ```ControlDeskView``` class uses an object of the ```ControlDesk``` class.
    - ```AddPartyView``` and ```NewPatronView``` are also associated in a similar manner
    - ```ControlDeskView``` and ```AddPartyView``` also have an association relation

- **Compositions**: 
    - The ```NewPatronView``` class composes the ```AddPartyView``` class since a major functionality supported by the ```AddPartyView``` class is the add a patron to the list of already existing bolwers.
    - The ```AddPartyView``` class composes the ```ControlDeskView``` since the main purpose or functionality of the ```ControlDesk```is to enable the players to add/create a party with a set of members/patrons and play the game. This is done via the ```AddPartyView``` class's members and methods. 
    - The ```ControlDeskiew``` class inturn composes the control desk class since without the View provided by the GUI there would be no interface to support the ```ControlDesk```class. 
    - The ```Bowler``` object is a *composition* of the lane class indicating that if there exists an objec of the ```Bowler``` class then it must definitely belong to some ```Lane``` object and cannot exist alone. There exists a *1:n* cardinality indicating that for a particular ```Lane``` there can be multiple ```Bowlers``` associated to it and a ```Bowler``` can be associated to only 1 lane.
    - For ```Alley``` and ```ControlDesk``` classes, if there exists an object of the ```ControlDesk``` then it must have at least one object of ```Alley``` linked to it. It cannot exists standalone, hence the compostion relationship.


![uml-class-1](/home/mallika/Desktop/DASS/Assn/A3/Bowling-Management-System/UML-Diagram1.png)



**B. Fucntionality : Simulating a Ballthrow and observing corresponding changes in the Score and PinsetterView**

* The classes involved in this are :
  - Pinsetter
  - PinsetterObserver (interface)
  - PinsetterEvent
  - PinSetterView
  - Lane
  - LaneEvent
  - LaneObserver
  - LaneServer
  - LaneView
  - LaneStatusView
  - LaneEventInterface


* The cardinalities - *number of participating objects in any association between two classes* - are mentioned on the relationship arrows of the classes.

* To further indicate the creation of classes, it has also been specified as to which class is create from which parent class. The ```create``` written above the arrows indicates the parent-child relationship.

* The ```PinsetterObserver```, ```LaneObserver```, ```LaneEventInterface``` classes are an interface class. Essentially serves as an interface between two or more classes that may not be able to interact otherwise. For eg: the ```PinSetterView``` and ```Pinsetter``` classes are interfaced to be able to share infromation and perform functions. Also, the ```LaneStatusView``` and ```LaneView``` are interfaced with ```LaneObserver``` to ```LaneEvent```. This is also a demonstration of the *Adapter Design Pattern* 

* All the methods and attributes associated with each class are shown in the UML class diagram as well. The private attributes and methods are represented by **-** and the public ones are represented by **+**.

The several arrows in the diagram represent different relationships between the multiple classes. 

- **Associations and Dependencies** : 
    - ```Pinsetter``` & ```Lane``` and ```PinsetterView``` & ```LaneStatusView```classes are related via an association indicated by a solid arrow that shows that they have objects of these respective classes shared between them.
    - Dependencies are indicated by dotted arrows and show that a change in the class to which the arrow head points will and can cause a change to the dependent class. 

- **Compositions**: 
    - The ```LaneView``` and ```Lane``` classes exist only if the ```LaneStatusView ```class object exists. That is the ```LaneStatusView```class composes the remaining classes. This is because, for every Lane there must be a UI view and and status view linked to it. 
    - The ```LaneStatusView``` class also composes the ```PinsetterView``` class indicating that if there exists a ```PinsetterView``` object then it must be the view of the pins belonging to some ```LaneStatusView``` object. 
    - Likewise the ```Lane``` class also composes the ```Pinsetter``` class.

![uml-class-2](/home/mallika/Desktop/DASS/Assn/A3/Bowling-Management-System/UML-Diagram2.png)


**C. Fucntionality : Scoring the game and maintaing a queue of the various parties**

* The classes involved in this are :
  - Score
  - ScoreHistoryFile
  - PrintableText
  - ScoreReport
  - BowlerFile
  - Queue
  - ControlDesk


* The cardinalities - *number of participating objects in any association between two classes* - are mentioned on the relationship arrows of the classes.

* To further indicate the creation of classes, it has also been specified as to which class is create from which parent class. The ```create``` written above the arrows indicates the parent-child relationship.

* This UML class diagram unlike the previous two shows two disjoint sets of classes. However in order to successfully implement this functionality, all these classes are required. 

* All the methods and attributes associated with each class are shown in the UML class diagram as well. The private attributes and methods are represented by **-** and the public ones are represented by **+**.

The several arrows in the diagram represent different relationships between the multiple classes. 

- **Associations and Dependencies** : 
    - ```Score``` & ```ScoreHistoryFile```, ```Score``` & ```ScoreReport```, ```ScoreReport``` & ```ScoreHistoryFile``` are all related via a dependency indicated by a dotted arrow that shows that they have objects of these respective classes shared between them.

- **Compositions**: 
    - The ```ControlDesk``` class composes the ```Queue``` class indicating that if there exists a ```Queue``` object comprising the list of all the parties in the queue, then it must be managed by some ```ControlDesk``` object. 
    
![uml-class-3](/home/mallika/Desktop/DASS/Assn/A3/Bowling-Management-System/UML-Diagram3.png)





# **IV. UML CLASS DIAGRAMS (After refactoring)**

Below are UML diagrams describing the same major functionalities of the game mentioned in the previous section.

The diagram follows a *KEY* that represents the changes and modifications that were done while refactoring and thus resulted in the new UML Diagrams.

**KEY**:

* *New classes introduced:* These are represented by a **green** coloured class
* *New attributes/methods introduced:* These are represented by **bold and italics black text**
* *Methods or attributes eliminated:* These are represented by **red text**
* *New Associations or relations among classes:* These are represented by **blue arrows**

**A. Functionality : This UML diagram represents the functionality of creating a new party for a game and assigning a particular lane for the same. The bowlers can be chosen from an existing list or a NewPatron can be added**

![newuml-class-1](/home/mallika/Desktop/DASS/Assn/A3/Bowling-Management-System/New-UML-Diagram1.png)

**B. Fucntionality : Simulating a Ballthrow and observing corresponding changes in the Score and PinsetterView**

![newuml-class-2](/home/mallika/Desktop/DASS/Assn/A3/Bowling-Management-System/New-UML-Diagram2.png)

**C. Fucntionality : Scoring the game and maintaing a queue of the various parties**

![newuml-class-3](/home/mallika/Desktop/DASS/Assn/A3/Bowling-Management-System/New-UML-Diagram3.png)



# **V. SEQUENCE DIAGRAMS** : 

Below are the sequence diagrams that depict the flow of the various functionalities incorporated into the game. The sequence diagrams have been drawn for both the *original* and the *refactored* code and hence have subtle differences in their major control flow. 

**BEFORE REFACTORING**
![uml-seq-1](/home/mallika/Desktop/DASS/Assn/A3/Bowling-Management-System/Seq_diagram_before.png)

**AFTER REFACTORING**
![umL-seq-2](/home/mallika/Desktop/DASS/Assn/A3/Bowling-Management-System/Seq_diagram_after.png)


# **VI. SUMMARY OF RESPONSIBILITIES OF EACH MAJOR CLASS** : 

The Bowling Management System codebase has a collection of a total of 29 files. Each file has a collection of classes and funcitons that help simulate the entire game.
*Here is a list of all the files and their corresponding characteristics:*

|SNo.| File Name | Methods | Attribute  |Major Functionalities|Interlinked Classes|
|----|-----|----------------------------------------------------------------|-------|------|---------|
|1.| AddPartyView | <ul><li>void actionPerformed()</li><li>void valueChanged()</li><li>Vector getParty()</li><li>Vector getNames()</li><li> void updateNewPatron()</li> </ul> | <ul><li>Vector party</li><li>Vector bowlerdb</li> <li>ControlDeskView controlDesk</li> <li>String selectedNick</li> <li>String selectedMember</li></ul>  |<ul><li>Adding a new patron to party</li><li>Removing a patron from a party</li> <li>Creating a new patron</li> <li>Finished party selection</li> <li>Returning the latest state of the party</li></ul> |<ul><li>NewPatronView</li></ul> |
|2.| Alley | <ul><li>ControlDesk getControlDesk()</ul> | <ul><li>ControlDesk controldesk</li></ul>  |<ul><li>Return Current state of ControlDesk</li></ul> |<ul><li>ControlDesk</li></ul> |
|3.| Bowler | <ul><li>String getNickName()</li><li>String getFullName ( )</li><li>String getNick ( )</li><li>String getEmail ( )</li></ul> | <ul><li>String fullName</li><li>String nickName</li> <li>String email</li></ul>  |<ul><li>Getter functions</li><li>Validation of the bolwer</ul> |<ul>NIL</ul> |
|4.| BowlerFile | <ul><li>static Bowler getBowlerInfo(String nickName)</li><li>static void putBowlerInfo(String nickName,String fullName,String email)</li><li>static Vector getBowlers()</li></ul> | <ul><li>static String BOWLER_DAT</li></ul>  |<ul><li>Adding a new bowler</li><li>Getting details of one bowler</li> <li>Getting details of all bowlers</li></ul> |<ul>NIL</ul> |
|5.| ControlDesk | <ul><li>void run()</li><li>Bowler registerPatron(String nickName)</li><li>void assignLane() </li><li>void addPartyQueue(Vector partyNicks) </li><li> Vector getPartyQueue()</li><li>int getNumLanes()</li> <li>void publish(ControlDeskEvent event) </li><li>HashSet getLanes()</li></ul> | <ul><li>HashSet lanes</li><li>Queue partyQueue</li> <li>int numLanes</li> <li>Vector subscribers</li> </ul>  |<ul><li>Setter and Getter functions</li><li>Broadcast an event to subscribing objects.</li> <li>Creating a new patron</li> <li>Finished party selection</li> <li>Returning party names to be displayed in the GUI representation of the wait queue.</li> <li>Main loop for ControlDesk's thread</li> <li>Registering a Patron</li><li>Assigning a lane</li> </ul> |<ul><li>Lane</li></ul> |
|6.| ControlDeskEvent | <ul><li> Vector getPartyQueue()</li> </ul> | <ul><li>Vector partyQueue</li></ul>  |<ul><li>Returns a vector of the names of the parties in the waiting queue</li></ul> |
|7.| ControlDeskObserver | <ul><li>void receiveControlDeskEvent</li> </ul> | <ul>NIL</ul>  |<ul><li>Interface for classes that observe control desk events.</li></ul> |<ul><li>--------</li></ul> |
|8.| ControlDeskView | <ul><li>void actionPerformed(ActionEvent e)</li><li> void updateAddParty(AddPartyView addPartyView)</li><li>void receiveControlDeskEvent(ControlDeskEvent ce)</li></ul> | <ul><li>int maxMembers</li><li>ControlDesk controlDesk</li></ul>  |<ul><li>Display the GUI for the control desk</li><li>Handler for actionEvents</li><li>Receive a new party from andPartyView</li><li>Receive a broadcast from a ControlDesk</li></ul> |<ul><li>ControlDesk</li><li>AddPartyView</li></ul> |
|9.| drive | <ul><li>static void main()</li></ul> | <ul><li>int numLanes</li><li>int maxPatronsPerParty</li> </ul>  |<ul><li>Driver class for the entire game</li><li>Creates and alley with numLanes number of lanes</li> <li>Activates the control desk object</li> <li>Render the GUI for the control desk via ControlDeskView</li> |<ul><li>ControlDesk</li><li></li>Alley<li>ControlDeskView</li></ul> |
|10.| EndGamePrompt | <ul><li>EndGamePrompt( String partyName )</li><li> void actionPerformed(ActionEvent e)</li><li> int getResult()</li><li>void distroy()</li></ul> | <ul><li>int result</li><li>String selectedNick</li> <li>String selectedMember</li></ul>  |<ul><li>Displaying the end promt</li><li>Destroying the currently active game object.</li></ul> |<ul><li>--------</li></ul> |
|11.| EndGameReport | <ul><li>EndGameReport( String partyName, Party party )</li><li> void actionPerformed(ActionEvent e)</li><li> Vecotr getResult()</li><li>void distroy()</li><li>static void main( String args[] )</li><li>void valueChanged(ListSelectionEvent e)</li></ul> | <ul><li>int result</li><li>String selectedMember</li></ul>  |<ul><li>Displaying the end game repor</li><li>Destroying the currently active game object.</li></ul> |<ul><li>--------</li></ul> |
|12.| Lane | <ul><li>void run()</li><li>void receivePinsetterEvent(PinsetterEvent pe)</li><li>void receivePinsetterEvent(PinsetterEvent pe)</li><li>void resetScores()</li><li> void assignParty( Party theParty )</li> <li>void markScore( Bowler Cur, int frame, int ball, int score )</li> <li>LaneEvent lanePublish(  )</li><li>void publish( LaneEvent event )</li><li>Setter and Getter functions</li></ul> | <ul><li>Party party</li><li>Pinsetter setter</li> <li> HashMap scores</li> <li>Vector subscribers</li> <li>boolean gameIsHalted</li><li>boolean partyAssigned</li><li>private boolean gameFinished;</li><li>Iterator bowlerIterator</li><li>int ball</li><li>int bowlIndex</li><li>int frameNumber</li><li>boolean tenthFreameStrike</li><li>intp[ curScores</li><li>int[][] cumulScores</li><li>boolean canThrowAgain</li><li>int [][] finalScroes</li><li>int gameNumber</li><li>Bowler currentThrowe</li></ul>  |<ul><li>Simulates the bowling alley lanes in the game</li><li>Ensures cylic rounds of each bowlers turn</li> <li>assigns a party to the lane</li> <li>Keeps track and calculates bowlers score</li></ul> |<ul><li>Bowler</li><li>Party</li><li>Pinsetter</li></ul> |
|13.| LaneEvent | <ul><li>Setters and getter funcitons only</li> </ul> | <ul><li>Party p</li><li>int frame</li> <li>int ball</li> <li>Bowler bowler</li> <li>boolean mechProb</li></ul>  |<ul><li>Setter and getter functions for all lane functionalities</ul> |<ul><li>Party</li><li>Bowler</li></ul> |
|14.| LaneEventInterface | <ul><li>An interface class</li></ul> | <ul><li>------</li></ul>  |<ul><li>Interfaces the multiple classes</li></ul> |<ul><li>Party</li><li>Bowler</li></ul> |
|15.| LaneObserver | <ul><li>An interface class</li></ul> | <ul><li>------</li></ul>  |<ul><li>Interfaces the multiple classes</li></ul> |<ul><li>------</li></ul> |
|16.| LaneServer | <ul><li>An interface class</li></ul> | <ul><li>------</li></ul>  |<ul><li>Interfaces the multiple classes</li></ul> |<ul><li>-------</li></ul> |
|17.| LaneStatusView | <ul><li>LaneStatusView(Lane lane, int laneNum )</li><li>JPanel showLane()</li><li>void receiveLaneEvent(LaneEvent le)</li><li> void receivePinsetterEvent(PinsetterEvent pe)</li></ul>|<ul><li>PinSetterView psv</li><li>LaneView lv</li><li>Lane lane</li><li>int laneNum</li><li>boolean laneShowing</li><li>booleean psShowing</li></ul>|<ul><li>Rendering the GUI for the status of the lanes</li></ul> |<ul><li>PinSetterView</li><li></li><li>LaneView</li><li>Lane</li></ul> |
|18.| LaneView | <ul><li>void show()</li><li>void high()</li><li>Jframe makeFrame</li><li>void receiveLaneEvent(LaneEvent le)</li></ul> | <ul><li>int cur</li><li>int roll</li> <li>boolean initDone</li> <li>Iterator bowlIt</li> <li>Lane lane</li></ul>  |<ul><li>Render the view GUI for the alley lanes</li></ul> |<ul><li>Lane</li></ul> |
|19.| NewPatronView | <ul><li>void actionPerformed()</li><li>void valueChanged()</li><li>Vector getParty()</li><li>Vector getNames()</li><li> void updateNewPatron()</li> </ul> | <ul><li>int maxSize</li><li>boolean done</li> <li>Strinf selectedNick</li> <li>AddPartyView addParty</li> <li>String selectedMember</li></ul>  |<ul><li>Setter and Getter functions</li></ul> |<ul><li>AddPartyView</li></ul> |
|20.| Party | <ul><li>Vector getMembers()</li></ul> | <ul><li>Vecotr myBowlers</li></ul>  |<ul><li>Accessor for members belonging to a party</li></ul> |<ul><li>------</li></ul> |
|21.| Pinsetter | <ul><li>void ballThrown()</li><li>void reset()</li><li>void resetPins()</li><li>void subscribe(PinsetterObserver subscriber)</li></ul> | <ul><li>Vector subscribers</li><li>Random rnd</li> <li>boolean[] pins</li> <li>boolean foul</li> <li>int throwNumber</li></ul>  |<ul><li>Updates the state of the pins across all subscribers</li><li>Simulates a ball being thrown and probabilistically creates a result for the ballThrown() function- either as a foul or some number of pins</li> </ul> |<ul><li>PinsetterObserver</li></ul> |
|22.| PinsetterEvent | <ul><li>boolean pinsKnockedDown()</li><li>int pinsDownOnThisThrow()</li><li>int totalPinsDown()</li><li>boolean isFoulCommited()</li><li> int gerThrowNumber</li> </ul> | <ul><li>boolean[] pinsStillStanding</li><li>boolean foulCommited</li> <li>int throwNumber</li> <li>int pinsDownThisThrow</li></ul>  |<ul><li>Includes functionalities that mimic the dropping of pins and probabilistaically (or randomly) determines this</li></ul> |<ul><li>------</li></ul> |
|23.| PinsetterObserver | <ul><li>An interface class</li></ul> | <ul><li>------</li></ul>  |<ul><li>Interfaces the multiple classes</li></ul> |<ul><li>-------</li></ul> |
|24.| PinSetterView | <ul><li>void receivePinsetterEvent()</li></ul> | <ul><li>Vector pinVect</li></ul>  |<ul><li>Constructs a Pin Setter GUI displaying which roll it is</li><li>Receives the current state of the PinSetter and the method changes how the GUI looks accordingly</li></ul> |<ul><li>------</li></ul> |
|25.| PrintableText | <ul><li>int print(Graphics g, PageFormat pageFormat, int pageIndex)</li> </ul> | <ul><li>String text</li><li>int POINTS_PER_INCH</li></ul>  |<ul><li>Displays the graphical text on the UI including colour</li></ul> |<ul><li>------</li></ul> |
|26.| Queue | <ul><li>void add(Object o)</li><li>boolean hasMoreElements()</li><li>Vector asVector()</li><li>Object next()</li></ul> | <ul><li>Vector v</li></ul>  |<ul><li>Creates a new Queue</li></ul> |<ul><li>-----</li></ul> |
|27.| Score | <ul><li>constructor, getter and setter functions</li> </ul> | <ul><li>String nick</li><li>String date</li> <li>String score</li></ul>  |<ul><li>Sets the scores for the players in the game</li></ul> |<ul><li>------</li></ul> |
|28.| ScoreHistoryFile | <ul><li>Vector getScores(string nick)</li><li>void addScore()</li></ul> | <ul><li>String SCOREHISTORY_DAT</li></ul>  |<ul><li>Writes the scores of the playes into a .DAT file after a game finishes. Makes use of I/O options, reading/writing to a buffer etc</li></ul> |<ul><li>------</li></ul> |
|29.| ScoreReport | <ul><li>void sendEmail()</li><li>void sendPrintout()</li><li>void sendln()</li></ul> | <ul><li>String content</li></ul>  |<ul><li>Generates the ScoreReport and sends it via email/printout to the user.</li></ul> |<ul><li>Bowler</li></ul> |

# **VII. ANALYZING THE ORIGINAL DESIGN** :

As mentioned in the design document provided to us with the codebase, most features and functionalities are in line with the document. <br>
The only feature that has not been implemented is the system to *Email the score report to the player.*

Below is an analysis of the design of the original code : 

* **Design analysis *Within* Classes**

  * **Comments :** The given code is well commented and enables a new coder to easily understand the codebase. 

  * **Long Method:** Some files such as ```Lane.java``` have functions that and are too long and hence have high cylcomatic complexity. This is a drawback in the design.

  * **Long Parameter List:** The ```LaneEvent.java``` file has a long parameterlist, this is undersirable since it makes the code more complex.

  * **Duplicate Code:** A few functions are redundant, they perform the same tasks and hence one of the redundant methods can be removed. 

  * **Inconsisten/Uncommunicative names:** The code makes use of intuitive function names and instance declarations which makes it easier for a person reading the codebase for the first time to navigate through the different files.

  * **Conditional Complexity**: Several files such as ```Lane.java```, ```LaneEvent.java```, ```LaneStatusView.java``` etc have high conditional complexity. These can be improved upon. 

  * **Speculative generalization and Dead Code:** There exist some chunks of dead code that have been left in the codebase with the intention to complete them later. These add to the LOC, and make the code less readable. Hence it is best to remove them.

* **Design Analysis *Between* Classes**

  * **Indecent Exposure:** Classes like the ```ControlDesk``` are subject to this codesmell wherein they have functions that expose themselves to the class where they arent even required. 

  * **Feature Envy:** Certain methods from ```ControlDesk``` can be moved into other classes wherein they are more extensively used. For Eg: the Queue related functions can be moved to the ```Queue.java``` file.

  * **Primitive Obsession:** Objects and datatypes such as the ```JButton``` are written and rewritten again and again. This causes it to become extremely complex. This can be fixed by writing a class for the same.



# **VIII. CODE SMELLS**

The Bowling Management System codebase is comprised of a total of 29 files. 
*Here is a list of those files and the code smells found in them:*

|SNo.| File/Class Name | Code Smells and their instances |
|----|-----|----------------|
|1.| AddPartyView | <ol><li>Conditional Complexity<ul><li>The *void actionPerformed(ActionEvent e)* function has high conditional complexity due to the high number of action events possible within the class.</li></ul></li><li>Feature Envy:One of the functions makes extensive use of the *NewPatron* class and hence it should be moved there:<ul><li>*updateNewPatron*</li></ul></li><li>Primitive Obsession & Combinatiorial Explosion:The *JButton* objects are initialized multiple times in this class for various uses. This causes the same code to be written multiple times. Instead a class should be created for this like how it's done for queues using the *Queue* class:<ul><li>*addPatron*</li><li>*remPatron*</li><li>*newPatron*</li><li>*finished*</li></ul></li></ol>|
|2.| ControlDesk | <ol><li>Indecent Exposure<ul><li>The *subscribe* function should be moved to a different class because all the components this class expose themselves to this method despite it not needing them. This is also responsible for increased lack of cohesion in this class.</li></ul></li><li>Feature Envy:Two of the functions use the *Queue* class a lot so they should be moved there.<ul><li>*addpartyQueue*</li><li>*getpartyQueue*</li></ul></li></ol>|
|3.| ControlDeskView | <ol><li>Large Class<ul><li>This class is very long (taking about 180 lines out of the total 1500 lines of code amidst a total 29 classes).</li></ul></li><li>Primitive Obsession & Combinatiorial Explosion: The *JButton* objects are initialized multiple times in this class for various uses. This causes the same code to be written multiple times. Instead a class should be created for this:<ul><li>*addParty*</li><li>*assign*</li><li>*finished*</li></ul></li></ol>|
|4.| Lane | <ol><li>Large Class- The *Lane* class is massive(at about 600 lines) and breaking it down would make it easier to read, understand and troubleshoot.</li><li>Long Method- The following functions are very big and should be broken down:<ul><li>*run()* function</li><li>*receivePinsetterEvent(PinsetterEvent pe)* function</li><li>*getScore()* function</li></ul></li><li>Conditional Complexity- The following methods have high complexity due to a high number of chained if-else conditions:<ul><li>*getScore()* function</li><li>*receivePinsetterEvent()* function</li></ul></li><li>Dead Code<ul><li>The *strikeballs* variable is never used in the *getScore(Bowler Cur, int frame)* function and should be removed from there.</li></ul></li><li>Indecent Exposure-The *laneSubscribe* function should be removed from the class because it can be handled in a different class which would result in other data and methods in the class to not be exposed while also reducing the number of methods.</li></ol>|
|5.| LaneStatusView | <ol><li>Conditional Complexity<ul><li>The *void actionPerformed(ActionEvent e)* function has high conditional complexity due to the high number of action events possible within the class.</li></ul></li><li>Primitive Obsession & Combinatiorial Explosion: The *JButton* objects are initialized multiple times in this class for various uses. This causes the same code to be written multiple times. Instead a class should be created for this:<ul><li>*viewLane*</li><li>*viewPinSetter*</li><li>*maintenance*</li></ul></li></ol>|
|6.| LaneView | <ol><li>Long Method & Conditional Complexity-The *receiveLaneEvent(LaneEvent le)* function is very long and has high conditional complexity. It should be broken down if possible for easier understanding and debugging.</li></ul></li></ol>|
|7.| NewPatronView | <ol><li>Primitive Obsession & Combinatiorial Explosion: The *JPanel* objects are initialized multiple times in this class. This causes the same code to be written multiple times. Instead a class should be created for this:<ul><li>*nickPanel*</li><li>*fullPanel*</li><li>*emailPanel*</li></ul></li></ol>|
|8.| Score | <ol><li>Comments-Irrelevant information is provided in the code, not pertaining to the explanation or functioning of the class.</li></ul></li></ol>|

# **IX. ANALYZING THE REFACTORED DESIGN**

Our refactored design reflects a balance among competing criteria. Whilst refactoring our code, we understood that writing good quality code is to some level a bargain between these several parameters. It is essential that some metrics take a lower value however that may reflect in some other metric shooting up. Hence striking the right balance is essential. 

There were several paramters that we had taken into consideration while we refactored the code that was provided to us. Here is an indepth analysis of how we chose between the metrics to be finetuned and the steps taken to achieve the same.

* **Low Coupling**:

  We started off with a total of 29 classes (quite a huge number in iteself). As we went on to increase the functionalities and extend the project, we observed that there were more classes required. Since this was a dire requirement and couldn't be overlooked we had to ensure that the coupling between these class is low. 
  
  Low coupling is often a sign of a well-structured computer system. Hence the refactored code includes as few dependencies as possible. We trie to keep the newer classes as standalone classes (such that they arent dependent on other classes). Furthermore, the existing dependencies between classes was also lowered. A major factor that is affected when coupling becomes high is the idea of *Data Abstraction*. If classes freely access the members of other classes, then the whole point of data-hiding is lost. Hence we took care of this by shifting those functions to the classes in which they are most suitable from where they were initially placed (Eg : ```updateNewPatron``` was shifted into the ```NewPatronView``` class).

  Overall we were successfully able to ensure that on an average amongst all our classes, there exists a low value for the **Coupling metric**

* **High Cohesion** :

  Cohesion is used to indicate the degree to which a class has a single, well-focused purpose. Here we focused on how individually classes are designed. Highly cohesive classes are much easier to maintain and less frequently changed. Such classes are more usable than others as they are designed with a well-focused purpose. 

  Firstly we observed that there tends to be a higher *Lack of Cohesion* for classes with greater number of methods. This is natural since then the funcitonality of the class isn't contained in a concise manner and the code readability decreases. However here, there was another metric that was to be taken care of. In order to decrease the *Cyclomatic Complexity* we split the rather huge functions into smaller simpler chunks (downside : increased number of methods). Hence we carefully noted the values of the metric and decided which one to choose over the other. 
  
  In cases where the cyclomatic complexity was too high (most of the times), we resorted to increasing the methods. In cases where the value for cohesion was dropping below a point, we decided to get rid of some redundant functions that weren't suited in the respective classes. Detailed examples for this cam be found in the previous section about CODE SMELLS.


* **Separation of concerns**:

  The idea behind this metric is that a software system must be decomposed into parts that overlap in functionality as little as possible. The main reason for creating numerous classes is that each class can encapsulte a particular fucntionality and can be responsible for all the tasks related to that. Following OOPs principles we paid special ephasis to modularize our code using encapsulation and inheritance. 

  We used the existing classes and created new ones that inherited methods and attributes from the *Parent/God classes*.
  Furthermore, we also introduced modularity in functions of the various classes by splitting them into smaller modules and invoking them from the main function. This also helped improve our cyclomatic complexity for many functions. Eg: ```actionsPerformed``` function in the ```AddPartyView``` class. 

* **Information hiding**

  Data Abstraction or Information hidign is one of the key techniques in OOPs. Most of the attributes in the existing classes were already private members of the class. Continuing this, we ensured that we incorporate attributes with the private visability in most cases. We used public variables only when requried. Lastly, all the functions we declared were public to ensure that they can be accessed by the objects of the class.

* **The Law of Demeter**

  Demeter's Law is an extension of the principle of low coupling and high cohesion. We ensured that this law is obeyed by making fewer classes interact with each other and allowing classes to interact with objects of only immidiate neighbour classes (based on Demeter's rule)

* **Extensibility**
  
  Since we ensured low coupling, we made sure that it was easier to introduce new modules, for example a new implementation for an existing interface. The new features that we were asked to implement were easily done without needing to change much code from existing files.
  As a consequence of high cohesion, it was easier to implement new modules without being concerned with aspects that are not directly related to their functionality. For eg: the module to search the db was easily implemented.

* **Reusability** 

  On inspecting the code we noticed that some features are similar to the new features that are expected to be implemented. For Eg: the *pause/resume* feature is much like the maintenance call feature that causes the Lane to freeze until a button is clicked. Reusing this code and the methods associated with it, we recreated the new functionality. 




# **X. METRIC ANALYSIS**

The following questions have been answered in this section : 

1. What were the metrics for the codebase? What did these initial measurements tell you about the system?
2. How did you use these measurements to guide your refactoring?
3. How did your refactoring affect the metrics? Did your refactoring improve the metrics? In all areas? In some areas? What contributed to these results?

--------------------------

### 1. McCabe Cyclomatic Complexity

#### 1.1  Measurements tell about the system
* Indicate the complexity of a program.
* Quantitative measure of the number of linearly independent paths through a program's source code.
* Our maximum cyclomatic complexity was set to 10.
* Functions that violated our constrain are (along with their respective cyclomatic complexity):
	1. Lane.java(Lane)
		1. getScore - 38
		2. run - 19
		3. receivePinsetterEvent - 12
	2. LaneView.java(LaneView)
		1. receiveLaneEvent - 19
	3. LaneStatusView.java(LaneStatusView)
		1. actionPerformed - 11
	4. AddPartyView.java(AddPartyView)
		1. actionPerformed - 11
* Inference: the highest cyclometic complexity is of `getScore()` method in `Lane.java` and has a big scope of reduced complexity.

#### 1.2 Guide our refactoring
* Split up method into simpler components.
* Complex loops can be made into separate functions.
* Complex conditional branches can be made into separate functions.
* Use smaller methods.

#### 1.3  Refactoring affect metrics
![uml-class-1](/home/mallika/Pictures/imgs/complane.png)
![uml-class-1](/home/mallika/Pictures/imgs/complaneview.png)
![uml-class-1](/home/mallika/Pictures/imgs/complanesview.png)
![uml-class-1](/home/mallika/Pictures/imgs/compapv.png)


--------------------------

### 2. Number of parameters

#### 2.1  Measurements tell about the system
* Our maximum number of parameters was set to 5.
* It should be kept low for simpler understanding of code.
* Functions that violated our constrain are (along with their respective number of parameters):
	1. LaneEvent.java(LaneEvent)
		1. LaneEvent - 9
* Inference: There was just one method that had higher than 5 parameters.  

#### 2.2 Guide our refactoring
* We can pass an object instead of high number of parameters.
* We can split the method if the number of parameters can be partioned well with the resulting methods.

--------------------------

### 3. Nested block depth

#### 3.1  Measurements tell about the system
* Our maximum nested block depth was set to 5.
* It should be kept low for simpler understanding of code.
* Functions that violated our constrain are (along with their respective  nested block depth):
	1. Lane.java(Lane)
		1. run - 7
		2. getScore - 7
* Inference: Lane class has methods which violate our constrain and needs improvement.  

#### 3.2 Guide our refactoring
* Split up method into simpler components.
* Put deeper blocks into meaningful functions.

#### 3.3  Refactoring affect metrics
![uml-class-1](/home/mallika/Pictures/imgs/lanenes.png)


--------------------------

### 4. Lack of Cohesion of Methods

#### 4.1  Measurements tell about the system
* Our limit for LCOM was set to 0.85
* It should be kept low, although it can go high for other reasons too like using getters and setters in java.
* Classes that violated our constrain are (along with their respective LCOM):
	1. LaneEvent - 0.91
	2. NewPatronView - 0.894
	3. LaneView - 0.88
	4. Lane - 0.851
* Inference
	1. LaneEvent has high LCOM because of getters and dosen't need to be split.
	2. NewPatronView has high LCOM because of getters and dosen't need to be split.
	3. LaneView does nees splitting.
	4. Lane view does have one liner functoins, may need splitting.

#### 4.2 Guide our refactoring
* Split up method into simpler components.

#### 4.3  Refactoring affect metrics

![uml-class-1](/home/mallika/Pictures/imgs/lacklaneview.png)
![uml-class-1](/home/mallika/Pictures/imgs/lacknpv.png)
![uml-class-1](/home/mallika/Pictures/imgs/lacklane.png)


--------------------------

### 5. Method lines of code

#### 5.1  Measurements tell about the system
* Our limit for LCOM was set to 100.0
* It should generally not be high.
* Functions with more lines of code are more bug prone.
* Classes that violated our constrain are: None
* Inference: The code given is strong in this metric.

#### 5.2 Guide our refactoring
* The code given is strong in this metric.
* We should make sure that this is maintained after refactoring.
* If metric constrains are not met, then split funciton into smaller functions.

#### 5.3  Refactoring affect metrics

Strength of the Metric is retained.


--------------------------

### 6. Depth of inheritance tree

#### 6.1  Measurements tell about the system
* Our limit for DIT was set to 5.0
* It should typically be kept between 2 to 5.
* If there is a majority of DIT values below 2, it may represent poor exploitation of the advantages of OO design and inheritance.
* It is recommended a maximum DIT value of 5 since deeper trees constitute greater design complexity as more methods and classes are involved.
* Since the code is relatively small, no lower limit was kept since not much scope of inheritance is there in relatively small code.
* Classes that violated our constrain are: None
* Inference: The code given is strong in this metric.

#### 6.2 Guide our refactoring
* The code given is strong in this metric.
* We should make sure that this is maintained after refactoring.
* If metric constrains are not met, then split funciton into smaller functions.

#### 6.3  Refactoring affect metrics

Strength of the Metric is retained.


--------------------------

### 7. Number of methods

#### 7.1  Measurements tell about the system
* Our limit for number of methods was set to 7.
* Classes that violated our constrain are (along with their respective number):
	1. Lane - 17
	2. LaneEvent - 11
	3. ControlDesk - 11
	4. LaneEventInterface - 9
* Inference
	1. Lane has the highest number of methods per class and should be reduced
	2. LaneEvent has high number of methods due to getters and setters and hence can be ignored.
	3. Control desk has high number and should be reduced.
	4. LaneEventInterface is interface and has getters and setters and hence can be ignored.

#### 7.2 Guide our refactoring
* Split up class into simpler classes with fewer methods.
* Try to merge smaller methods if possible and not violating other metrics.
* Remove dead code and unused methods.

#### 7.3  Refactoring affect metrics

* Successful File eg : 

Decreased number of methods by 4
![uml-class-1](/home/mallika/Pictures/imgs/nomcontrol.png)

* Unsuccesfull File eg :

Increase in numeber of methods by 1
![uml-class-1](/home/mallika/Pictures/imgs/nomlane.png)


For the unsuccessful files however, we managed to better a different metric and hence gave in on the number of methods in this case.

--------------------------

### 8. Number of classes

#### 8.1  Measurements tell about the system
* Our limit for number of methods was set to 30.
* The number of classes in given package is 29.
* Classes that violated our constrain are: None
* Inference: The code given is strong in this metric.

#### 8.2 Guide our refactoring
* The code given is strong in this metric.
* We should make sure that this is maintained after refactoring.
* If metric constrains are not met, then split package into smaller packages.

#### 8.3  Refactoring affect metrics

In order to include the newer implementations and functionalities, there was no option but to include new classes. Hence this metric was compromised.
