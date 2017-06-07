package Contacts;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;




public class AddContact extends JPanel {

	private JButton back = new JButton("Back");
	private JButton cancel = new JButton("Cancel");
	private JButton save = new JButton("Save");
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



	public AddContact(JFrame frame, Contacts cont) {

		setBackground(Color.BLACK);

		// PANEL
		panelNorth.setBackground(Color.GRAY);
		panelNorth.add(back);
		panelNorth.add(save);
		panelNorth.add(cancel);
		add(panelNorth, BorderLayout.NORTH);

		// BUTTONS
		back.setPreferredSize(new Dimension(90,90));
		back.setBackground(Color.BLACK);
		back.setOpaque(true);
		back.setBorderPainted(false);
		back.setForeground(Color.WHITE);
		cancel.setPreferredSize(new Dimension(90,90));
		cancel.setBackground(Color.BLACK);
		cancel.setOpaque(true);
		cancel.setBorderPainted(false);
		cancel.setForeground(Color.WHITE);
		save.setPreferredSize(new Dimension(90,90));
		save.setBackground(Color.BLACK);
		save.setOpaque(true);
		save.setBorderPainted(false);
		save.setForeground(Color.WHITE);
		photo.setBackground(Color.GRAY);
		photo.setOpaque(true);
		photo.setBorderPainted(false);
		photo.setForeground(Color.WHITE);
		black.setBackground(Color.BLACK);
		black.setOpaque(true);
		black.setBorderPainted(false);


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
		panelCentral.setLayout(new GridLayout(0,1,10,15));  // give panel dimensions !
		
		add(panelCentral, BorderLayout.CENTER);




		// ADD ACTION FOR BUTTONS WITH ANONYMOUS CLASS
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(), "contacts");
			}
		});


		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtLastName.setText("");
				txtName.setText("");
				txtNumber.setText("");
				((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(), "contacts");
			}
		});


		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// ADD CONTACT IN THE JLIST
//					list.add(txtLastName.getText().toUpperCase()+":" + txtName.getText()+":" + txtNumber.getText());
				
				// ADD CONTACT IN THE TXT FILE
				File contact = new File("Contacts.txt");

				try{

					// WRITE TO "CONTACTS.TXT" WITHOUT OVERWRITING, WE HAVE TO ADD "TRUE"
					FileWriter fw = new FileWriter(contact, true);
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write( "\n" + txtLastName.getText().toUpperCase()+":" + txtName.getText()+":" + txtNumber.getText() );
					bw.close();
					
//					cont.getNewListContact();
					
				} catch(IOException e1) {
					e1.printStackTrace();
				}
				
				((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(), "contacts");
				
				cont.validate();
				cont.repaint();
			}
		});
	}
}
