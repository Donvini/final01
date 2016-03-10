package edu.kit.informatik;

import edu.kit.informatik.commandline.Terminal;
import edu.kit.informatik.serializer.Serializer;
import edu.kit.informatik.commandline.Commandline;
import edu.kit.informatik.exceptions.FileSyntaxException;
import edu.kit.informatik.exceptions.GraphSyntaxException;
import edu.kit.informatik.serializer.FileInputHelper;

/**
 * @author Vincenzo Pace | KIT
 * @version 1.0
 */
public final class MainClass {

    /**
     * privater Konstuktor um Instantiierung zu verhindern
     */
    private MainClass() { }
    /**
     * Die Hauptmethode, die das ganze Programm ins Rollen bringt
     * @param args Der Pfad zur Textdatei mit den Infos über den Graph
     */
    public static void main(String[] args) {
        try {
            String path = args[0];
            String[] lines = FileInputHelper.read(path);
            System.out.println(Serializer.validate(lines));
            if (Serializer.validate(lines))
                Commandline.navigationUp(Serializer.initializeGraph(lines));
        }  catch (FileSyntaxException e) {
            Terminal.printLine("Error, " + e.getMessage());
            System.exit(1);
        } catch (GraphSyntaxException e) {
            Terminal.printLine("Error, " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.exit(1);
        }
    }
}