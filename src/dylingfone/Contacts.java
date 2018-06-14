package dylingfone;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * This is an object that manages ALL contacts in an array, and the associated functions for treating them
 * @author Dylan Thompson & Ben Pocklington
 *
 */
public class Contacts extends Page{
	private Contact[] contactList;
	
	/**
	 * Creation of contacts object
	 */
	public Contacts() {	
		String[] path = {"contacts", "contact"};
		super.setXml(new File("./contacts.xml"));
		super.setPath(path);
		this.contactList = getContactsFromFile();
	}
	
	/**
	 * toString for all contacts
	 */
	public void dumpData() {
		System.out.println("START OF CONTACTS DATA : ");
		System.out.println();
		for (int i = 0; i < contactList.length ; i++) {
		
		   System.out.println("=====================");
		   System.out.println("index :" + i );
		   System.out.println("id : "+this.contactList[i].getId());
		   System.out.println("name : "+this.contactList[i].getFirstName());
		   System.out.println("last name : "+this.contactList[i].getLastName());
		   System.out.println("dob : "+this.contactList[i].getBirthDate());
		   System.out.println("email : "+this.contactList[i].getEmail());
		   System.out.println("mob : "+this.contactList[i].getTelMobile());
		   System.out.println("home : "+this.contactList[i].getTelHome());
		   System.out.println("=====================");
		}
	}
	
