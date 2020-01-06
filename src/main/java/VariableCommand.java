import java.util.Map;
import java.util.Set;

class VariableCommand implements Command {
    private String variableId;
    private int value;

    public VariableCommand(String variableId, Integer value) {
        this.variableId = variableId;
        this.value = value;
    }

    @Override
    public void check(Set<String> variables, int index) throws Exception {
        if (variables.contains(variableId)) {
            String message = String.format("Variable \"%s\" already exists for command №%d", variableId, index);
            throw new Exception(message);
        }
        variables.add(variableId);
    }

    @Override
    public void run(Map<String, Integer> variables) {
        variables.put(variableId, value);
    }
}
