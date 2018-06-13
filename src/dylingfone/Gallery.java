package dylingfone;


import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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



public class Gallery extends Page {
	
	private Pictures[] images;
	
	public Gallery() { 
		String[] path = {"gallery", "image"};
		super.setXml(new File("./gallery.xml"));
		super.setPath(path);
		this.images = getImagesFromFile();
	}
	
	public void dumpData() {
		System.out.println("START OF DATA : ");
		System.out.println();
		for (int i = 0; i < this.images.length; i++) {
		   System.out.println("=====================");
		   System.out.println("id : "+this.images[i].getId());
		   System.out.println("title : "+this.images[i].getTitle());
		   System.out.println("desc : "+this.images[i].getDescription());
		   System.out.println("file : "+this.images[i].getPath());
		   System.out.println("=====================");
		}
	}
	
	public void addPicture(String title, String desc, String path) {
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(super.xml);
	        Element root = doc.getDocumentElement();
	        
	        Element newPic = doc.createElement("image");
	        newPic.setAttribute("id",  Integer.toString(super.getNextId()));

            Element titleE = doc.createElement("title");
            titleE.appendChild(doc.createTextNode(title));
            newPic.appendChild(titleE);
            
            Element description = doc.createElement("description");
            description.appendChild(doc.createTextNode(desc));
            newPic.appendChild(description);
            
            Element file = doc.createElement("file");
            file.appendChild(doc.createTextNode(path));
            newPic.appendChild(file);
            
            root.appendChild(newPic);
            
            DOMSource source = new DOMSource(doc);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            StreamResult result = new StreamResult(getXml());
            transformer.transform(source, result);
            
            images = getImagesFromFile();
		} catch (Exception e) {
			System.out.println("ERROR IN ADD CONTACT : "+e);
		}
	}
	
	public void editPicture(int id, String element, String newValue) {
		int tempId;
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(super.xml);
			
			doc.getDocumentElement().normalize();
		    NodeList list = doc.getElementsByTagName("image");
		    
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
			        
			        images = getImagesFromFile();
			    }
		    }
		} catch(Exception e) {
			System.out.println("ERROR IN EDITCONTACT() : "+e);
		}
	}
	
	public void deletePicture(int id) {
		int tempId;
		Node tempNode;
		
		try {
			
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(super.xml);
	        
	        doc.getDocumentElement().normalize();
		    NodeList list = doc.getElementsByTagName("image");
		    
		    for (int i = 0; i < list.getLength(); i++) {
		    	
		    	tempId = -1;
			    Element image = (Element)list.item(i);
			  
			    tempId = Integer.parseInt(image.getAttribute("id"));
			    System.out.println("id = "+id+" AND tempId = "+tempId);
			    if (id == tempId) {
			    	System.out.println("FOUND ID");
			    	//Selects node and file
			    	tempNode=list.item(i);
			    	NodeList listN = image.getElementsByTagName("file");
			    	NodeList subList = listN.item(0).getChildNodes();
			    	
			    	Path path = Paths.get(subList.item(0).getNodeValue());
			    	System.out.println("PATH = "+path);
			    	Files.deleteIfExists(path);
			    	
			        tempNode.getParentNode().removeChild(image);
			        DOMSource source = new DOMSource(doc);
			        
			        //changes the actual file
		            TransformerFactory transformerFactory = TransformerFactory.newInstance();
		            Transformer transformer = transformerFactory.newTransformer();
		            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

		            StreamResult result = new StreamResult(getXml());
		            transformer.transform(source, result);
		            
		            //reloads the data after modifications
			        images = getImagesFromFile();
			       
			    } 
		    }
		} catch(Exception e) {
			System.out.println("ERROR IN deleteContact : "+e);
		}
	}
	
	public Pictures[] getImagesFromFile() {
		Pictures[] imagesFF = null;
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(super.xml);
			
			doc.getDocumentElement().normalize();
		    NodeList nList = doc.getElementsByTagName("image");
		    imagesFF = new Pictures[nList.getLength()-1];
		    
		    for (int temp = 1; temp < nList.getLength(); temp++) {
		        Node nNode = nList.item(temp);
		        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		        	
		            Element el = (Element) nNode;
		            Pictures tempImg = new Pictures(
		            						Integer.parseInt(el.getAttribute("id")), 
		            						el.getElementsByTagName("title").item(0).getTextContent(),
		            						el.getElementsByTagName("description").item(0).getTextContent(),
		            						el.getElementsByTagName("file").item(0).getTextContent()
		            						);
		            imagesFF[temp-1] = tempImg;
		        }
		    }
	    
		} catch (Exception e) {
			System.out.println("IN GALLERY ERROR : "+e);
		}
		return imagesFF;
	}
	
	public String getPathFromId(int id) {
		int tempId;
		for(int i = 0; i < images.length; i++) {
			tempId = i;
			if(tempId == id) 
				return images[i].getPath();
		}
		return "/placeholder.jpg";
	}
	
	public Pictures[] getImages() {
		return images;
	}
	public void setImages(Pictures[] images) {
		this.images = images;
	}
}
