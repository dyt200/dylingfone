package dylingfone;

import java.util.Date;

public class Test {
	
	public static void main(String[] args) {
		System.out.println("STARTING...");
		
		
		
		try {
			//creation of gallery object ( funtions the same as contacts )
			Gallery gallery = new Gallery();
			
			//For printing whole gallery
			gallery.dumpData();
			
			
			
			//DYLAN START HERE

			Contacts contacts = new Contacts();
			contacts.dumpData();
			
			
			Contact[] array = contacts.getContacts();
			
			
			
			for (Contact contact : array) {

				contact.getId();
				System.out.println(contact.getId());

				contact.getFirstName();
				System.out.println(contact.getFirstName());
				
				contact.getLastName();
				contact.getBirthDate();
				contact.getEmail();
				contact.getTelMobile();
				contact.getTelHome();
				contact.getPic();
			}
			
			contacts.deleteContact(3);
			contacts.dumpData();
			//DYLAN FINSIH HERE
	
			//for printing all contacts
			//contacts.dumpData();
			
			/*contacts.addXmlElements(new String[][]{
												{"firstName", "ben"}, 
												{"lastName", "pock"},
												{"birthDate", "01.01.1901"}, 
												{"email", "a@a.com"}, 
												{"telMobile", "0101010"}, 
												{"telHome", "010101"}});*/
			
			//this isn't finished
			//contacts.deleteElementById(3);
			
		} catch(Exception e) {
			System.out.println("ERROR: "+e);
		}
		
		System.out.println("...FINISHED!");

	}

}
