
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.io.*;

	/**
	 *Class GUI
	 *
	 * Klassen GUI är det grafiska
	 *
	 */

public class GUI extends JFrame implements ActionListener
{
	//Knappar
	private JButton searchName;
	private JButton searchNumb;
	private JButton insert;
	private JButton remove;
	private JButton about;
	private JButton clean;
	private JButton open;
	private JButton save;

	//Labels
	private JLabel firstname;
	private JLabel lastname;
	private JLabel phonenumber;
	private JLabel message;


	//Textfield
	private JTextField fnameInput;
	private JTextField lnameInput;
	private JTextField numberInput;
	private JTextField info;

	//Deklarerar ett Register
	Register r;

	/**
	 * Method GUI
	 *
	 * Skapar ett nytt register och lägger till alla grafikobjekt som är definerade i som instansvariabler.
	 */
	public GUI()
	{
		//Skapar ett nytt Register
		r = new Register();

		//Labels
		firstname 	= new JLabel("Förnamn: ", JLabel.RIGHT);
		lastname 	= new JLabel("Efternamn: ", JLabel.RIGHT);
		phonenumber = new JLabel("Telefonnr: ", JLabel.RIGHT);
		message 	= new JLabel("Meddelande: ", JLabel.RIGHT);

		//Knappar
		searchNumb	= new JButton("Sök nummer");
		searchName 	= new JButton("Sök namn");
		insert 		= new JButton("Sätt in post");
		remove 		= new JButton("Ta bort post");
		about 		= new JButton("Om programmet");
		clean 		= new JButton("Rensa fält");
		open 		= new JButton("Öppna register");
		save 		= new JButton("Spara register");

		//Actionlisteners på knappar
		searchNumb.addActionListener(this);
		searchName.addActionListener(this);
		insert.addActionListener(this);
		remove.addActionListener(this);
		about.addActionListener(this);
		clean.addActionListener(this);
		open.addActionListener(this);
		save.addActionListener(this);

		//Textfields
		fnameInput  = new JTextField("");
		lnameInput	= new JTextField("");
		numberInput = new JTextField("");
		info 		= new JTextField("");

		//Actionlisteners på Textfields
		fnameInput.addActionListener(this);
		lnameInput.addActionListener(this);
		numberInput.addActionListener(this);

		//States på Textfields
		info.setEditable(false);
		info.setForeground(Color.RED);

		//Skapar en Jpanel
		JPanel west = new JPanel();
		west.setLayout(new GridLayout(4,1));

		west.add(firstname);
		west.add(lastname);
		west.add(phonenumber);
		west.add(message);

		JPanel center = new JPanel();
		center.setLayout(new GridLayout(4,1));

		center.add(fnameInput);
		center.add(lnameInput);
		center.add(numberInput);
		center.add(info);

		JPanel east = new JPanel();
		east.setLayout(new GridLayout(4,2));

		east.add(searchNumb);east.add(about);
		east.add(searchName);east.add(clean);
		east.add(insert); 	 east.add(open);
		east.add(remove);	 east.add(save);

		//Skapar en Container som alla JPanels läggs i
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		c.add(west, BorderLayout.WEST);
		c.add(center, BorderLayout.CENTER);
		c.add(east, BorderLayout.EAST);

		//Mer attribut för rutan
		setSize(550, 150);
		setTitle("Register Pro 1.0");
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	/**
	 * Method actionPerformed
	 *
	 * Lyssnar efter händelser för de grafik objekt som definerade
	 */
	public void actionPerformed(ActionEvent e)
	{
		//Sök nummer
		if (e.getSource() == searchNumb)
		{
			String fname = fnameInput.getText();
			String lname = lnameInput.getText();

			lname = lname.substring(0, 1).toUpperCase() + lname.substring(1).toLowerCase();
			fname = fname.substring(0, 1).toUpperCase() + fname.substring(1).toLowerCase();

			Person p = r.lookupNumber(fname, lname);

			fnameInput.setText(p.getFirstname());
			lnameInput.setText(p.getLastname());
			numberInput.setText(p.getPhonenumber());

			info.setText("Nummer funnet");

		//Sök namn
		}else if(e.getSource() == searchName)
		{

			String number = numberInput.getText();


			Person p = r.lookupName(number);

			fnameInput.setText(p.getFirstname());
			lnameInput.setText(p.getLastname());
			numberInput.setText(p.getPhonenumber());

			info.setText("Namnet funnet");

		//Lägg till obejkt
		}else if(e.getSource() == insert)
		{
			String fname = fnameInput.getText();
			String lname = lnameInput.getText();
			String number = numberInput.getText();

			lname = lname.substring(0, 1).toUpperCase() + lname.substring(1).toLowerCase();
			fname = fname.substring(0, 1).toUpperCase() + fname.substring(1).toLowerCase();

			Person p = new Person(lname, fname, number);

			if (r.insert(p) == true){
				info.setText("Posten insatt");
			}else{
				info.setText("Posten kan inte sättas in");
			}

		//Ta bort objekt
		}else if(e.getSource() == remove)
		{
			String fname = fnameInput.getText();
			String lname = lnameInput.getText();
			String number = numberInput.getText();

			lname = lname.substring(0, 1).toUpperCase() + lname.substring(1).toLowerCase();
			fname = fname.substring(0, 1).toUpperCase() + fname.substring(1).toLowerCase();

			Person p = new Person(lname, fname, number);

			if (r.removeFromlist(p) == true){
				info.setText("Posten borttagen");
			}else{
				info.setText("Posten kan inte tas bort");
			}

		//Information
		}else if(e.getSource() == about)
		{
			JOptionPane.showMessageDialog(null, "Register Pro 1.0");

		//Rensa textfälten
		}else if(e.getSource() == clean)
		{
			fnameInput.setText("");
			lnameInput.setText("");
			numberInput.setText("");

		//Öppna textfilen och läs från
		}else if(e.getSource() == open)
		{
			try {
				r.load();
				info.setText("Filen reg.txt öppnad");
			}catch(IOException d)
			{
				System.out.println("Nått gick fel");
			}
			open.setEnabled(false);

		//Spara det som finns i Registret till textfil
		}else if(e.getSource() == save)
		{
			try{
			r.save();
			info.setText("Filen reg.txt sparad");
			}catch(IOException d)
			{
				System.out.println("Nått gick fel");
			}
		}

	}
}
