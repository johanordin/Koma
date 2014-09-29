import java.io.*;
import java.util.*;

	/**
	 *Class Register
	 *
	 * Klassen Register är en lista som består av Person objekt.
	 */

public class Register {

	//Instansvariabler
	private Vector<Person> list;

	/**
	 * Method Register
	 *
	 * Skapar en tom vektor för Person objekt.
	 *
	 */
	public Register()
	{
		list = new Vector<Person>();
	}

	/**
	 *Method load
	 *
	 *Skapar en scanner som kopplas till textfilen som ska läsas från.
	 *Läser in 3 olika strängar från varje rad och skapar ett Person objekt
	 *som sedan läggs till i vektorn list.
	 *
	 */
	public void load(String filename) throws IOException
	{
		//Scanner sc = new Scanner(new File("reg.txt"));
		Scanner sc = new Scanner(new File(filename));

		while( sc.hasNext() )
		{
			String lname  = sc.next();
			String fname  = sc.next();
			String number = sc.nextLine().trim();

			Person p = new Person(lname, fname, number);
			list.add(p);
		}
		sc.close();
	}
	/**
	 *Method print
	 *
	 *Skriver ut objekten som finns i vektorn till konsollen.
	 *
	 */
	public void print()
	{
		for (int i = 0; i < list.size(); i++){
			System.out.println(list.get(i));
		}
	}
	/**
	 *Method lookupName
	 *
	 *Jämför det inskriva numret mot numrerna i vektorn.
	 *Om numret finns returnas obejktet som hör till det numret.
	 *
	 */
	public Person lookupName(String number)
	{
    	int i = 0;
    	while( i < list.size() && !number.equals(list.get(i).getPhonenumber()) )
    	{
    		i++;
    	}

  		if(i < list.size() ){
    		return list.get(i);
  		}
  		else{
        	return null;
  		}
	}
	/**
	 *Method lookupNumber
	 *
	 *Jämför det inskriva för- och efternamn mot för- och efternamn i vektorn.
	 *Om det både för- och efternamn matchar returnas obejktet som hör till det numret.
	 *
	 */
	public Person lookupNumber(String fname, String lname)
	{
    	int i = 0;
    	while( i < list.size() && !(fname.equals(list.get(i).getFirstname()) && lname.equals(list.get(i).getLastname()) )  )
    	{
    		i++;
    	}

  		if(i < list.size() ){
    		return list.get(i);
  		}
  		else{
        	return null;
  		}
	}
	/**
	 *Method insert
	 *
	 *Kollar om objektet p mot de objekt som finns i vektorn.
	 *Om det inte finns ett likadant obejekt läggs objektet p till i listan och metoden returnar true.
	 *
	 */
	public boolean insert(Person p){


		for(int i = 0; i < list.size(); i++){
			if( p.getFirstname().equals(list.get(i).getFirstname()) || p.getLastname().equals(list.get(i).getLastname()) || p.getPhonenumber().equals(list.get(i).getPhonenumber()) ){
				return false;
			}
		}
		list.add(p);
		return true;

	}
	/**
	 *Method remove
	 *
	 *Kollar om objektet p mot de objekt som finns i vektorn.
	 *Om det finns ett likadant obejekt tas objektet p bort från listan och metoden returnar true.
	 *
	 */
	public boolean removeFromlist(Person p){


		for(int i = 0; i < list.size(); i++){
			if( p.getFirstname().equals(list.get(i).getFirstname()) && p.getLastname().equals(list.get(i).getLastname()) && p.getPhonenumber().equals(list.get(i).getPhonenumber()) ){
				list.remove(i);
				return true;
			}


		}
		return false;

	}
	/**
	 *Method save
	 *
	 *Sorterar listan med Collection.sort()
	 *Skapar en ström som listan ska skrivas till
	 *
	 *Skriver ut hela listan
	 *
	 */
	public void save(String filename) throws IOException
    {

        Collections.sort(list);

        PrintWriter outfile = new PrintWriter
                             (new BufferedWriter
                             (new FileWriter(filename)));


        for (int i = 0; i < list.size(); i++)
        {
			outfile.print(list.get(i));
        }

        outfile.close();
    }
	
	public String[]  update() {
		
		
		int size = list.size();
		String[] lst = new String[size];
		
		for (int i = 0; i < list.size(); i++){
			
			lst[i] = list.get(i).getFirstname();
		}
		
		
		return lst;
		
	}
	
	
}