	/**
	 * Change any element of the XML file, make sure it exists!
	 * @param id
	 * @param element
	 * @param newValue
	 */
	public void editContact(int id, String element, String newValue) {
		int tempId;
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(super.xml);
			
			doc.getDocumentElement().normalize();
		    NodeList list = doc.getElementsByTagName("contact");
		    
		    for (int i = 0; i < list.getLength(); i++) {
		    	
		    	Element contact = (Element)list.item(i);
			    tempId = Integer.parseInt(contact.getAttribute("id"));
			    
			    if (id == tempId) {
			    	Node toChange = contact.getElementsByTagName(element).item(0);
			    	toChange.setTextContent(newValue);
			    	
			    	TransformerFactory transformerFactory = TransformerFactory.newInstance();
			        Transformer transformer = transformerFactory.newTransformer();
			        DOMSource source = new DOMSource(doc);
			        StreamResult result = new StreamResult(getXml());
			        transformer.transform(source, result);
			        
			        contactList = getContactsFromFile();
			    }
		    }
		} catch(Exception e) {
			System.out.println("ERROR IN EDITCONTACT() : "+e);
		}
	}
	
	/**
	 * Delete any contact in the array by ID
	 * @param id
	 */
	public void deleteContact(int id) {
		
		int tempId;
		Node tempNode;
		
		try {
			
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(super.xml);
	        
	        doc.getDocumentElement().normalize();
		    NodeList list = doc.getElementsByTagName("contact");
		    
		    for (int i = 0; i < list.getLength(); i++) {
		    	tempId = -1;
			    Element contact = (Element)list.item(i);
			    
			    tempId = Integer.parseInt(contact.getAttribute("id"));
			    
			    if (id == tempId) {
			    	
			    	//Selects node and file
			    	tempNode=list.item(i);
			        tempNode.getParentNode().removeChild(contact);
			        DOMSource source = new DOMSource(doc);
			        
			        //changes the actual file
		            TransformerFactory transformerFactory = TransformerFactory.newInstance();
		            Transformer transformer = transformerFactory.newTransformer();
		            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

		            StreamResult result = new StreamResult(getXml());
		            transformer.transform(source, result);
		            
		            //reloads the data after modifications
			        contactList = getContactsFromFile();
			    } 
		    }
		} catch(Exception e) {
			System.out.println("ERROR IN deleteContact : "+e);
		}
		
		
	}
	/**
	 * Add a new contact to the XML file and to the array
	 * @param firstName
	 * @param lastName
	 * @param birthDate
	 * @param email
	 * @param telMobile
	 * @param telHome
	 * @param pic
	 */
	public void addContact(String firstName, String lastName, String birthDate, String email, String telMobile, String telHome, String pic) {
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(super.xml);
	        Element root = doc.getDocumentElement();
	        
	        Element newContact = doc.createElement("contact");
	        newContact.setAttribute("id",  Integer.toString(super.getNextId()));

            Element fname = doc.createElement("firstName");
            fname.appendChild(doc.createTextNode(firstName));
            newContact.appendChild(fname);
            
            Element lname = doc.createElement("lastName");
            lname.appendChild(doc.createTextNode(lastName));
            newContact.appendChild(lname);
            
            Element bDate = doc.createElement("birthDate");
            bDate.appendChild(doc.createTextNode(birthDate));
            newContact.appendChild(bDate);
            
            Element mail = doc.createElement("email");
            mail.appendChild(doc.createTextNode(email));
            newContact.appendChild(mail);
            
            Element tMobile = doc.createElement("telMobile");
            tMobile.appendChild(doc.createTextNode(telMobile));
            newContact.appendChild(tMobile);
            
            Element tHome = doc.createElement("telHome");
            tHome.appendChild(doc.createTextNode(telHome));
            newContact.appendChild(tHome);
            
            Element picture = doc.createElement("pic");
            picture.appendChild(doc.createTextNode(pic));
            newContact.appendChild(picture);

            root.appendChild(newContact);
            
            DOMSource source = new DOMSource(doc);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            StreamResult result = new StreamResult(getXml());
            transformer.transform(source, result);
            
            contactList = getContactsFromFile();
            
		} catch (Exception e) {
			System.out.println("ERROR IN ADD CONTACT : "+e);
		}
	}
	
	/**
	 * Get all contacts from the XML file contacts.xml
	 * @return Contact[]
	 */
	public Contact[] getContactsFromFile() {
		Contact[] contactsFF = null;
		int pic;
		Contact tempCon;
		
		try {
			
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(getXml());
			
			doc.getDocumentElement().normalize();
		    NodeList nList = doc.getElementsByTagName("contact");
		    contactsFF = new Contact[nList.getLength()];
		    
		    for (int temp = 0; temp < nList.getLength(); temp++) {
		    	
		        Node nNode = nList.item(temp);
		        //System.out.println("\nCurrent Element :" + nNode.getNodeName());
		        
		        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		        	
		            Element el = (Element) nNode;
		            
		            String dateTemp = el.getElementsByTagName("birthDate").item(0).getTextContent();
		            Date birthDate=new SimpleDateFormat("dd.MM.yyyy").parse(dateTemp);
		            
		            if(el.getElementsByTagName("pic").item(0).getTextContent() == "") {
		            	 tempCon = new Contact(
         						Integer.parseInt(el.getAttribute("id")), 
         						el.getElementsByTagName("firstName").item(0).getTextContent(),
         						el.getElementsByTagName("lastName").item(0).getTextContent(),
         						birthDate,
         						el.getElementsByTagName("email").item(0).getTextContent(),
         						el.getElementsByTagName("telMobile").item(0).getTextContent(),
         						el.getElementsByTagName("telHome").item(0).getTextContent()
         						);
		            } else {
		            	 tempCon = new Contact(
	         						Integer.parseInt(el.getAttribute("id")), 
	         						el.getElementsByTagName("firstName").item(0).getTextContent(),
	         						el.getElementsByTagName("lastName").item(0).getTextContent(),
	         						birthDate,
	         						el.getElementsByTagName("email").item(0).getTextContent(),
	         						el.getElementsByTagName("telMobile").item(0).getTextContent(),
	         						el.getElementsByTagName("telHome").item(0).getTextContent(),
	         						Integer.parseInt(el.getElementsByTagName("pic").item(0).getTextContent())
	         						);
		            }
		            contactsFF[temp] = tempCon;
		        }
		    }
		} catch (Exception e) {
			System.out.println("ERROR IN CONTACTS : "+e);
		}
		return contactsFF;
	}
	
	/**
	 * 
	 * @return Contact[] 
	 */
	public Contact[] getContactList() {
		return contactList;
	}
	
	/**
	 * Returns the array of contacts in alphabetical order
	 * @return Contact[]
	 */
	public Contact[] getContactListAlpha() {
		Contact[] tempC = contactList;
		int compare;
		Contact save;
		
		for(int i = 0; i < tempC.length; i++) {
			
			for(int j = i+1; j < tempC.length; j++) {
				
				compare = tempC[j].getLastName().toUpperCase().compareTo(tempC[i].getLastName().toUpperCase());
				
				if (compare == 0) {
					compare = tempC[j].getFirstName().toUpperCase().compareTo(tempC[i].getFirstName().toUpperCase());
				}
				
				if (compare == 0) {
					if(tempC[i].getId() > tempC[j].getId())
						compare = -1;
					else if (tempC[i].getId() < tempC[j].getId())
						compare = 1;
				}
				
				if(compare < 0) {
					save = tempC[i];
					tempC[i] = tempC[j];
					tempC[j] = save;
				}
				
			}
		}
		
		return tempC;
	}
	
	/**
	 * Sets the contacts array
	 * @param contacts
	 */
	public void setContacts(Contact[] contacts) {
		this.contactList = contacts;
	}
}
