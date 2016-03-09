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

    /**
     * Die Kriterien, nach denen gesucht werden kann.
     */
    private enum criterion { TIME, ROUTE, OPTIMAL, ALL }


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
                       if (parts.length != 1)
                           throw new UserInputException("info takes no arguments.");
                       graph.info();
                       break;
                   case INSERT:
                       if (parts.length != 5)
                           throw new UserInputException("insert needs exactly 4 arguments separated by spaces.");
                       graph.insertEdge(parts[1], parts[2], parts[3], parts[4]);
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
