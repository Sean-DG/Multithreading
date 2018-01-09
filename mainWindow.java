import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.StringTokenizer;


public class mainWindow extends JFrame{
	/**
	 * Creates the main window and asks user to enter file names
	 */
	public void makeMainWindow() {
		JFrame frame = new JFrame("File Word and Letter Counter");
		frame.setSize(350, 300);
		
		JLabel label = new JLabel("Enter File Names", SwingConstants.CENTER);
		label.setPreferredSize(new Dimension(0,30));
		label.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		
		JButton submit = new JButton("Submit");
		submit.setPreferredSize(new Dimension(20,20));
		JButton choose = new JButton("Open a File...");
		choose.setPreferredSize(new Dimension(20,20));
		
		
		text.setBorder(BorderFactory.createLineBorder(Color.black));
		text.setLineWrap(true);
		text.setWrapStyleWord(true);
		
		JScrollPane scroll = new JScrollPane(text);
		JPanel mPanel = new JPanel();
		mPanel.setLayout(new GridLayout(1,0));
		
		JPanel bPanel = new JPanel();
		bPanel.setLayout(new GridLayout(1,2,5,5));
		
		
		frame.add(label, BorderLayout.NORTH);
		mPanel.add(scroll);
		frame.add(mPanel);
		bPanel.add(choose);
		bPanel.add(submit);
		frame.add(bPanel, BorderLayout.SOUTH);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// uses a file chooser to aide the user in selecting the desired files
		choose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				JFileChooser chooser = new JFileChooser();
				chooser.setMultiSelectionEnabled(true);
				chooser.setCurrentDirectory(new java.io.File("."));
				chooser.showOpenDialog(null);
				File[] files = chooser.getSelectedFiles();
				if(text.getText().equals("Please enter a file name")){text.setText("");}
				for(File s: files) {
					text.append(s.getName() + "\n");
				}	
			}
		});
		//submit file choices to be processed
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				for(String line : text.getText().split("\\n")) {
					if(line.equals("")) {
						text.append("Please enter a file name");
					}
					else {
						Runnable r = new processFile(line);
						Thread t = new Thread(r);
						t.start();
					}
				}	
			}
		});
		
}
	/**
	 * Returns the strings in textArea
	 * @return string
	 */
	public String getText(){
			return text.getText();
	}
	
	final JTextArea text = new JTextArea();
}
