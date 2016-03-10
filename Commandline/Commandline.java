package edu.kit.informatik.commandline;

import edu.kit.informatik.exceptions.InvalidOperationException;
import edu.kit.informatik.exceptions.NoSuchEntryException;
import edu.kit.informatik.algorithms.Dijkstra;
import edu.kit.informatik.exceptions.UserInputException;
import edu.kit.informatik.graph.MapGraph;
import edu.kit.informatik.graph.Vertex;

import java.util.ArrayList;

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
    private static final String ALL = "all";
    private static final String OPTIMAL = "optimal";
    private static final String TIME = "time";

    /**
     * Privater Konstruktor um Instantiierung zu verhindern.
     */
    private Commandline() {
    }

    /**
     * Hauptmethode der Klasse um die Nutzereingaben entgegenzunehmen
     *
     * @param graph auf diesem Graph sollen alle operationen ausfgeführt werden
     */
    public static void navigationUp(MapGraph graph) {
        while (true) {
            try {
                String commands = Terminal.readLine().toLowerCase();
                if (commands.length() == 0)
                    throw new IllegalArgumentException("you have to enter an argument.");
                String[] parts = commands.split("\\s", 2);
                switch (parts[0]) {
                    case Commandline.REMOVE:
                        remove(graph, parts[1]);
                        break;
                    case Commandline.SEARCH:
                        searchValue(graph, parts[1]);
                        break;
                    case Commandline.ROUTE:
                        searchRoute(graph, parts[1]);
                        break;
                    case Commandline.INFO:
                        if (parts.length != 1)
                            throw new UserInputException("info takes no arguments.");
                        graph.info();
                        break;
                    case Commandline.INSERT:
                        insert(graph, parts[1]);
                        break;
                    case Commandline.NODES:
                        if (parts.length != 2)
                            throw new UserInputException("nodes needs exactly one argument.");
                        graph.nodes(parts[1]);
                        break;
                    case Commandline.VERTICES:
                        if (parts.length != 1)
                            throw new UserInputException("vertices takes no arguments.");
                        graph.vertices();
                        break;
                    case Commandline.QUIT:
                        quit();
                        break;
                    default:
                        throw new IllegalArgumentException(" invalid input.");
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

    private static void remove(MapGraph g, String commands) throws InvalidOperationException,
            UserInputException , NoSuchEntryException {
        String[] parts = commands.split(";");
        if (parts.length != 2) {
            throw new UserInputException("remove needs 2 arguments");
        }
        g.remove(parts[0], parts[1]);
    }

    private static void insert(MapGraph g, String commands)
            throws InvalidOperationException, NoSuchEntryException, UserInputException {
        String[] parts = commands.split(";");
        if (parts.length != 4)
            throw new UserInputException("insert needs exactly 4 arguments.");
        g.insertEdge(parts[0], parts[1], parts[2], parts[3]);
    }

    private static void searchValue(MapGraph g, String command) throws UserInputException, NoSuchEntryException {
        String[] parts = command.split(";");
        if (parts.length != 3)
            throw new UserInputException("search needs 3 arguments");
        if (!(parts[2].equalsIgnoreCase(Commandline.ROUTE) || parts[2].equalsIgnoreCase(Commandline.TIME)
        || parts[2].equalsIgnoreCase(Commandline.OPTIMAL)))
            throw new UserInputException("criterion has to be route, time or optimal");
        Dijkstra.shortestPath(g, g.getVertexByName(parts[0]), parts[2]);
        if (!g.getVertices().contains(g.getVertexByName(parts[1])))
            throw new  NoSuchEntryException("Node does not exist!");
        Terminal.printLine(String.valueOf(g.getVertexByName(parts[1]).getDistance()));
    }

    private static void searchRoute(MapGraph g, String command) throws UserInputException, NoSuchEntryException {
        String[] parts = command.split(";");
        if (parts.length != 3)
            throw new UserInputException("route needs 3 arguments");
        if (!(g.getVertices().contains(g.getVertexByName(parts[1]))
                || g.getVertices().contains(g.getVertexByName(parts[2]))))
            throw new NoSuchEntryException("At least one of the nodes does not exist!");
        if (parts[2].equalsIgnoreCase(Commandline.ROUTE) || parts[2].equalsIgnoreCase(Commandline.TIME)) {
            Dijkstra.shortestPath(g, g.getVertexByName(parts[0]), parts[2]);
            ArrayList<Vertex> path = Dijkstra.getShortestPathTo(g.getVertexByName(parts[1]));
            String output = "";
            for (Vertex element : path) {
                output += element.getName() + " ";
            }
            Terminal.printLine(output);
        }
        else if (parts[2].equalsIgnoreCase(ALL))
            g.searchAllPaths(parts[0], parts[1]);
        else
            throw new UserInputException("invalid criterion entered.");
    }
}
