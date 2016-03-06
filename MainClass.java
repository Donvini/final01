package final01;

import final01.commandline.Commandline;
import final01.commandline.Terminal;
import final01.serializer.FileInputHelper;
import final01.serializer.Serializer;

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
