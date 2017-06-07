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


public class SmartPhone {

	public static void main(String[] args) throws IOException {
		JFrame frame = new JFrame();
		Contacts cont = new Contacts(frame);
		AddContact addC = new AddContact(frame, cont);
		ModifyContact modC = new ModifyContact(frame, cont);
		HomeScreen hs = new HomeScreen(frame, cont);
		Calculator calc = new Calculator(frame);
		Notes not = new Notes(frame);

		
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
		
		
        frame.setTitle("SMARTPHONE");
		frame.setVisible(true);

		// PUT THE CARD HOME BY DEFAULT
		((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(), "home");
	}
}
