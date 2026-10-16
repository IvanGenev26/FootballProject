package football.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class LeagueStorage {
    public void save(League league, String path) throws IOException {
        StringBuilder sb = new StringBuilder();

        for (Team team : league.getTeams()) {
            sb.append("TEAM|").append(team.getName()).append("\n");
        }
        for (Match match : league.getMatches()) {
            if (match.isPlayed()) {
                sb.append("MATCH|")
                        .append(match.getHomeTeam().getName()).append(" ")
                        .append(match.getHomeGoals()).append("-")
                        .append(match.getAwayGoals()).append(" ")
                        .append(match.getAwayTeam().getName()).append("\n");
            }
        }
        Files.writeString(Path.of(path), sb.toString());
    }
}
