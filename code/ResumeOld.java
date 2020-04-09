import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;


public class ResumeOld {

	ResumeOld() {

		Frame f = new Frame("Resume Old");
		Label l = new Label("Enter game name:");
		TextField t = new TextField("Write game name here");
		Button b = new Button("Enter");
		
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				String gameName = t.getText();
				System.out.println(gameName);
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

