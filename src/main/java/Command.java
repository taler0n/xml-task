import java.util.Map;
import java.util.Set;

interface Command {
    void check(Set<String> variableIds, int index) throws Exception;
    void run(Map<String, Integer> variables);
}
