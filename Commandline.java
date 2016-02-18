package final01;

/**
 * @author Vincenzo Pace | KIT
 * @version 1.0
 */
public final class Commandline {

    public static final String SEARCH = "search";

    public static final String ROUTE = "route";

    public static final String REMOVE = "remove";

    public static final String INSERT = "insert";

    public static final String INFO = "info";

    public static final String NODES = "nodes";

    public static final String VERTICES = "vertices";

    public static final String QUIT = "quit";

    public enum criterion {TIME, ROUTE, OPTIMAL, ALL}


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
                       graph.search(graph.getVertex()parts[1], parts[2], parts[3]);
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
    public static void quit() {
        System.exit(0);
    }
}
