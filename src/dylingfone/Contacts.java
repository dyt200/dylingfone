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
// HELLO WORLDD
public class Contacts extends Page{
	private Contact[] contactList;
	
	
	public Contacts() {	
		String[] path = {"contacts", "contact"};
		super.setXml(new File("./contacts.xml"));
		super.setPath(path);
		this.contactList = getContactsFromFile();
	}
	
	public void dumpData() {
		System.out.println("START OF CONTACTS DATA : ");
		System.out.println();
		for (int i = 0; i < contactList.length ; i++) {
		
		   System.out.println("=====================");
		   System.out.println("Pointer :" + i );
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
	
	public void addContact(String firstName, String lastName, String birthDate, String email, String telMobile, String telHome) {
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
	
	public Contact[] getContactsFromFile() {
		Contact[] contactsFF = null;
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
		            Contact tempCon = new Contact(
		            						Integer.parseInt(el.getAttribute("id")), 
		            						el.getElementsByTagName("firstName").item(0).getTextContent(),
		            						el.getElementsByTagName("lastName").item(0).getTextContent(),
		            						birthDate,
		            						el.getElementsByTagName("email").item(0).getTextContent(),
		            						el.getElementsByTagName("telMobile").item(0).getTextContent(),
		            						el.getElementsByTagName("telHome").item(0).getTextContent()
		            						);
		            
		            contactsFF[temp] = tempCon;
		        }
		    }
		} catch (Exception e) {
			System.out.println("ERROR IN CONTACTS : "+e);
		}
		return contactsFF;
	}

	public Contact[] getContactList() {
		return contactList;
	}
	
	public void setContacts(Contact[] contacts) {
		this.contactList = contacts;
	}
}
