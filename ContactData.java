package Contacts;

import java.io.Serializable;

public class ContactData implements Serializable {

	//AVOID ERRORS WITH DESERIALASATION
	private static final long serialVersionUID = 1L;
	
	private String lastName;
	private String name;
	private String number;
	
	public ContactData (String lastName, String name, String number){
		this.lastName=lastName;
		this.name=name;
		this.number=number;
		
	}
	
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
}
