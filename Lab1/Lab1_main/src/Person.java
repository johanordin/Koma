
	/**
	 *Class Person
	 *
	 * Klassen Person definerar objekten som klassen Register kommer innehålla.
	 * Person har 3 instansvariabler som är firstname, lastname och phonenumber.
	 */

public class Person implements Comparable<Person>
{
	//Instansvariabler
	private String firstname, lastname, phonenumber;

	/**
	 * Method Person
	 *
	 * Initierar de tre argumenten med instansvariablerna för objektet
	 */
	public Person(String lname, String fname, String number)
	{
		lastname = lname;
		firstname  = fname;
		phonenumber = number;
	}
	/**
	 *Method getLastname
	 *
	 * getmetod för att komma åt ett objekts lastname.
	 */
	public String getLastname()
	{
		return lastname;
	}
	/**
	 * Method getFirstname
	 *
	 * getmetod för att komma åt ett objekts firstname.
	 *
	 */
	public String getFirstname()
	{
		return firstname;
	}
	/**
	 *Method getPhonenumber
	 *
	 *getmetod för att komma åt ett objekts telefonnummer.
	 *
	 */
	public String getPhonenumber()
	{
		return phonenumber;
	}
	/**
	 *Method toString
	 *
	 *definerar hur ett Person objekt ska skrivas ut.
	 *
	 */
	public String toString()
    {
        return String.format("%-15s%-15s%-15s%n", lastname, firstname, phonenumber);
    }
    /**
     *Method compareTo
     *
     *definerar hur ett Person objekt ska jämföras.
     *
     */
	public int compareTo(Person p)
	{
		return getLastname().compareTo(p.getLastname());
	}


}
