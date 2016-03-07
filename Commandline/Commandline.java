package final01.commandline;

import final01.graph.Graph;
import final01.graph.MapGraph;

/**
 * @author Vincenzo Pace | KIT
 * @version 1.0
 */
public final class Commandline {

    private static final String SEARCH = "search";

    private static final String ROUTE = "route";

    private static final String REMOVE = "remove";

    private static final String INSERT = "insert";

    private static final String INFO = "info";

    private static final String NODES = "nodes";

    private static final String VERTICES = "vertices";

    private static final String QUIT = "quit";

    private enum criterion {TIME, ROUTE, OPTIMAL, ALL}


    private Commandline() {
    }


    public static void navigationUp(Graph graph) {
        while (true) {
           try {
               String commands = Terminal.readLine();
               if (commands == null)
                   throw new IllegalArgumentException("Error,invalid input.");
               String[] parts = commands.split("\\s", 5);
               switch (parts[0]) {
                   case SEARCH:
                       break;
                   case REMOVE:
                       break;
                   case ROUTE:
                       break;
                   case INFO:
                       break;
                   case INSERT:
                       break;
                   case NODES:
                       break;
                   case VERTICES:
                       break;
                   case QUIT:
                       quit();
                       break;
                   default:
                       throw new IllegalArgumentException("Error,invalid input.");
               }
           } catch (IllegalArgumentException e) {
               Terminal.printLine(e.getMessage());
           }
        }
    }
    /**
     * Diese Methode beendet das Programm.
     */
    private static void quit() {
        System.exit(0);
    }
}
