package navi.commandline;

import navi.exceptions.InvalidOperationException;
import navi.exceptions.NoSuchEntryException;
import navi.exceptions.UserInputException;
import navi.graph.MapGraph;

/**
 * Die Klasse, die für die Nutzereingaben benutzt wird
 * @author Vincenzo Pace | KIT
 * @version 1.0
 */
public final class Commandline {

    /**
     * Die zugehörigen Strings zu den akzeptierten Befehlen
     */
    private static final String SEARCH = "search";
    private static final String ROUTE = "route";
    private static final String REMOVE = "remove";
    private static final String INSERT = "insert";
    private static final String INFO = "info";
    private static final String NODES = "nodes";
    private static final String VERTICES = "vertices";
    private static final String QUIT = "quit";
    private static final String TIME = "time";
    private static final String OPTIMAL = "optimal";
    private static final String ALL = "all";




    /**
     * Privater Konstruktor um Instantiierung zu verhindern.
     */
    private Commandline() {

    }


    /**
     * Hauptmethode der Klasse um die Nutzereingaben entgegenzunehmen
     * @param graph auf diesem Graph sollen alle operationen ausfgeführt werden
     */
    public static void navigationUp(MapGraph graph)  {
        while (true) {
           try {
               String commands = Terminal.readLine().toLowerCase();
               if (commands.length() == 0)
                   throw new IllegalArgumentException("you have to enter an argument.");
               String[] parts = commands.split("\\s", 2);
               switch (parts[0]) {
                   case SEARCH:
                       break;
                   case REMOVE:
                       String[] removeParts = parts[1].split(";");
                       if (removeParts.length != 2)
                           throw new UserInputException("remove takes 2 arguments separated by semicolon.");
                       graph.remove(removeParts[0], removeParts[1]);
                       break;
                   case ROUTE:
                       String[] routeParts = parts[1].split(";");
                       if (routeParts.length != 3)
                           throw new UserInputException("route takes 3 arguments separated by semicolon.");
                       switch (routeParts[2]) {
                           case TIME:
                               break;
                           case ROUTE:
                               break;
                           case OPTIMAL:
                               break;
                           case ALL:
                               graph.deepNiko(routeParts[0], routeParts[1]);
                               break;
                           default:
                               throw new UserInputException("route expects one of the 4 criterions: "
                                       + "time, route, optimal or all");
                       }
                       break;
                   case INFO:
                       if (parts.length != 1)
                           throw new UserInputException("info takes no arguments.");
                       graph.info();
                       break;
                   case INSERT:
                       String[] insertParts = parts[1].split(";");
                       if (insertParts.length != 4)
                           throw new UserInputException("insert needs exactly 4 arguments separated by spaces.");
                       graph.insertEdge(insertParts[0], insertParts[1],
                               insertParts[2], insertParts[3]);
                       break;
                   case NODES:
                       if (parts.length != 2)
                           throw new UserInputException("nodes needs exactly one argument:"
                                   + " the node which neighbours you want.");
                       graph.nodes(parts[1]);
                       break;
                   case VERTICES:
                       if (parts.length != 1)
                        throw new UserInputException("vertices takes no arguments.");
                       graph.vertices();
                       break;
                   case QUIT:
                       quit();
                       break;
                   default:
                       throw new IllegalArgumentException("Error,invalid input. "
                       + "Valid inputs are: " + SEARCH + ", " + REMOVE
                              + ", " + ROUTE + ", " + INSERT + ", "
                               + INFO + ", " + NODES + ", " + VERTICES
                               + ", " + QUIT + ".");
               }
           } catch (UserInputException | NoSuchEntryException
                   | InvalidOperationException | IllegalArgumentException e) {
               Terminal.printLine("Error, " + e.getMessage());
           } catch (ArrayIndexOutOfBoundsException e) {
               Terminal.printLine("Arguments have to be separated by spaces.");
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
