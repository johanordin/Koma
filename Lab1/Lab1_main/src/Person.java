
	/**
	 *Class Person
	 *
	 * Klassen Person definerar objekten som klassen Register kommer inneh�lla.
	 * Person har 3 instansvariabler som �r firstname, lastname och phonenumber.
	 */

public class Person implements Comparable<Person>
{
	//Instansvariabler
	private String firstname, lastname, phonenumber;

	/**
	 * Method Person
	 *
	 * Initierar de tre argumenten med instansvariablerna f�r objektet
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
	 * getmetod f�r att komma �t ett objekts lastname.
	 */
	public String getLastname()
	{
		return lastname;
	}
	/**
	 * Method getFirstname
	 *
	 * getmetod f�r att komma �t ett objekts firstname.
	 *
	 */
	public String getFirstname()
	{
		return firstname;
	}
	/**
	 *Method getPhonenumber
	 *
	 *getmetod f�r att komma �t ett objekts telefonnummer.
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
     *definerar hur ett Person objekt ska j�mf�ras.
     *
     */
	public int compareTo(Person p)
	{
		return getLastname().compareTo(p.getLastname());
	}


}
