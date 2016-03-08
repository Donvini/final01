package navi;

import navi.commandline.Commandline;
import navi.serializer.FileInputHelper;
import navi.serializer.Serializer;

/**
 * @author Vincenzo Pace | KIT
 * @version 1.0
 */
public final class MainClass {

    /**
     * privater Konstuktor um Instantiierung zu verhindern
     */
    private MainClass() {

    }

    /**
     * Die Hauptmethode, die das ganze Programm ins Rollen bringt
     * @param args Der Pfad zur Textdatei mit den Infos über den Graph
     */
    public static void main(String[] args) {
        try {
            String path = args[0];
            String[] lines = FileInputHelper.read(path);
            System.out.println(Serializer.validate(lines));
            if( Serializer.validate(lines))
                Commandline.navigationUp(Serializer.initializeGraph(lines));
        } catch (IllegalArgumentException e) {
            System.exit(1);
        }
    }
}
