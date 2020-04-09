# **NEW FEATURES IMPLEMENTED:**


## **OVERVIEW OF THE FEATURES IMPLEMENTED**

### ***FEATURE 1:* Making code extensible for a Maximum of 6 players :**

This is a minute change in the code. Since the code is already functional for a ```maxPatronsPerParty``` size of **5** players by default, all that was to be done in order to implement this feature was to modify this value from 5 to 6. 

The file to be modified was ```drive.java```. Here the class ```drive``` was modified.

Below is the snippet of code that was modified : 

```java
public class drive {

	public static void main(String[] args) {

		int numLanes = 3;
		int maxPatronsPerParty=6;

		Alley a = new Alley( numLanes );
		ControlDesk controlDesk = a.getControlDesk();

		ControlDeskView cdv = new ControlDeskView( controlDesk, maxPatronsPerParty);
		controlDesk.subscribe( cdv );

	}
}
```

### ***FEATURE 2:* Searching the Database :**

This feature has the functionality of enabling the users to make *ad-hoc* queries on the database. 

The logic we have used to implement this is :

* Have 2 classes whereby we do not have any *Primitive obsession* code smell for our code. 
  One that works with the UI for this feature and the other works with querying the .DAT file.

* In order to query on the database we have provide a *Dropdown* menu option that allows the user to select one among the many queries. 

* Custom queries - searching for the highest/lowest score of a particular player can also be done using the input text-box provided. 

* We have 6 functions for the drop down menu. The below code shows the function prototype snippets of the same : 

```java
    public static String giveHighestScoreSpecific(String playerName) ;

	public static String giveLowestScoreSpecific(String playerName) ;

	public static String giveLast5ScoreSpecific(String playerName);

	public static String giveHighestScoreAll() ;

	public static String giveLowestScoreAll() ;

	public static String giveTopPlayer() ;
```

* Error handling is also done.

### ***FEATURE 3* Pause and Resume :**

* This feature allows a party to pause their game for later.

* A UI button against each lane has been created that allows a user to pause an existing game in a lane

* The lane will remain frozen for that party until the party resumes their game and the game finishes. 

* The text on the UI button toggles between *PAUSE* and *RESUME*

* Here are the snippets of the code reused from the existing codebase : 

```java
	/**
	 * Pause the execution of this game
	 */
	public void pauseGame() {
		gameIsHalted = true;
		laneSubscribe.publish(lanePublish());
	}

	/**
	 * Resume the execution of this game
	 */
	public void unPauseGame() {
		gameIsHalted = false;
		laneSubscribe.publish(lanePublish());
```


### ***FEATURE 4* Saving an Existing Party's Game :**

* Here we have created another UI button that allows the user to save the current status of an ongoing game in a particular lane. 

* It is essentially storing a snapshot of the events of that lane into a file. 




## **UML AND SEQ DIAGRAMS FOR NEW FEATURES:**


### ***FEATURE 1:* Making code extensible for a Maximum of 6 players :**

![umL-seq-2](/home/mallika/Desktop/DASS/Assn/A3/Bowling-Management-System/Feature1_Seq_diagram.jpg)


### ***FEATURE 2:* Searching the Database :**

![umL-seq-2](/home/mallika/Desktop/DASS/Assn/A3/Bowling-Management-System/db-search.png)
![umL-seq-2](/home/mallika/Desktop/DASS/Assn/A3/Bowling-Management-System/Feature2_Seq_diagram.jpg)

### ***FEATURE 3:* Pause and Resume :**

![umL-seq-2](/home/mallika/Desktop/DASS/Assn/A3/Bowling-Management-System/pause-play.png)






