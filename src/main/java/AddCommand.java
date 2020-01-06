import java.util.Map;
import java.util.Set;

class AddCommand implements Command {
    private String variableId;
    private int value;

    public AddCommand(String variableId, int value) {
        this.variableId = variableId;
        this.value = value;
    }

    @Override
    public void check(Set<String> variables, int index) throws Exception {
        if (!variables.contains(variableId)) {
            String message = String.format("Variable \"%s\" doesn't exist for command #%d", variableId, index);
            throw new Exception(message);
        }
    }

    @Override
    public void run(Map<String, Integer> variables) {
        int newValue = variables.get(variableId) + value;
        variables.put(variableId, newValue);
    }
}
