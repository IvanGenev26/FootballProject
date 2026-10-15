package football.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ResultParser {
    private static final Pattern RESULT = Pattern.compile("(.+) (\\d+)-(\\d+) (.+)");

    public boolean isValid (String line) {
        if (line == null || line.isBlank()) {
            return false;
        }
        Matcher matcher = RESULT.matcher(line);
        return matcher.matches();
    }
}
