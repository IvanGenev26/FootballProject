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

    public ParsedResult parse(String line) {
        if (!isValid(line)) {
            throw new IllegalArgumentException("Invalid result line: " + line);
        }
        Matcher matcher = RESULT.matcher(line);
        matcher.matches();
        String homeTeam = matcher.group(1);
        int homeGoals = Integer.parseInt(matcher.group(2));
        int awayGoals = Integer.parseInt(matcher.group(3));
        String awayTeam = matcher.group(4);
        return new ParsedResult(homeTeam, homeGoals, awayGoals, awayTeam);
    }
}
