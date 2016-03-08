package navi.commandline;

import navi.graph.Graph;

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
                       graph.nodes(parts[1]);
                       break;
                   case VERTICES:
                       graph.vertices();
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
