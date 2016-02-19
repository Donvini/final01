package final01.Serializer;

/**
 * @author Vincenzo Pace | KIT
 * @version 1.0
 */
public class Serializer {
    /**
     *
     */
    private static final String REGEX_EDGE = "(?:(?:[A-Za-z0-9_-]+)|(?:\\s+))+";
    /**
     *
     */
    private static final String REGEX_CITY = "[A-Za-z-]+";

    public String[] cities(String[] worldMap) {
        String[] cities = new String[worldMap.length];
        int i = 0;
        while (!worldMap[i].equalsIgnoreCase("--") && i < worldMap.length && validate(worldMap)) {
            cities[i] = worldMap[i];
            i++;
        }
        return cities;
    }

    public boolean validate(String[] worldMap) {
        int i = 0;
        while (!worldMap[i].equalsIgnoreCase("--") && i < worldMap.length) {
            if(!worldMap[i].matches(REGEX_CITY))
                return false;
            i++;
        }
        i++;
        while (i < worldMap.length) {
            if(!worldMap[i].matches(REGEX_EDGE))
                return false;
            i++;
        }
        return true;
    }

    public String[] connections(String[] worldMap) {
        String[] connections = new String[worldMap.length];
        int i;
        int j = 0;
        for (i = 0; i < worldMap.length; i++) {
            if (worldMap[i].equals("--")) {
                connections[j] = worldMap[i];
                j++;
            }
        }
        return connections;
    }
}