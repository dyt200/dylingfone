package dylingfone;


import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

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
	
	public Pictures[] getImagesFromFile() {
		Pictures[] imagesFF = null;
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(super.xml);
			
			doc.getDocumentElement().normalize();
		    NodeList nList = doc.getElementsByTagName("image");
		    imagesFF = new Pictures[nList.getLength()];
		    
		    for (int temp = 0; temp < nList.getLength(); temp++) {
		        Node nNode = nList.item(temp);
		        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		        	
		            Element el = (Element) nNode;
		            Pictures tempImg = new Pictures(
		            						Integer.parseInt(el.getAttribute("id")), 
		            						el.getElementsByTagName("title").item(0).getTextContent(),
		            						el.getElementsByTagName("description").item(0).getTextContent(),
		            						el.getElementsByTagName("file").item(0).getTextContent()
		            						);
		            imagesFF[temp] = tempImg;
		        }
		    }
	    
		} catch (Exception e) {
			System.out.println("IN GALLERY ERROR : "+e);
		}
		return imagesFF;
	}
	
	public Pictures[] getImages() {
		return images;
	}
	public void setImages(Pictures[] images) {
		this.images = images;
	}
}
