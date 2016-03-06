package final01;

import final01.Commandline.Commandline;
import final01.Commandline.Terminal;
import final01.Graph.MapGraph;
import final01.Serializer.FileInputHelper;
import final01.Serializer.Serializer;

/**
 * @author Vincenzo Pace | KIT
 * @version 1.0
 */
public class MainClass {
    private MainClass() {

    }
    public static void main(String[] args) {
        try {
            String path = args[0];
            String[] lines = FileInputHelper.read(path);
            Commandline.navigationUp(Serializer.initializeGraph(lines));
        } catch (IllegalArgumentException e) {
            System.exit(1);
        } catch (IndexOutOfBoundsException e) {
            Terminal.printLine("Error, No argument was given.");
            System.exit(1);
        }
    }
}
