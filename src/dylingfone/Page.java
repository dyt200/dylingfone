package dylingfone;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class Page {
	
	protected File xml;
	protected String[] path;
	
	public void formatXml() {
		try {
			String file = xml.getAbsolutePath();
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		    DocumentBuilder builder = factory.newDocumentBuilder();
		    
			Document document = builder.parse(new InputSource(new InputStreamReader(new FileInputStream(file))));
	
		    Transformer xformer = TransformerFactory.newInstance().newTransformer();
		    xformer.setOutputProperty(OutputKeys.METHOD, "xml");
		    xformer.setOutputProperty(OutputKeys.INDENT, "yes");
		    xformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
		    xformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		    Source source = new DOMSource(document);
		    javax.xml.transform.Result result = new StreamResult(new File(file));
		    xformer.transform(source, result);
		} catch (Exception e) {
			System.out.println("ERROR IN FORMAT XML : "+e);
		}
	}
	
	public int getNextId() {
		int key = 0;
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(xml);
			XPathFactory xPathfactory = XPathFactory.newInstance();
			XPath xpath = xPathfactory.newXPath();
			XPathExpression expr = xpath.compile("/"+path[0]+"/"+path[1]+"/@id[not(. < ../../"+path[1]+"/@id)][1]");
			NodeList nl = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
			
			key = Integer.parseInt(nl.item(0).getNodeValue());
		} catch (Exception e) {
			System.out.println("ERROR IN GET NEXT XML ID : "+e);
		}
		return (key+1);
	}
	
	public void addXmlElements(String[][] elements) {
		String id = Integer.toString(getNextId());
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xml);
	        Element root = doc.getDocumentElement();
	        Element newElement = doc.createElement(path[1]);
	        newElement.setAttribute("id", id);
	        
	        for(int i = 0; i < elements.length; i++) {
		        Element element = doc.createElement(elements[i][0]);
	            element.appendChild(doc.createTextNode(elements[i][1]));
	            newElement.appendChild(element);
	        }
	        
	        root.appendChild(newElement);
            
            DOMSource source = new DOMSource(doc);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            StreamResult result = new StreamResult(xml);
            transformer.transform(source, result);
            
            formatXml();
            
		} catch (Exception e) {
			System.out.println("ERROR IN ADD XML ELEMENTS : "+e);
		}
	}
	
	//needs finishing, try with getByElementId or w/e it was called again, delete the xPath stuff
	/*public void deleteElementById(int id) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(xml);
			XPathFactory xPathfactory = XPathFactory.newInstance();
			XPath xpath = xPathfactory.newXPath();
			XPathExpression expr = xpath.compile("/"+path[0]+"/"+path[1]+"[@id = '"+id+"']");
			NodeList nl = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
			
			for(int i = 0; i < nl.getLength(); i++) {
				nl[i]
			}
			element.getParentNode().removeChild(element);
		} catch(Exception e) {
			System.out.println("ERROR IN DEL BY ID : "+e);
		}
	}*/
	
	public File getXml() {
		return xml;
	}
	public void setXml(File xml) {
		this.xml = xml;
	}
	public String[] getPath() {
		return path;
	}
	public void setPath(String[] path) {
		this.path = path;
	}
}
