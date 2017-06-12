/**
 * Class which displays the pictures in full screen
 * @author Beyza Salyador
 */

package Pictures;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Contacts.AddContact;


public class ShowImage extends JPanel {

	private JButton back = new JButton("Back");
	private JButton show = new JButton("Show");
	private JButton add = new JButton("ADD");
	private JPanel panel = new JPanel();
	private JLabel lab;
	private ImageIcon img;
	private ImageIcon img2;

	public ShowImage(JFrame frame, Pictures pic, AddContact addC){

		setBackground(Color.BLACK);

		initialize();

		// ADD ACTION FOR BUTTONS WITH ANONYMOUS CLASS
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				remove(lab);
				((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(), "pictures");
			}
		});

		show.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				img = pic.bigImage;
				Image image = img.getImage();
				Image newimg = image.getScaledInstance(470, 660,  java.awt.Image.SCALE_SMOOTH);
				img = new ImageIcon(newimg);
				lab = new JLabel(img);
				add(lab);
			}
		});
		
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				img2 = pic.bigImage;
				Image image = img2.getImage();
				Image newimg = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
				img2 = new ImageIcon(newimg);
				addC.getPhoto().setIcon(img2);
				((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(), "addContact");
			}
		});
	}

	/**
	 * This private method customize and add all
	 * the components we need for this class
	 * @author Beyza Salyador
	 */
	private void initialize() {
		// BUTTONS
		back.setPreferredSize(new Dimension(90,90));
		back.setBackground(Color.BLACK);
		back.setOpaque(true);
		back.setBorderPainted(false);
		back.setForeground(Color.WHITE);
		show.setPreferredSize(new Dimension(90,90));
		show.setBackground(Color.BLACK);
		show.setOpaque(true);
		show.setBorderPainted(false);
		show.setForeground(Color.WHITE);
		add.setPreferredSize(new Dimension(90,90));
		add.setBackground(Color.BLACK);
		add.setOpaque(true);
		add.setBorderPainted(false);
		add.setForeground(Color.WHITE);

		// PANEL NORTH
		panel.setBackground(Color.GRAY);
		panel.add(back);
		panel.add(show);
		panel.add(add);
		add(panel);		
	}
}
