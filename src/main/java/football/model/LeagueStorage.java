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

    public League load(String leagueName, String path) throws IOException {
        League league = new League(leagueName);
        ResultParser parser = new ResultParser();

        for (String line : Files.readAllLines(Path.of(path))) {
            if (line.startsWith("TEAM|")) {
                String teamName = line.substring(5);
                league.addTeam(new Team(teamName));
            } else if (line.startsWith("MATCH|")) {
                ParsedResult result = parser.parse(line.substring(6));

                Team home = league.findTeamByName(result.homeTeam());
                Team away = league.findTeamByName(result.awayTeam());

                Match match = new Match(home, away);
                match.recordResult(result.homeGoals(), result.awayGoals());
                league.addMatch(match);
            }
        }
        return league;
    }
}
