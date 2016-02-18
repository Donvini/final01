package final01;
import java.util.regex.*;
/**
 * @author Vincenzo Pace | KIT
 * @version 1.0
 */
public class Serializer {
    /**
     * Regex matching a valid word and an arbitrary amount of whitespace between
     * html tags or inside a comment.
     */
    private static final String REGEX_WORD_WITH_WITHESPACE = "(?:(?:[A-Za-z0-9_-]+)|(?:\\s+))+";

    /**
     * Regex matching a valid word between html tags or inside a comment.
     */
    private static final String REGEX_WORD = "[A-Za-z0-9_-]+";

    /**
     * Regex matching a valid tag name.
     */
    private static final String REGEX_TAG_IDENTIFIER = "[a-z0-9]+";

    private Serializer(String path) {
        String[] file = FileInputHelper .read(path);

    }

}
