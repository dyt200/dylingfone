package dylingfone;

public class Test {
	
	public static void main(String[] args) {
		System.out.println("STARTING...");
		
		
		
		try {
			Gallery gallery = new Gallery();
			gallery.dumpData();
			
			Contacts contacts = new Contacts();
			contacts.dumpData();
			
	
			
	
			
			
		
			
			contacts.addXmlElements(new String[][]{
												{"firstName", "ben"}, 
												{"lastName", "pock"},
												{"birthDate", "01.01.1901"}, 
												{"email", "a@a.com"}, 
												{"telMobile", "0101010"}, 
												{"telHome", "010101"}});
			
			//this isn't finished
			//contacts.deleteElementById(3);
			
		} catch(Exception e) {
			System.out.println("ERROR: "+e);
		}
		
		System.out.println("...FINISHED!");

	}

}
