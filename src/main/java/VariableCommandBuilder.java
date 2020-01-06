import java.util.HashMap;
import org.w3c.dom.Element;

class VariableCommandBuilder extends CommandBuilder {
    private static final String ID_TAG = "data:id";
    private static final String VALUE_TAG = "data:value";

    public VariableCommandBuilder() {
        possibleTags = new HashMap<>();
        possibleTags.put(ID_TAG, new IntRange(1, 1));
        possibleTags.put(VALUE_TAG, new IntRange(1, 1));
    }

    @Override
    public Command build(Element element) throws Exception {
        checkTags(element);
        String id = element.getElementsByTagName(ID_TAG).item(0).getTextContent();
        String stringValue = element.getElementsByTagName(VALUE_TAG).item(0).getTextContent();
        try {
            int intValue = Integer.parseInt(stringValue);
            return new VariableCommand(id, intValue);
        } catch (NumberFormatException e) {
            String message = String.format("Non integer parameter inside tag <%s>.", element.getTagName());
            throw new Exception(message);
        }
    }
}
