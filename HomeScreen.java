package HomeScreen;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.*;

import Calculator.Calculator;
import Contacts.Contacts;





public class HomeScreen extends JPanel {

	// STUFF FOR THE DOCK
	private JPanel dock = new JPanel();
	private JButton calculator = new JButton("Calculator") ;
	private JButton contacts = new JButton("Contacts") ;
	private JButton pictures = new JButton("Pictures") ;
	private JButton notes = new JButton("Notes");
	private Font fontForButton = new Font("Arial", Font.BOLD, 11 );

	// DATE AND TIME
	private Font fontForTime = new Font("Arial",Font.BOLD, 45);
	private JLabel clock = new JLabel();
	
	// WALLPAPER
	private ImageIcon pic = new ImageIcon("Wallpaper.jpg");
    



	public HomeScreen(JFrame frame, Contacts cont){
	
		setBackground(Color.BLACK);
		
		// BUTTONS
		calculator.setBackground(Color.BLACK);
		calculator.setOpaque(true);
		calculator.setBorderPainted(false);
		calculator.setForeground(Color.WHITE);
		calculator.setPreferredSize(new Dimension(100,100));
		calculator.setFont(fontForButton);

		contacts.setBackground(Color.BLACK);
		contacts.setOpaque(true);
		contacts.setBorderPainted(false);
		contacts.setForeground(Color.WHITE);
		contacts.setPreferredSize(new Dimension(100,100));
		contacts.setFont(fontForButton);

		pictures.setBackground(Color.BLACK);
		pictures.setOpaque(true);
		pictures.setBorderPainted(false);
		pictures.setForeground(Color.WHITE);
		pictures.setPreferredSize(new Dimension(100,100));
		pictures.setFont(fontForButton);

		notes.setBackground(Color.BLACK);
		notes.setOpaque(true);
		notes.setBorderPainted(false);
		notes.setForeground(Color.WHITE);
		notes.setPreferredSize(new Dimension(100,100));
		notes.setFont(fontForButton);



		// DATE AND TIME
		clock.setFont(fontForTime);
		clock.setForeground(Color.WHITE);
		clock.setBackground(Color.BLACK);
		clock.setOpaque(true);
		add(clock);

		
		
		
		// DOCK
		dock.setBackground(Color.GRAY);
		dock.setOpaque(true);
		dock.add(contacts);
		dock.add(pictures);
		dock.add(calculator);
		dock.add(notes);
		add(dock);

		
		// WALLPAPER
		add(new JLabel(pic));
		
		
		
		// ADD ACTION FOR BUTTONS WITH ANONYMOUS CLASS
		contacts.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            	((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(), "contacts");
            }
        });
		
		
		calculator.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            	((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(), "calculator");
            }
        });
		
		
		notes.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            	((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(), "notes");
            }
        });

		

		// ANONYMOUS CLASS TO GET DATE AND TIME
		Timer time = new Timer(500, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setClock();
			}
		});
		time.setRepeats(true);
		time.setCoalesce(true);
		time.setInitialDelay(0);
		time.start();
	}
	
	public void setClock() {
		clock.setText(DateFormat.getDateTimeInstance().format(new Date()));
	}

	
	

	
	
	
	// CLASS FOR THE WALLPAPER
//	class PanelFond extends JPanel {
//		Image picture;
//		public PanelFond() {
//
//			BufferedImage picture = null;
//			try {
//				picture = ImageIO.read(new File("Wallpaper.jpg"));
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//
//		public void paintComponent(Graphics g) {
//			super.paintComponent(g); // lui il s'occupe de redessiner les composant enfant.
//			g.drawImage(picture, 0, 0, null); // elle doit etre avant
//		}
//	}
	



}
