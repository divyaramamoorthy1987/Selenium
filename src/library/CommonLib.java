package library;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.WrapsDriver;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class CommonLib {

    public static String getPath(String app, String element, String identifier) {
        try {
            //create Document object using XPathList.xml file
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new File(".src/xpaths.xml"));

            //get app node list
            NodeList nodeList = doc.getElementsByTagName(app);
            //getting first node from list
            Node node = nodeList.item(0);
            //if node is element node
            if (node.getNodeType() == Node.ELEMENT_NODE) {

                Element elementNode = (Element) node;
                //get node list given by @element
                NodeList childNodes = elementNode.getElementsByTagName(element);
                //loop through all the @element nodes
                for (int i = 0; i < childNodes.getLength(); i++) {
                    //fetch @element node
                    Node childNode = childNodes.item(i);
                    //if @element node is element node
                    if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                        //fetch all the attributes nodes of @element node
                        NamedNodeMap attributeNodes = childNode.getAttributes();
                        //loop through all the attributes nodes
                        for (int j = 0; j < attributeNodes.getLength(); j++) {
                            //fetch attribute node
                            Node attributeNode = attributeNodes.item(j);
                            //if attributeNode is attribute node
                            if (attributeNode.getNodeType() == Node.ATTRIBUTE_NODE) {
                                //check node name with @identifier and return childNode value
                                if (attributeNode.getNodeValue().equals(identifier)) {
                                    return childNode.getTextContent();
                                }
                            }
                            //if atttributeNode is not an attribute node, print on console and exit
                            else {
                                System.out.println("#####" + attributeNode + "is not a attribute node" + "#######");
                            }
                        }
                    }
                    //if @element node is not an element node, print on console and exit
                    else {
                        System.out.println("#####" + childNode + "is not a element node" + "#######");
                    }
                }

            }
            //if node is not an element node, print on console and exit
            else {
                System.out.println("#####" + node + "is not a element node" + "#######");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static ArrayList<String> getQueries() throws Exception {
        String query = null;
        ArrayList<String> allQueries = new ArrayList<String>();
        BufferedReader bw = new BufferedReader(
                new FileReader(
                        new File(
                                "src/queries.properties")));
        while ((query = bw.readLine()) != null) {
            allQueries.add(query.toString().trim());
                System.out.println(query);
        }
        bw.close();
        return allQueries;
    }
}
