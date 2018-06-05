package dylingfone;

import java.util.Date;

public class Test {
	
	public static void main(String[] args) {
		System.out.println("=====================");
		System.out.println("START OF BEN'S TESTING SUITE");
		System.out.println("=====================");
		System.out.println();
		
		
		
		try {
			//creation of primary objects
			Gallery gallery = new Gallery();
			Contacts contacts = new Contacts();
		
			//for testing use dumpData() its like toString or w/e but better 
			//gallery.dumpData();
			
			//if you want an array with all the data 
			/*Contact[] array = contacts.getContactList();
			
			simple loop to give an example how to get information - 
			 
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
			}*/
			
			//How to EDIT a contact, with test dumps
			//works really well so far, needs full testing
			/*contacts.dumpData();
			contacts.editContact(1, "firstName", "tickleMyPickle");
			contacts.dumpData();*/
			
			//How to EDIT an image, with dumps
			//didnt test yet but assumed working
			/*gallery.dumpData();
			 * gallery.editImage(1, "title", "iChangedTheTitle");
			 * gallery.dumpData();
			 */
			
			//How to DELETE a contact, with dumps for testing
			/*contacts.dumpData();
			contacts.deleteContact(3);
			contacts.dumpData();*/
			
			
			//How to DELETE a picture, with dumps for testing
			/* gallery.dumpData();
			gallery.addPicture("added photo 1", "this was/is the very first photo added with Ben's sick function", "./images/test.jpg");
			gallery.dumpData();
			gallery.deletePicture(3);
			gallery.dumpData();*/
	
			//for printing all contacts
			//contacts.dumpData();
			
			//HOW TO ADD TO CONTACTS/GALLERY (add strings this is just an example)
			//normally these two functions handle IDs automatically;
			//
			//contacts.addContact(firstName, lastName, birthDate, email, telMoblie, telHome);
			//gallery.addPicture(title, desc, path);
			
			//How to add stuff to either contacts or gallery 
			//... I don't actually know what this is for because contacts.addContact() && gallery.addImage()
			//...did I even make this? 
			/*contacts.addXmlElements(new String[][]{
												{"firstName", "ben"}, 
												{"lastName", "pock"},
												{"birthDate", "01.01.1901"}, 
												{"email", "a@a.com"}, 
												{"telMobile", "0101010"}, 
												{"telHome", "010101"}});*/
			
		} catch(Exception e) {
			System.out.println("ERROR: "+e);
		}
		
		System.out.println();
		System.out.println();
		System.out.println("=====================");
		System.out.println("END OF BEN'S TESTING SUITE");
		System.out.println("=====================");
	}

}
