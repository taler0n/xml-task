import java.util.Map;
import java.util.Set;

class PrintCommand implements Command {
    private String variableId;

    public PrintCommand(String variableId) {
        this.variableId = variableId;
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
        int value = variables.get(variableId);
        System.out.println(value);
    }
}
