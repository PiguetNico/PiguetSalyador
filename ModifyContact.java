package Contacts;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ModifyContact extends JPanel {

	private JButton back = new JButton("Back");
	private JButton save = new JButton("Save");
	private JButton show = new JButton("Show");
	private JButton black = new JButton();
	private JPanel panelNorth = new JPanel();


	private JButton photo = new JButton("PHOTO");
	private JLabel lastName = new JLabel("Lastname");
	private JLabel name = new JLabel("Firstname");
	private JLabel number = new JLabel("Phone number");
	private JTextField txtLastName = new JTextField(25);
	private JTextField txtName = new JTextField(25);
	private JTextField txtNumber = new JTextField(25);
	private JPanel panelCentral = new JPanel();
	private Font font = new Font("Arial",Font.BOLD,20);

	private String currentLine;
	private String lineToRemove;
	

	public ModifyContact (JFrame frame, Contacts cont) {

		setBackground(Color.BLACK);

		initialize();
		
		// ADD ACTION FOR BUTTONS WITH ANONYMOUS CLASS
		show.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lineToRemove = cont.lineSelectedToModify;
				if(cont.lineSelectedToModify != null){

					// GET STRING SPLITED OF THE CONTACT SELECTED
					String contactParts[] = cont.lineSelectedToModify.split(":");

					txtLastName.setText(contactParts[0]);
					txtName.setText(contactParts[1]);
					txtNumber.setText(contactParts[2]);
				}
			}
		});
		
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtLastName.setText("");
				txtName.setText("");
				txtNumber.setText("");
				((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(), "contacts");
			}
		});

		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {


				deleteCont();
				// DELETE CONTACT IN THE JLIST
				int index = cont.jlist.getSelectedIndex();
				if (index != -1){
					cont.list.remove(index);
				}

				saveCont();
				// ADD CONTACT IN THE JLIST
				cont.list.add(txtLastName.getText().toUpperCase()+" : " + txtName.getText()+" : " + txtNumber.getText());

				// METHODS TO REFRESH THE JLIST
				Collections.sort(cont.list);
				cont.jlist.setListData(cont.list);
				cont.jlist.setSelectedIndex(0);

				
				// RESET FIELDS
				txtLastName.setText("");
				txtName.setText("");
				txtNumber.setText("");

				((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(), "contacts");
			}
		});
	}



	private void initialize() {
		// PANEL
		panelNorth.setBackground(Color.GRAY);
		panelNorth.add(back);
		panelNorth.add(save);
		panelNorth.add(show);
		add(panelNorth, BorderLayout.NORTH);

		// BUTTONS
		back.setPreferredSize(new Dimension(90,90));
		back.setBackground(Color.BLACK);
		back.setOpaque(true);
		back.setBorderPainted(false);
		back.setForeground(Color.WHITE);
		black.setBackground(Color.BLACK);
		black.setOpaque(true);
		black.setBorderPainted(false);
		save.setPreferredSize(new Dimension(90,90));
		save.setBackground(Color.BLACK);
		save.setOpaque(true);
		save.setBorderPainted(false);
		save.setForeground(Color.WHITE);
		show.setPreferredSize(new Dimension(90,90));
		show.setBackground(Color.BLACK);
		show.setOpaque(true);
		show.setBorderPainted(false);
		show.setForeground(Color.WHITE);

		// CONFIGURE ADD CONTACT
		panelCentral.setBackground(Color.BLACK);
		lastName.setForeground(Color.WHITE);
		lastName.setFont(font);
		txtLastName.setFont(font);
		name.setForeground(Color.WHITE);
		name.setFont(font);
		txtName.setFont(font);
		number.setForeground(Color.WHITE);
		number.setFont(font);
		txtNumber.setFont(font);
		panelCentral.add(black);
		panelCentral.add(photo);
		panelCentral.add(lastName);
		panelCentral.add(txtLastName);
		panelCentral.add(name);
		panelCentral.add(txtName);
		panelCentral.add(number);
		panelCentral.add(txtNumber);
		panelCentral.setLayout(new GridLayout(0, 1, 10, 15));  // give panel dimensions !
		add(panelCentral, BorderLayout.SOUTH);
	}


	// ADD CONTACT IN THE TXT FILE
	public void saveCont() {
		
		File contact = new File("Contacts.txt");

		try{

			// WRITE TO "CONTACTS.TXT" WITHOUT OVERWRITING, WE HAVE TO ADD "TRUE"
			FileWriter fw = new FileWriter(contact, true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write( "\n" + txtLastName.getText().toUpperCase()+" : " + txtName.getText()+" : " + txtNumber.getText() );
			bw.close();

		} catch(IOException e1) {
			e1.printStackTrace();
		}	
	}

	// DELETE CONTACT IN THE TXT FILE
	public void deleteCont(){
		try {
			File inputFile = new File("Contacts.txt");
			File tempFile = new File("ContactsTemp.txt");

			BufferedReader br = new BufferedReader(new FileReader(inputFile));
			BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));

			int i = 0;
			while((currentLine = br.readLine()) != null) {
				String trimmedLine = currentLine.trim();
				if(!trimmedLine.contains(lineToRemove)) { //startswith()
					if(i == 0){
						bw.write(currentLine); 
					}
					else{
						bw.write("\n" + currentLine); 
					}
					i++;
				}
			}  

			bw.close();

			if(!inputFile.delete())
			{
				JOptionPane.showMessageDialog(null, "IMPOSSIBLE TO RENAME TEMPFILE");
				return;
			}
			if(!tempFile.renameTo(inputFile))
				JOptionPane.showMessageDialog(null, "IMPOSSIBLE TO RENAME TEMPFILE");


			br.close();
			tempFile.renameTo(inputFile);	
		}

		catch(Exception e1) {
			JOptionPane.showMessageDialog(null, "FAILED TO DELETE CONTACT !!! ");
			e1.printStackTrace();
		}
	}
}
