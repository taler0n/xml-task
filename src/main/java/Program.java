import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class Program {
    private List<Command> commands;

    public Program() {
        commands = new ArrayList<>();
    }

    public void addCommand(Command command) {
        commands.add(command);
    }

    public void checkGrammar() throws Exception {
        Set<String> variables = new HashSet<>();
        for (int i = 0; i < commands.size(); i++) {
            commands.get(i).check(variables, i);
        }
    }

    public void run() {
        Map<String, Integer> variables = new HashMap<>();
        for (Command command : commands) {
            command.run(variables);
        }
    }
}
