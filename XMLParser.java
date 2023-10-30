import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Node;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLParser{
    private static File xmlFile;
    private static Node root;

    
    public static void parseXML(Node root){
        NodeList childNodes=root.getChildNodes();
        for(int i=0;i<childNodes.getLength();i++){
            Node childNode=childNodes.item(i);
            if(childNode.getNodeType()==Node.ELEMENT_NODE){
                parseXML(childNode);    
            }else{
                String text=childNode.getTextContent().trim();
                if(!text.isEmpty()){
                    System.out.println(text);
                }
            }
        }
    }
    static boolean checkFileExists(String fileName){
        xmlFile=new File(fileName);
        return xmlFile.exists();
    }
   
    static void parseXMLMain(String fileName){

        if(!checkFileExists(fileName)) return;

        try{
            DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder=factory.newDocumentBuilder();
            Document document=documentBuilder.parse(fileName);
            root=document.getDocumentElement();

        }catch(IOException io){
            io.printStackTrace();
        }catch(SAXException sax){
            sax.printStackTrace();
        }catch(ParserConfigurationException p){
            p.printStackTrace();
        }
        parseXML(root);        
    }

    static boolean checkFileNameValid(String fileName){
        if(fileName.isEmpty()){
            System.out.println("ERR:no input was found");
            return false;
        }
        System.out.println(fileName); 
        String[] fileNameContents=fileName.split(".");
        System.out.println(fileNameContents.getClass());
        System.out.println(fileNameContents.length);
        return false;
        /* 
        if(fileNameContents[1]!="xml"){
            System.out.println("ERR:wrong file extension");
            return false;
        }
        
        return true;
*/
    } 
    
    public static void main(String[] args){
        System.out.println("Enter the XML file name to be parsed (must be in the same working directory)");
       
        String fileName=new String();
        Scanner scanner=new Scanner(System.in);
        
        try{
            fileName=scanner.nextLine();
        }catch(NoSuchElementException  e){
            System.out.println("ERR:Nothing was found from inputStream-System.in");
            e.printStackTrace();
        }catch(IllegalStateException e){
            System.out.println("ERR:error reading from System.in (NOT_IN_STATE)");
            e.printStackTrace();
        }finally{
            scanner.close();
        }

        if(!checkFileNameValid(fileName)){
            return;
        }

        System.out.println("Parsing XML file...");
        parseXMLMain(fileName);
        System.out.println("bye");
    }
}