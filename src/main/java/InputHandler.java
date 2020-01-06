import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

class InputHandler {
    public static File parseFileName(String fileName) throws Exception {
        int extensionIndex = fileName.lastIndexOf('.');
        if (extensionIndex == -1 || !fileName.substring(extensionIndex).equals(".xml")) {
            throw new Exception("Invalid file format.");
        }
        File inputFile = new File(fileName);
        if (inputFile.exists()) {
            return inputFile;
        } else {
            String message = String.format("File %s not found.", fileName);
            throw new Exception(message);
        }
    }

    public static List<Element> getXmlElements(File inputFile) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = factory.newDocumentBuilder();
        Document document = docBuilder.parse(inputFile);
        Element root = document.getDocumentElement();
        NodeList nodeList = root.getChildNodes();
        List<Element> elements = new ArrayList<>();

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node currentNode = nodeList.item(i);
            if (currentNode.getNodeType() == Document.ELEMENT_NODE) {
                elements.add((Element)currentNode);
            }
        }
        return elements;
    }
}
