import java.awt.CardLayout;
import java.awt.Color;
import java.io.IOException;

import javax.swing.JFrame;

import Calculator.Calculator;
import Contacts.AddContact;
import Contacts.Contacts;
import Contacts.ModifyContact;
import HomeScreen.HomeScreen;
import Notes.Notes;
import Pictures.Pictures;
import Pictures.ShowImage;


public  class SmartPhone {

	static JFrame frame ;
	static Contacts cont;
	static AddContact addC ;
	static ModifyContact modC ;
	static HomeScreen hs;
	static Calculator calc ;
	static Notes not ;
	static Pictures pic ;
	static ShowImage im ;	



	public  SmartPhone(){
		frame = new JFrame();
		try {
			cont = new Contacts(frame);
			calc = new Calculator(frame);
			not = new Notes(frame);
			modC = new ModifyContact(frame, cont);
			addC = new AddContact(frame, cont);
			hs = new HomeScreen(frame, cont);
			pic = new Pictures(frame);
			im = new ShowImage(frame, pic, addC);

			frame.setLayout(new CardLayout());
			frame.setSize(480, 800);
			frame.setResizable(false);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			frame.add(hs, "home");
			frame.add(cont, "contacts");
			frame.add(addC, "addContact");
			frame.add(modC, "modifyContact");
			frame.add(calc, "calculator");
			frame.add(not, "notes");
			frame.add(pic, "pictures");
			frame.add(im, "image");


			frame.setTitle("SMARTPHONE");
			frame.setVisible(true);

			// PUT THE CARD HOME BY DEFAULT
			((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(), "home");
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
