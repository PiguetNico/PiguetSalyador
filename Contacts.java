package Contacts;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;



public class Contacts extends JPanel {

	private JButton back = new JButton("Back");
	private JButton add = new JButton("Add");
	private JButton modify = new JButton("Modify");
	private JButton delete = new JButton("Delete");
	private JPanel panel = new JPanel();


	private Vector<String> list = new Vector<String>();
	//	private String line;
	//	File contacts = new File("Contacts.txt");
	//	FileReader fr = new FileReader(contacts);
	//	BufferedReader br = new BufferedReader(fr);


	public Contacts(JFrame frame) throws IOException{

		setBackground(Color.BLACK);

		// BUTTONS
		back.setPreferredSize(new Dimension(90,90));
		back.setBackground(Color.BLACK);
		back.setOpaque(true);
		back.setBorderPainted(false);
		back.setForeground(Color.WHITE);
		add.setPreferredSize(new Dimension(90,90));
		add.setBackground(Color.BLACK);
		add.setOpaque(true);
		add.setBorderPainted(false);
		add.setForeground(Color.WHITE);
		modify.setPreferredSize(new Dimension(90,90));
		modify.setBackground(Color.BLACK);
		modify.setOpaque(true);
		modify.setBorderPainted(false);
		modify.setForeground(Color.WHITE);
		delete.setPreferredSize(new Dimension(90,90));
		delete.setBackground(Color.BLACK);
		delete.setOpaque(true);
		delete.setBorderPainted(false);
		delete.setForeground(Color.WHITE);


		// PANEL
		panel.setBackground(Color.GRAY);
		panel.add(back);
		panel.add(add);
		panel.add(modify);
		panel.add(delete);
		add(panel);


		// LIST CONTACTS
		//		while ((line = br.readLine()) != null) {
		//			list.add(line);
		//		}

		getNewListContact();
		JList jlist = new JList(list);
		jlist.setFont(new Font("Arial",Font.BOLD,25));
		jlist.setForeground(Color.WHITE);
		jlist.setBackground(Color.BLACK);
		jlist.setFixedCellHeight(44);
		JScrollPane scroll = new JScrollPane (jlist);
		scroll.setPreferredSize(new Dimension(450, 650));
		add(scroll);




		// ADD ACTION FOR BUTTONS WITH ANONYMOUS CLASS
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(), "home");
			}
		});


		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(), "addContact");
			}
		});


		modify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(), "modifyContact");
			}
		});


		delete.addActionListener (new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// DELETE CONTACT IN THE JLIST
				int index = jlist.getSelectedIndex();
				if (index != 0){
					list.remove(index);
				}

				// DELETE CONTACT IN THE TXT FILE
				try {
					File inputFile = new File("Contacts.txt");
					File tempFile = new File("ContactsTemp.txt");

					BufferedReader br = new BufferedReader(new FileReader(inputFile));
					BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));

					String lineToRemove = jlist.getSelectedValue().toString();
					System.out.println(jlist.getSelectedValue());
					System.out.println(lineToRemove);
					String currentLine;

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
					tempFile.renameTo(inputFile);

					br.close();
				}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null, "FAILED TO DELETE CONTACT !!! ");
					e1.printStackTrace();
				}
			}
		});
	}


	public void getNewListContact() throws IOException{
		String line="";
		File contacts = new File("Contacts.txt");
		FileReader fr = new FileReader(contacts);
		BufferedReader br = new BufferedReader(fr);

		while ((line = br.readLine()) != null) {
//			String[] listWithSpace = line.split(":");
//			list.add(listWithSpace[0] + "  " + listWithSpace[1] + "    " + listWithSpace[2]);
			list.add(line);
		}

		fr.close();	
	}






	//	class MaListe {
	//		private String name;
	//		private String lastName;
	//		private String number;
	//
	//
	//		public MaListe(String n, String ln, String numb) {
	//			this.name = n;
	//			this.lastName = ln;
	//			this.number=numb;
	//
	//		}
	//		public String toString() {
	//			return this.lastName.toUpperCase() + "  " + this.name + " : " + this.number;
	//		}
	//	}
}
