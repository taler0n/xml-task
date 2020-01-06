import java.io.File;
import java.util.List;
import org.w3c.dom.Element;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please, enter XML file name as argument.");
            return;
        }
        Program program;
        try {
            File inputFile = InputHandler.parseFileName(args[0]);
            List<Element> xmlElements = InputHandler.getXmlElements(inputFile);
            ProgramBuilder programBuilder = new ProgramBuilder();
            program = programBuilder.build(xmlElements);
            program.checkGrammar();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        program.run();
    }
}
