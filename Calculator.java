package Calculator;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Calculator extends JPanel {

	//DEFINING THE FONT FOR THE DISPLAY
	private Font fontDisplay = new Font("Arial", Font.BOLD, 40);

	//DEFINE THE 2 TABLES CONTAINING THE BUTTONS
	private String[] tableOfString = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0", ".", "=", "C", "+", "-", "*", "/"};
	private JButton[] tableOfButtons = new JButton[tableOfString.length];

	//CREATING THE OTHER ELEMENTS OF THE CALCULATOR
	private JButton back = new JButton("Back");
	private JPanel panelNorth = new JPanel();
	private JPanel operators = new JPanel();
	private JPanel numbers = new JPanel();
	private JPanel displayButtonAndCalculation = new JPanel();
	private JLabel displayCalculation = new JLabel();
	private Dimension size = new Dimension(100, 120);
	private Font font = new Font("Arial",Font.BOLD,30);

	//DEFINING THE ELEMENTS FOR THE CALCULATION
	private double number1;
	private boolean clicOperator = false;
	private boolean update = false;
	private String operator = "";


	public Calculator(JFrame frame){

		setBackground(Color.BLACK);

		initialize();

		//ADD ACTION FOR BUTTON WITH ANONYMOUS CLASS
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(), "home");
			}
		});	
	}

	/**
	 * This private method customize and add all
	 * the components we need for this class
	 */
	private void initialize() {
		//PUTTING 0 LIKE DEFAULT VALUE IN THE DISPLAYCALCULATION AND PUTTING INFORMATION TO THE RIGHT IN THE JLABEL
		displayCalculation = new JLabel("0");
		displayCalculation.setFont(fontDisplay);
		displayCalculation.setHorizontalAlignment(JLabel.RIGHT);
		displayCalculation.setPreferredSize(new Dimension(180, 60));

		//RESIZE THE PANELS
		operators.setPreferredSize(new Dimension(120, 500));
		numbers.setPreferredSize(new Dimension(320, 500));
		displayButtonAndCalculation.setPreferredSize(new Dimension(450, 50));

		//ADDING THE BUTTON BACK AND THE LABEL IN THE DISPLAYBUTTONANDCALCULATION
		panelNorth.setBackground(Color.GRAY);
		panelNorth.add(back);
		add(panelNorth, BorderLayout.NORTH);
		displayButtonAndCalculation.add(displayCalculation);

		//CREATING A NEW GRIDLAYOUT
		displayButtonAndCalculation.setLayout(new GridLayout(1, 1));
		displayButtonAndCalculation.setBorder(BorderFactory.createLineBorder(Color.black));

		//PUTTING THE ELEMENT OF THE CALCULATOR IN THE GOOD PLACE
		numbers.setBackground(Color.DARK_GRAY);
		operators.setBackground(Color.BLACK);
		add(displayButtonAndCalculation, BorderLayout.NORTH);
		add(numbers, BorderLayout.CENTER);
		add(operators, BorderLayout.EAST);

		// BUTTONS
		back.setPreferredSize(new Dimension(90,90));
		back.setBackground(Color.BLACK);
		back.setOpaque(true);
		back.setBorderPainted(false);
		back.setForeground(Color.WHITE);

		//CREATING THE BUTTONS WITH A LOOP FOR
		for(int i = 0; i < tableOfString.length; i++){
			tableOfButtons[i] = new JButton(tableOfString[i]);
			tableOfButtons[i].setPreferredSize(size);
			tableOfButtons[i].setFont(font);

			//CHOSEN THE ACTION ACCORDING TO THE BUTTON WITH SWITCH
			switch(i){

			//EQUAL
			case 11 :
				tableOfButtons[i].addActionListener(new EqualListener());
				numbers.add(tableOfButtons[i]);
				break;

				//RESET
			case 12 :
				tableOfButtons[i].setBackground(Color.ORANGE);
				tableOfButtons[i].setOpaque(true);
				tableOfButtons[i].setBorderPainted(false);
				tableOfButtons[i].setForeground(Color.red);
				tableOfButtons[i].addActionListener(new CancelListener());
				operators.add(tableOfButtons[i]);
				break;

				//SUM
			case 13 :
				tableOfButtons[i].setBackground(Color.ORANGE);
				tableOfButtons[i].setOpaque(true);
				tableOfButtons[i].setBorderPainted(false);
				tableOfButtons[i].addActionListener(new SumListener());
				tableOfButtons[i].setPreferredSize(size);
				operators.add(tableOfButtons[i]);
				break;

				//SUBTRACTION
			case 14 :
				tableOfButtons[i].setBackground(Color.ORANGE);
				tableOfButtons[i].setOpaque(true);
				tableOfButtons[i].setBorderPainted(false);
				tableOfButtons[i].addActionListener(new SubtractionListener());
				tableOfButtons[i].setPreferredSize(size);
				operators.add(tableOfButtons[i]);
				break; 

				//MULTIPLICATION
			case 15 :
				tableOfButtons[i].setBackground(Color.ORANGE);
				tableOfButtons[i].setOpaque(true);
				tableOfButtons[i].setBorderPainted(false);
				tableOfButtons[i].addActionListener(new MultiplicationListener());
				tableOfButtons[i].setPreferredSize(size);
				operators.add(tableOfButtons[i]);
				break;

				//DIVISION
			case 16 :
				tableOfButtons[i].setBackground(Color.ORANGE);
				tableOfButtons[i].setOpaque(true);
				tableOfButtons[i].setBorderPainted(false);
				tableOfButtons[i].addActionListener(new DivisionListener());
				tableOfButtons[i].setPreferredSize(size);
				operators.add(tableOfButtons[i]);
				break;

				//NUMBERS
			default :
				numbers.add(tableOfButtons[i]);
				tableOfButtons[i].addActionListener(new NumberListener());
				break;
			}
		}
	}

	//METHOD WHICH DOES THE CALCULATION ACCORDING TO THE CHOSEN OPERATOR
	private void calculation(){
		if(operator == "+"){
			number1 = number1 + Double.valueOf(displayCalculation.getText()).doubleValue();
			displayCalculation.setText(String.valueOf(number1));
		}
		if(operator == "-"){
			number1 = number1 - Double.valueOf(displayCalculation.getText()).doubleValue();
			displayCalculation.setText(String.valueOf(number1));
		}          
		if(operator == "*"){
			number1 = number1 * Double.valueOf(displayCalculation.getText()).doubleValue();
			displayCalculation.setText(String.valueOf(number1));
		}     
		if(operator == "/"){
			try{
				number1 = number1 / Double.valueOf(displayCalculation.getText()).doubleValue();
				displayCalculation.setText(String.valueOf(number1));
			} catch(ArithmeticException e) {
				displayCalculation.setText("0");
			}
		}
	}

	class NumberListener implements ActionListener {
		public void actionPerformed(ActionEvent e){
			String text = ((JButton)e.getSource()).getText();
			if(update){
				update = false;
			}
			else{
				if(!displayCalculation.getText().equals("0"))
					text = displayCalculation.getText() + text;
			}
			displayCalculation.setText(text);
		}
	}

	class EqualListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0){
			calculation();
			update = true;
			clicOperator = false;
		}
	}

	class SumListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0){
			if(clicOperator){
				calculation();
				displayCalculation.setText(String.valueOf(number1));
			}
			else{
				number1 = Double.valueOf(displayCalculation.getText()).doubleValue();
				clicOperator = true;
			}
			operator = "+";
			update = true;
		}
	}

	class SubtractionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0){
			if(clicOperator){
				calculation();
				displayCalculation.setText(String.valueOf(number1));
			}
			else{
				number1 = Double.valueOf(displayCalculation.getText()).doubleValue();
				clicOperator = true;
			}
			operator = "-";
			update = true;
		}
	}

	class MultiplicationListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0){
			if(clicOperator){
				calculation();
				displayCalculation.setText(String.valueOf(number1));
			}
			else{
				number1 = Double.valueOf(displayCalculation.getText()).doubleValue();
				clicOperator = true;
			}
			operator = "*";
			update = true;
		}
	}

	class DivisionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0){
			if(clicOperator){
				calculation();
				displayCalculation.setText(String.valueOf(number1));
			}
			else{
				number1 = Double.valueOf(displayCalculation.getText()).doubleValue();
				clicOperator = true;
			}
			operator = "/";
			update = true;
		}
	}

	class CancelListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0){
			number1 = 0;
			clicOperator = false;
			update = true;
			operator = "";
			displayCalculation.setText("0");
		}
	}      
}
