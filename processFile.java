import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;
import javax.swing.*;
import javax.swing.border.EtchedBorder;

/**
 * 
 * Once a user has entered file names and hit the submit button this class
 * starts processing the file and displays the output
 *
 */
public class processFile extends JFrame implements Runnable{
	public processFile(String name) {
		fileName = name;
	}
	public void run() {
		JFrame frame = new JFrame(fileName);
		frame.setSize(800, 800);
		
		JTextArea text = new JTextArea();
		text.setBorder(BorderFactory.createLineBorder(Color.black));
		text.setLineWrap(true);
		text.setWrapStyleWord(true);
		
		JScrollPane scroll = new JScrollPane(text);
		JPanel mPanel = new JPanel();
		mPanel.setLayout(new GridLayout(1,0));
		
		File file = new File(fileName);
		
		try{
			Scanner scan = new Scanner(file);
			while(scan.hasNextLine()) {
				String thisLine = scan.nextLine();
				for(int i = 0; i < thisLine.length(); i++) {
					if((thisLine.charAt(i) >= 'A' && thisLine.charAt(i) <= 'Z') || 
							(thisLine.charAt(i) >= 'a' && thisLine.charAt(i) <= 'z')) {
						letterCount++;
					}
				}
				wordCount += new StringTokenizer(thisLine, " ,.").countTokens();
				text.append(thisLine + "\n");
			}	
		
		
			JLabel label = new JLabel("Words: " + wordCount + " Letters: " + letterCount, SwingConstants.CENTER);
			label.setPreferredSize(new Dimension(0,30));
			label.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		
			frame.add(label, BorderLayout.NORTH);
			mPanel.add(scroll);
			frame.add(mPanel);
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		}
		catch(FileNotFoundException e){
			text.setText("File not found!");
			e.printStackTrace();
		}
	}
	private int letterCount = 0;
	private int wordCount = 0;
	private String fileName;
}

	
		
	


