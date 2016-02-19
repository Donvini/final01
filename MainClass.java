package final01;

import final01.Serializer.FileInputHelper;

/**
 * @author Vincenzo Pace | KIT
 * @version 1.0
 */
public class MainClass {
    public static void main(String[] args) {
        String path = args[0];
        String[] lines = FileInputHelper.read(path);
    }
}
