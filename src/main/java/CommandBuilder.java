import java.util.HashMap;
import java.util.Map;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

abstract class CommandBuilder {
    protected Map<String, IntRange> possibleTags;
    public abstract Command build(Element element) throws Exception;

    protected void checkTags(Element element) throws Exception {
        Map<String, Integer> tagCount = new HashMap<>();
        NodeList nodes = element.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element child = (Element)node;
                String tagName = child.getTagName();
                if (possibleTags.containsKey(tagName)) {
                    if (tagCount.containsKey(tagName)) {
                        int count = tagCount.get(tagName) + 1;
                        tagCount.put(tagName, count);
                    }
                    else {
                        tagCount.put(tagName, 1);
                    }
                } else {
                    String message = String.format("Invalid tag <%s> inside tag <%s>.", tagName, element.getTagName());
                    throw new Exception(message);
                }
            }
        }
        for (Map.Entry<String, IntRange> requiredTag : possibleTags.entrySet()) {
            String tagName = requiredTag.getKey();
            if (!tagCount.containsKey(tagName)) {
                String message = String.format("Missing tag <%s> inside tag <%s>.", tagName, element.getTagName());
                throw new Exception(message);
            }
            if (!possibleTags.get(tagName).contains(tagCount.get(tagName))) {
                String message = String.format("Wrong number of tag <%s> inside tag <%s>.", tagName, element.getTagName());
                throw new Exception(message);
            }
        }
    }
}
