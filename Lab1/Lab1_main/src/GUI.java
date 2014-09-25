
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

public class GUI
{
	
	//--------------------------------------------//
	//Frame
    public JFrame mainframe;
	
	//--------------------------------------------//
	//MenuBar
	private JMenuBar mbar = new JMenuBar();
	private JMenu menuFile = new JMenu("File");
	private JMenu menuEdit = new JMenu("Edit");
	private JMenu menuHelp = new JMenu("Help");
	
	//--------------------------------------------//
	//MenuFile
    private JMenuItem mniOpen = new JMenuItem("Open ...");
    private JMenuItem mniSave = new JMenuItem("Save ...");
    private JMenuItem mniExit = new JMenuItem("Exit");
    
	//MenuEdit
    private JMenuItem mniAdd    = new JMenuItem("Add ...");
    private JMenuItem mniRemove = new JMenuItem("Remove ...");

    //MenuHelp
    private JMenuItem mniHelp  = new JMenuItem("Help ...");
    private JMenuItem mniAbout = new JMenuItem("About ...");
    
    //--------------------------------------------//
    //PopupMenu
    private JPopupMenu pmnEdit = new JPopupMenu("Edit");
    
    private JMenuItem pmiAdd = new JMenuItem("Add");
    private JMenuItem pmiRemove = new JMenuItem("Remove");
    
    //--------------------------------------------//
    //Buttons
	private JButton funcButton;
	//Labels
	private JLabel firstname;
	private JLabel lastname;
	private JLabel phonenumber;
	private JLabel message;

	//Textfields
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
		JFrame mainframe = new JFrame();
		
		//Skapar ett nytt Register
		r = new Register();
		
		//--------------------------------------------//
		//JMenuBar
		mainframe.setJMenuBar(mbar);
		mbar.add(menuFile);
		mbar.add(menuEdit);
		mbar.add(menuHelp);
		
		//--------------------------------------------//
		//File
		FileListener filelistener = new FileListener();
		menuFile.add(mniOpen);
		menuFile.add(mniSave);
		menuFile.addSeparator();
		menuFile.add(mniExit);
		
		//File - ActionListener
		mniOpen.addActionListener(filelistener);
		mniSave.addActionListener(filelistener);
		mniExit.addActionListener(filelistener);
		
