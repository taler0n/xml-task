import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.w3c.dom.Element;

public class ProgramBuilder {
    private Map<String, CommandBuilder> builderMap;

    public ProgramBuilder() {
        builderMap = new HashMap<>();
        builderMap.put("data:variable", new VariableCommandBuilder());
        builderMap.put("data:add", new AddCommandBuilder());
        builderMap.put("data:print", new PrintCommandBuilder());
    }

    public Program build(List<Element> xmlElements) throws Exception {
        Program program = new Program();
        for (Element element: xmlElements) {
            String tagName = element.getTagName();
            if (!builderMap.containsKey(tagName)) {
                String message = String.format("Unknown tag <%s>.", tagName);
                throw new Exception(message);
            }
            CommandBuilder builder = builderMap.get(tagName);
            Command command = builder.build(element);
            program.addCommand(command);
        }
        return program;
    }
}
