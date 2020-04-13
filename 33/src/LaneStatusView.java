/**
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import java.util.*;

public class LaneStatusView implements ActionListener, LaneObserver, PinsetterObserver {

	private JPanel jp;

	private JLabel curBowler, foul, pinsDown;
	private JButton viewLane;
	private JButton viewPinSetter, maintenance, pause, saveGame;

	private PinSetterView psv;
	private LaneView lv;
	private Lane lane;
	int laneNum;
	private boolean paused=false;

	boolean laneShowing;
	boolean psShowing;

	public String filename;

	public LaneStatusView(Lane lane, int laneNum ) {

		this.lane = lane;
		this.laneNum = laneNum;

		laneShowing=false;
		psShowing=false;

		psv = new PinSetterView( laneNum );
		Pinsetter ps = lane.getPinsetter();
		ps.subscribe(psv);

		lv = new LaneView( lane, laneNum );
		lane.laneSubscribe.subscribe(lv);


		jp = new JPanel();
		jp.setLayout(new FlowLayout());
		JLabel cLabel = new JLabel( "Now Bowling: " );
		curBowler = new JLabel( "(no one)" );
		JLabel fLabel = new JLabel( "Foul: " );
		foul = new JLabel( " " );
		JLabel pdLabel = new JLabel( "Pins Down: " );
		pinsDown = new JLabel( "0" );

		// Button Panel
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());

		Insets buttonMargin = new Insets(4, 4, 4, 4);

		viewLane = new JButton("View Lane");
		JPanel viewLanePanel = new JPanel();
		viewLanePanel.setLayout(new FlowLayout());
		viewLane.addActionListener(this);
		viewLanePanel.add(viewLane);

		viewPinSetter = new JButton("Pinsetter");
		JPanel viewPinSetterPanel = new JPanel();
		viewPinSetterPanel.setLayout(new FlowLayout());
		viewPinSetter.addActionListener(this);
		viewPinSetterPanel.add(viewPinSetter);

		pause = new JButton("Pause");
		JPanel pausePanel = new JPanel();
		pausePanel.setLayout(new FlowLayout());
		pause.addActionListener(this);
		pausePanel.add(pause);

		saveGame = new JButton("saveGame");
		JPanel saveGamePanel = new JPanel();
		saveGamePanel.setLayout(new FlowLayout());
		saveGame.addActionListener(this);
		saveGamePanel.add(saveGame);

		maintenance = new JButton("     ");
		maintenance.setBackground( Color.GREEN );
		JPanel maintenancePanel = new JPanel();
		maintenancePanel.setLayout(new FlowLayout());
		maintenance.addActionListener(this);
		maintenancePanel.add(maintenance);

		viewLane.setEnabled( false );
		viewPinSetter.setEnabled( false );
		pause.setEnabled( false );
		saveGame.setEnabled(false);


		buttonPanel.add(viewLanePanel);
		buttonPanel.add(viewPinSetterPanel);
		buttonPanel.add(pausePanel);
		buttonPanel.add(saveGamePanel);
		buttonPanel.add(maintenancePanel);

		jp.add( cLabel );
		jp.add( curBowler );
//		jp.add( fLabel );
//		jp.add( foul );
		jp.add( pdLabel );
		jp.add( pinsDown );

		jp.add(buttonPanel);

	}

	public JPanel showLane() {
		return jp;
	}
	
	public void viewPinsetterAction() {
		if ( psShowing == false ) {
			psv.show();
			psShowing=true;
		} else if ( psShowing == true ) {
			psv.hide();
			psShowing=false;
		}
	}
	public void actionPerformed( ActionEvent e ) {
		if ( lane.isPartyAssigned() ) {
			if (e.getSource().equals(viewPinSetter)) {
				viewPinsetterAction();
			}
			if (e.getSource().equals(viewLane)) {
				if ( laneShowing ) {
					lv.hide();
					laneShowing=false;
				}
				else {
					lv.show();
					laneShowing=true;
				}
			}
			if (e.getSource().equals(pause)) {
				if (paused) {
					paused = false;
					pause.setText("Pause");
					lane.unPauseGame();
				}
				else {
					paused = true;
					pause.setText("Resume");
					lane.pauseGame();
				}
			}
			if (e.getSource().equals(saveGame)) {
				if (paused) {
					TakeGameName tkn = new TakeGameName(this);

					if (this.filename == null) {
						Random rand = new Random();
						this.filename = "Game" + String.valueOf(rand.nextInt(1000));
					}

					System.out.println(this.filename);
					lane.saveAndQuit(this.filename);
				}
			}

			if (e.getSource().equals(maintenance)) {
				lane.unPauseGame();
				maintenance.setBackground( Color.GREEN );
			}
		}
	}

	public void receiveLaneEvent(LaneEvent le) {
		curBowler.setText( ( (Bowler)le.getBowler()).getNickName() );
		if ( le.isMechanicalProblem() && !paused) {
			maintenance.setBackground( Color.RED );
		}
		if ( lane.isPartyAssigned() == false ) {
			viewLane.setEnabled( false );
			viewPinSetter.setEnabled( false );
			pause.setEnabled( false );
			saveGame.setEnabled( false );
		} else {
			viewLane.setEnabled( true );
			viewPinSetter.setEnabled( true );
			pause.setEnabled( true );
			saveGame.setEnabled ( true );
		}
	}

	public void receivePinsetterEvent(PinsetterEvent pe) {
		pinsDown.setText( ( new Integer(pe.totalPinsDown()) ).toString() );
//		foul.setText( ( new Boolean(pe.isFoulCommited()) ).toString() );

	}

}


class TakeGameName {
	TakeGameName(LaneStatusView lsv) {

		Frame f = new Frame("Enter game name");
		Label l = new Label("Enter game save name:");
		TextField t = new TextField("Enter here");
		Button b = new Button("Enter");
		
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				lsv.filename = t.getText();
			}
		});

		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				f.dispose();
			}
		});

		f.add(l);
		f.add(t);
		f.add(b);
	
		f.setLayout(new FlowLayout());
		f.setSize(300, 300);

		f.setVisible(true);
	}

}