		//File - Accelerators
		mniOpen.setAccelerator(
				  KeyStroke.getKeyStroke(
				    KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		mniSave.setAccelerator(
				  KeyStroke.getKeyStroke(
				    KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		mniExit.setAccelerator(
				  KeyStroke.getKeyStroke(
				    KeyEvent.VK_X, ActionEvent.CTRL_MASK));

		//--------------------------------------------//
		//Edit
		EditListener editlistener = new EditListener();
		menuEdit.add(mniAdd);
		menuEdit.add(mniRemove);
		
		//Edit - ActionListener
		mniAdd.addActionListener(editlistener);
		mniRemove.addActionListener(editlistener);
		
		//--------------------------------------------//
		//Help
		HelpListener helplistener = new HelpListener();
		menuHelp.add(mniHelp);
		menuHelp.add(mniAbout);
		
		//Edit - ActionListener
		mniHelp.addActionListener(helplistener);
		mniAbout.addActionListener(helplistener);
		
		mainframe.addMouseListener(new PopupHandler());
        rightMouseListener mouselistener = new rightMouseListener();
        
        pmnEdit.add(pmiAdd);
        pmnEdit.add(pmiRemove);
        
        pmiAdd.addActionListener(mouselistener);
        pmiRemove.addActionListener(mouselistener);
		
		mainframe.setSize(400,200);
        mainframe.setVisible(true);
        
        mainframe.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
               System.exit(0);
            }        
         });  
        
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void searchWindow() {
		
		
		
	}
	
	
	public void configureWindow(String func) {
		
		//--------------------------------------------//
		//Labels
		firstname 	= new JLabel("Förnamn: ", JLabel.RIGHT);
		lastname 	= new JLabel("Efternamn: ", JLabel.RIGHT);
		phonenumber = new JLabel("Telefonnr: ", JLabel.RIGHT);
		message 	= new JLabel("Meddelande: ", JLabel.RIGHT);
		
		//--------------------------------------------//
		//Buttons
		funcButton	= new JButton(func);

		//--------------------------------------------//
		//Textfields
		fnameInput  = new JTextField("");
		lnameInput	= new JTextField("");
		numberInput = new JTextField("");
		info 		= new JTextField("");
		
		//Textfields - states
		info.setEditable(false);
		info.setForeground(Color.RED);
		
		//--------------------------------------------//
		//Skapar Jpanels
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
		east.setLayout(new GridLayout(1,1));
		
		east.add(funcButton);
		
		//--------------------------------------------//
		//Configure
		ConfigureListener configurelistener = new ConfigureListener(func);
		
		//Configure - ActionListener
		fnameInput.addActionListener(configurelistener);
		lnameInput.addActionListener(configurelistener);
		numberInput.addActionListener(configurelistener);
		funcButton.addActionListener(configurelistener);
		
		//--------------------------------------------//
		//Skapar en Container 
		
		
		mainframe.getContentPane().removeAll();

		
		//Container c = new Container();
		Container c = mainframe.getContentPane();
		//mainframe.getContentPane().add(c);
		
		c.setLayout(new BorderLayout());
		c.add(west, BorderLayout.WEST);
		c.add(center, BorderLayout.CENTER);
		c.add(east, BorderLayout.EAST);
		
		mainframe.setContentPane(c);

		mainframe.revalidate();
		mainframe.repaint();
		
		
		mainframe.setVisible(true);
		//setSize(400,600);
	}
	
	//--------------------------------------------//
	//Implement FileListener
	private class FileListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
            if (e.getSource() == mniOpen) {
            	final JFileChooser  fileDialog = new JFileChooser();
        	
            	int returnVal = fileDialog.showOpenDialog(mainframe);
            	
	            if (returnVal == JFileChooser.APPROVE_OPTION) {
	               java.io.File file = fileDialog.getSelectedFile();
	               
	               String filename = file.getName();
	               
		   			try {
						r.load(filename);
						System.out.println("File Selected :" + file.getName());
						//info.setText("File Selected :" + file.getName() + " öppnad");
					}
		   			catch(IOException d) {
						System.out.println("Nått gick fel");
					}
		   			
	            }
	            else {
	            	System.out.println("Open command cancelled by user." );           
	            }
            
            }
            else if (e.getSource() == mniSave) {
    			try {
					r.save();
					System.out.println("Filen reg.txt sparad");
				}
    			catch(IOException d) {
					System.out.println("Nått gick fel");
				}
            }
            else if (e.getSource() == mniExit) {
                System.exit(0);
            }
		}
	}
	//--------------------------------------------//
	//Implement EditListener
	private class EditListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
            if (e.getSource() == mniAdd){
            	String add = "Add";
            	configureWindow(add);
            }
            else if (e.getSource() == mniRemove) {
            	String remove = "Remove";
            	configureWindow(remove);
            }
		}
	}
	//--------------------------------------------//
	//Implement HelpListener
	private class HelpListener implements ActionListener {
		public void actionPerformed(ActionEvent e){
			
			 if (e.getSource() == mniHelp) {
                JOptionPane.showMessageDialog(mainframe, "This feature is\n" + "only available in\n" + "ShapeDrawer Pro!");
	         }
			 else if (e.getSource() == mniAbout) {
                JOptionPane.showMessageDialog(mainframe, "ShapeDrawer LE 1.1\n" + "Copyright 2013-2013\n" + "Amazing Software, Inc.");
	         }
		}
	}
	
	private class ConfigureListener implements ActionListener {
		
		private String func1;
		
		public ConfigureListener() {
		}
		
		public ConfigureListener(String func) {
			func1 = func;
		}
		
		public void actionPerformed(ActionEvent e) {
			
			 if (e.getSource() == funcButton) {
				String fname = fnameInput.getText();
				String lname = lnameInput.getText();
				String number = numberInput.getText();

				lname = lname.substring(0, 1).toUpperCase() + lname.substring(1).toLowerCase();
				fname = fname.substring(0, 1).toUpperCase() + fname.substring(1).toLowerCase();

				Person p = new Person(lname, fname, number);
				
					if(func1 == "Add") {
						if (r.insert(p) == true) {
							info.setText("Posten insatt");
						}
						else {
							info.setText("Posten kan inte sättas in");
						}
					}
					else {
						if (r.removeFromlist(p) == true) {
							info.setText("Posten är borttagen");
						}
						else {
							info.setText("Posten kunde inte tas bort");
						}
					}
			}

		}
	}
	
	private class rightMouseListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {	
            
			if (e.getSource() == pmiAdd) {
            	String add = "Add";
            	configureWindow(add);
            }
            else if (e.getSource() == pmiRemove){
            	String remove = "Remove";
            	configureWindow(remove);
            }
		}
	}
	
	//--------------------------------------------//
    // innerklass för muslyssnare mha adapterklass
    private class PopupHandler extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
            if (e.isMetaDown()) { // Hoger musknapp nedtryckt?
            	
                pmnEdit.show(e.getComponent(), e.getX(), e.getY());
            }
        }
    }


}
