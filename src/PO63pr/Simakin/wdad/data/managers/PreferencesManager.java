package PO63pr.Simakin.wdad.data.managers;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.*;
import java.util.List;
import java.util.Properties;

import static PO63pr.Simakin.wdad.utils.PreferencesManagerConstants.*;

public class PreferencesManager {
    private static final String APPCONFIG_PATH = "C:\\Users\\PSYSHO\\Desktop\\РВПРС\\starting-monkey-to-human-path\\src\\PO63pr\\Simakin\\wdad\\resources\\configuration\\appconfig.xml";
    private static PreferencesManager instance;

    private XPath xPath;
    private Document doc;

    private PreferencesManager(){
        XPathFactory xPathFactory = XPathFactory.newInstance();
        xPath = xPathFactory.newXPath();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
            doc = builder.parse(new FileInputStream(APPCONFIG_PATH));

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static PreferencesManager getInstance(){
        if(instance == null){
            instance = new PreferencesManager();
        }
        return instance;
    }

    public void setProperty(String key, String value){
        String exp = key.replace(".", "/");
        try {
            ((Node)xPath.evaluate(exp, doc, XPathConstants.NODE)).getFirstChild().setNodeValue(value);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
    }

    public String getProperty(String key){
        String exp = key.replace(".", "/");
        try {
            return ((Node)xPath.evaluate(exp, doc, XPathConstants.NODE)).getFirstChild().getNodeValue();
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setProperties(Properties prop){
        try{
            String exp;
            for(Object key : prop.keySet()){
                exp = key.toString().replace(".", "/");
                ((Node)xPath.evaluate(exp, doc, XPathConstants.NODE))
                        .getFirstChild().setNodeValue(prop.getProperty(key.toString()));
            }
            saveChanges();
        }catch (XPathExpressionException e) {
            e.printStackTrace();
        }
    }

    public Properties getProperties(){
        Properties prop = new Properties();
        prop.put(CREATEREGISTRY,
                getProperty(CREATEREGISTRY));
        prop.put(REGISTRYADDRESS,
                getProperty(REGISTRYADDRESS));
        prop.put(REGISTRYPORT,
                getProperty(REGISTRYPORT));
        prop.put(POLICYPATH,
                getProperty(POLICYPATH));
        prop.put(USECODEBASEONLY,
                getProperty(USECODEBASEONLY));
        prop.put(CLASSPROVIDER,
                getProperty(CLASSPROVIDER));
        prop.put(CLASSNAME,
                getProperty(CLASSNAME));
        prop.put(DRIVERTYPE,
                getProperty(DRIVERTYPE));
        prop.put(HOST_NAME,
                getProperty(HOST_NAME));
        prop.put(PORT,
                getProperty(PORT));
        prop.put(DBNAME,
                getProperty(DBNAME));
        prop.put(USER,
                getProperty(USER));
        prop.put(PASS,
                getProperty(PASS));
        return prop;
    }

    public void addBindedObject(String name, String className){
        try {
            Node reg = (Node)xPath.evaluate("appconfig/rmi/server", doc, XPathConstants.NODE);
            Element binobj = doc.createElement("bindedobject");
            binobj.setAttribute("class", className);
            binobj.setAttribute("name", name);
            reg.appendChild(binobj);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        saveChanges();
    }

    public void removeBindedObject(String name){
        try {
            Node reg = (Node)xPath.evaluate("appconfig/rmi/server", doc, XPathConstants.NODE);
            NodeList childs = reg.getChildNodes();
            Node current;
            for(int i = 0; i < childs.getLength(); i++){
                current = childs.item(i);
                if (current.hasAttributes()) {
                    if(current.getAttributes().getNamedItem("name").getTextContent().equals(name)){
                        reg.removeChild(childs.item(i));
                    }
                }
            }
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        saveChanges();
    }

    public String getBindedObjectName(String className){
        try {
            Node reg = (Node)xPath.evaluate("appconfig/rmi/server", doc, XPathConstants.NODE);
            NodeList childs = reg.getChildNodes();
            Node current;
            for(int i = 0; i < childs.getLength(); i++){
                current = childs.item(i);
                if (current.hasAttributes()) {
                    if(current.getAttributes().getNamedItem("class").getTextContent().equals(className)){
                        return current.getAttributes().getNamedItem("name").getTextContent();
                    }
                }
            }
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void saveChanges(){
        try {
            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer transformer = tFactory.newTransformer();
            DocumentType docType = doc.getDoctype();
            if(docType != null){
                String systemID = docType.getSystemId();
                String publicID = docType.getPublicId();
                transformer.setOutputProperty (OutputKeys.DOCTYPE_PUBLIC, systemID + publicID);
            }
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount","2");
            DOMSource dom_source = new DOMSource(doc);
            StreamResult out_stream = new StreamResult(APPCONFIG_PATH);
            transformer.transform (dom_source, out_stream);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

}
