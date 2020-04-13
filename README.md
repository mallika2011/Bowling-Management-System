# **BOWLING MANAGEMENT SYSTEM** :


## **Brief Overview** :

The Bowling Management System is a game that is entirely developed in **Java**. It is a virtual game that enables players to enjoy the fun of bowling from their laptops. 

The code has been refactored to incorporate several **Design Patterns** and coding techniques that are critical and essential for readability and conciseness of code. Refactoring the code is essential in otder to enable multiple developers to easily understand the codebase and contribute to the same. We have made use of the [*Metric2-Tool*](http://metrics2.sourceforge.net/) in the Eclipse IDE to detect potential code smells and rectify them. 

The code smells that were identified included code smells within the classes as well as those between classes. Some examples of code smells are :

* Conditional Complexity 

* Feature Envy

* Indecent Exposure

* Large Class

* Dead Code

* Primitive Obsession & Combinatiorial Explosion

> A more indepth analysis of the design can be found in the design document PDF in the repository.

## **Features** :

The game simulates several features that add to the overall appeal of the game. Some of the significant features included in the game are : 

* Control Desk : The control desk operator has the ability to monitor the scores of any active lane. A configurable display option will allow the operator to view the score of an individual scoring station or multiple scoring stations.

* Creating a new player : A *NewPatron* can be created to play the game. This player is then added to the Bolwers database file. The new player will then be eligable to join a party and play a round of the game. 

* Adding a new party  : Selected number of bowlers can be added to a party which is then assigned to one of the free lanes to begin playing a game. In case all the lanes are occupied - the party is then added to the *Queue* that keeps track of the parties that are registered but yet to play. 

* Viewing the Pinsetter : For a particular lane, the user can also view the pinsetter window which simulates the pins dropped on each ball-throw. The pinsetter will re-rack the pins (places all ten down) after two consecutive throws have been detected. 

* Viewing the Scoreboard : The scoreboard keeps track of the score gained by each player in the party after their respective turns. It uses the normal score calculation technique as used in a regular game of bowling - ie for a spare : score = 10 + pins dropped on next ball and for a strike : score = 10 + pins dropped on next 2 balls.

* Maintenance Call : This is essentially a simulation of some repair work  – ball not returned, pinsetter did not re-rack, etc.– that is to take place for a particular lane. The game play is halted for the time the lane is being repaired. 

