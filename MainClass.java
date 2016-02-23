package final01;

import final01.Commandline.Terminal;
import final01.Graph.MapGraph;
import final01.Serializer.FileInputHelper;
import final01.Serializer.Serializer;

import java.io.FileNotFoundException;

/**
 * @author Vincenzo Pace | KIT
 * @version 1.0
 */
public class MainClass {
    public static void main(String[] args) {
            String path = args[0];
            String[] lines = FileInputHelper.read(path);
            MapGraph world = new MapGraph(Serializer.cities(lines), Serializer.connections(lines));
    }
}
