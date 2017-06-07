package Notes;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class Notes extends JPanel {
	private String storeAllNotes="";
	private JButton save = new JButton("Save");
	private JButton back = new JButton("Back");
	private JTextArea textArea = new JTextArea(27,25);


	private JPanel panel = new JPanel();
	private JPanel panelNorth = new JPanel();
	private JScrollPane scrollBarForTextArea=new JScrollPane(textArea);


	public Notes(JFrame frame){

		setBackground(Color.BLACK);
		
		// CONFIGURE TEXTAREA TO AVOID HORIZONTAL SCROLL
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setFont(new Font("Arial",Font.BOLD,20));
		textArea.setBackground(Color.GRAY);
		textArea.setForeground(Color.WHITE);

		// BUTTONS
		back.setPreferredSize(new Dimension(90,90));
		back.setBackground(Color.BLACK);
		back.setOpaque(true);
		back.setBorderPainted(false);
		back.setForeground(Color.WHITE);	
		save.setPreferredSize(new Dimension(90,90));
		save.setBackground(Color.BLACK);
		save.setOpaque(true);
		save.setBorderPainted(false);
		save.setForeground(Color.WHITE);
		
		// PANELS
		panelNorth.setBackground(Color.GRAY);
		panelNorth.add(back);
		panelNorth.add(save);
		add(panelNorth, BorderLayout.NORTH);

		panel.add(scrollBarForTextArea); 
		add(panel);
		
		// READ NOTES.TXT
		try {    
			FileReader fr = new FileReader("Notes.txt");
			Scanner scan = new Scanner(fr);
			while(scan.hasNextLine()){
				String temp=scan.nextLine()+"\n";
				storeAllNotes+=temp;
			}
			textArea.setText(storeAllNotes);
			scan.close();
		}
		catch (Exception exception) {
			exception.printStackTrace();
		}

		
		// ADD ACTION FOR BUTTONS WITH ANONYMOUS CLASS
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(), "home");
			}
		});
		
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File f = null;
				FileWriter fw=null;

				try {
					f = new File("Notes.txt");
					fw = new FileWriter(f);     
					fw.write(textArea.getText());
					fw.close();
					
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(), "home");
			}
		});
	}
}
