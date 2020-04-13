import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;


public class SearchableView {

	SearchableView() {

		Frame f = new Frame("Searchable View");
		Label l = new Label("Select query:");
		Choice c = new Choice();
		TextField t = new TextField("Fill name if specific");
		Button b = new Button("Search");
		Label r = new Label("Result: ");  // Result.

		c.add("Highest score all");
		c.add("Lowest score all");
		c.add("Top player");
		c.add("Highest score specific");
		c.add("Lowest score specific");
		c.add("Last 5 score specific");

		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				String query = c.getItem(c.getSelectedIndex());
				String result;

				if (query.equals("Highest score all")) {
					try {
						result = "Result: " + SearchDatabase.giveHighestScoreAll();
					} 
					catch (Exception e) {
						result = "Error retriving highest score.";
					}
				}
				else if (query.equals("Lowest score all")) {
					try {
						result = "Result: " + SearchDatabase.giveLowestScoreAll();
					} 
					catch (Exception e) {
						result = "Error retriving lowest score.";
					}
				}
				else if (query.equals("Top player")) {
					try {
						result = "Result: " + SearchDatabase.giveTopPlayer();
					} 
					catch (Exception e) {
						result = "Error retriving top player.";
					}
				}
				else if (query.equals("Highest score specific")) {
					try {
						String playerName = t.getText();
						result = "Result: " + SearchDatabase.giveHighestScoreSpecific(playerName);
					} 
					catch (Exception e) {
						result = "Error retriving top player.";
					}
				}
				else if (query.equals("Lowest score specific")) {
					try {
						String playerName = t.getText();
						result = "Result: " + SearchDatabase.giveLowestScoreSpecific(playerName);
					} 
					catch (Exception e) {
						result = "Error retriving top player.";
					}
				}
				else if (query.equals("Last 5 score specific")) {
					try {
						String playerName = t.getText();
						result = "Result: " + SearchDatabase.giveLast5ScoreSpecific(playerName);
					} 
					catch (Exception e) {
						result = "Error retriving top player.";
					}
				}
				else {
					result = "Error in SearchableView.";
				}

				r.setText(result);
			}
		});

		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				f.dispose();
			}
		});

		f.add(l);
		f.add(c);
		f.add(t);
		f.add(b);
		f.add(r);
	
		f.setLayout(new FlowLayout());
		f.setSize(600, 300);

		f.setVisible(true);
	}

}


class SearchDatabase {


	public static String giveHighestScoreSpecific(String playerName) throws FileNotFoundException, IOException {
		File myObj = new File("SCOREHISTORY.DAT");
		Scanner myReader = new Scanner(myObj);
		
		int highScore = -1;
		String returnString = "No high score!";
		
		while (myReader.hasNextLine()) {
			String data = myReader.nextLine();
			String[] splitData = data.split("\t");
			int curScore = Integer.parseInt(splitData[2]);
			String curPlayer = splitData[0];

			if (curPlayer.equals(playerName) && highScore < curScore) {
				highScore = curScore;
				returnString = splitData[2];
			}
		}

		return returnString;
	}	


	public static String giveLowestScoreSpecific(String playerName) throws FileNotFoundException, IOException {
		File myObj = new File("SCOREHISTORY.DAT");
		Scanner myReader = new Scanner(myObj);
		
		int lowScore = 301;
		String returnString = "No low score!";
		
		while (myReader.hasNextLine()) {
			String data = myReader.nextLine();
			String[] splitData = data.split("\t");
			int curScore = Integer.parseInt(splitData[2]);
			String curPlayer = splitData[0];

			if (curPlayer.equals(playerName) && lowScore > curScore) {
				lowScore = curScore;
				returnString = splitData[2];
			}
		}

		return returnString;
	}


	public static String giveLast5ScoreSpecific(String playerName) throws FileNotFoundException, IOException {
		File myObj = new File("SCOREHISTORY.DAT");
		Scanner myReader = new Scanner(myObj);
		
		String returnString = "";

		java.util.List<String> reverseFile = new ArrayList<String>();

		while (myReader.hasNextLine()) {
			String data = myReader.nextLine();
			reverseFile.add(data);
		}

		int count = 0;
		for(int i = reverseFile.size()-1; i >= 0; i--) {
			String data = reverseFile.get(i);
			String[] splitData = data.split("\t");
			String curPlayer = splitData[0];

			if (curPlayer.equals(playerName)) {
				returnString += splitData[2] + " on " + splitData[1] + ";     ";
				count += 1;
			}

			if (count == 5) {
				break;
			}
		}
	
		if (count == 0) {
			returnString = "No scores found!";
		}

		return returnString;
	}	


	public static String giveHighestScoreAll() throws FileNotFoundException, IOException {
		File myObj = new File("SCOREHISTORY.DAT");
		Scanner myReader = new Scanner(myObj);
		
		int highScore = -1;
		String returnString = "No high score!";
		
		while (myReader.hasNextLine()) {
			String data = myReader.nextLine();
			String[] splitData = data.split("\t");
			int curScore = Integer.parseInt(splitData[2]);
			
			if (highScore < curScore) {
				highScore = curScore;
				returnString = splitData[2];
			}
		}

		return returnString;
	}


	public static String giveLowestScoreAll() throws FileNotFoundException, IOException {
		File myObj = new File("SCOREHISTORY.DAT");
		Scanner myReader = new Scanner(myObj);
		
		int lowScore = 301;
		String returnString = "No low score!";
		
		while (myReader.hasNextLine()) {
			String data = myReader.nextLine();
			String[] splitData = data.split("\t");
			int curScore = Integer.parseInt(splitData[2]);
			
			if (lowScore > curScore) {
				lowScore = curScore;
				returnString = splitData[2];
			}
		}

		return returnString;
	}


	public static String giveTopPlayer() throws FileNotFoundException, IOException {
		File myObj = new File("SCOREHISTORY.DAT");
		Scanner myReader = new Scanner(myObj);
		
		int highScore = -1;
		String returnString = "No top player!";
		
		while (myReader.hasNextLine()) {
			String data = myReader.nextLine();
			String[] splitData = data.split("\t");
			int curScore = Integer.parseInt(splitData[2]);
			
			if (highScore < curScore) {
				highScore = curScore;
				returnString = splitData[0];
			}
		}

		return returnString;
	}

}

