import java.util.HashMap;
import org.w3c.dom.Element;

class PrintCommandBuilder extends CommandBuilder {
    private static final String ID_TAG = "data:data-id";

    public PrintCommandBuilder() {
        possibleTags = new HashMap<>();
        possibleTags.put(ID_TAG, new IntRange(1, 1));
    }

    @Override
    public Command build(Element element) throws Exception {
        checkTags(element);
        String id = element.getElementsByTagName(ID_TAG).item(0).getTextContent();
        return new PrintCommand(id);
    }
}
