/**
 * Class which creates the graphic part of the gallery of pictures with a table and a GridLayout
 * @author Beyza Salyador
 */

package Pictures;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Pictures extends JPanel{

	private JPanel panelCentral = new JPanel();
	private GridLayout galleryOfPictures = new GridLayout(4, 2);
	private JButton back = new JButton("Back");
	private JButton imagesButton;
	protected ImageIcon bigImage;
	private JPanel panel = new JPanel();
	private JScrollPane scrollPane;

	// TAKE THE REPOSITORY IMAGES
	private File imagesFile = new File("./Images"); 

	// LIST IMAGES INTO A TABLE
	private String [] listImages = imagesFile.list();

	private ImageIcon img;
	private Dimension size = new Dimension(150, 190);
	protected int j;
	protected int i;
	private String linkImage;

	public Pictures(JFrame frame){

		setBackground(Color.BLACK);

		initialize();

		// FOR EACH BUTTON
		for(j=0; j<listImages.length; j++){

			// CONFIGURE SIZE IMAGE
			img = new ImageIcon(imagesFile + "/" + listImages[j]);
			Image image = img.getImage();
			Image newimg = image.getScaledInstance(200, 300,  java.awt.Image.SCALE_SMOOTH);
			img = new ImageIcon(newimg);

			// BUTTONS
			imagesButton = new JButton(img);
			imagesButton.setPreferredSize(size);

			// PANEL
			panelCentral.add(imagesButton);


			// ADD ACTION FOR EACH IMAGE WITH ANONYMOUS CLASS
			imagesButton.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					bigImage = (ImageIcon) ((JButton)e.getSource()).getIcon();
					setLinkImage(((JButton)e.getSource()).getIcon().toString());
					((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(), "image");
				}
			});
		}


		// ADD ACTION FOR BUTTONS WITH ANONYMOUS CLASS
		back.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				((CardLayout) frame.getContentPane().getLayout()).show(frame.getContentPane(), "home");
			}
		}); 
	}

	/**
	 * This private method customize and add all
	 * the components we need for this class
	 * @author Beyza Salyador
	 */
	private void initialize() {
		// BUTTON BACK
		back.setPreferredSize(new Dimension(90,90));
		back.setBackground(Color.BLACK);
		back.setOpaque(true);
		back.setBorderPainted(false);
		back.setForeground(Color.WHITE);

		// SPACE AROUND PHOTOS
		galleryOfPictures.setHgap(5);
		galleryOfPictures.setVgap(5);

		// PANELS
		panelCentral.setBackground(Color.BLACK);
		panelCentral.setLayout(galleryOfPictures);
		panel.setBackground(Color.GRAY);
		panel.add(back);
		
		scrollPane = new JScrollPane(panelCentral);
		scrollPane.setPreferredSize(new Dimension(480,700));

		add(panel);
		add(scrollPane);
	}

	public String getLinkImage() {
		return linkImage;
	}

	public void setLinkImage(String linkImage) {
		this.linkImage = linkImage;
	} 
}
