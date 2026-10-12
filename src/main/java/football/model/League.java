package football.model;

import java.util.*;

public class League {
    private final String name;
    private final Map<Integer, Team> teams = new HashMap<>();
    private final List<Match> matches = new ArrayList<>();

    public League(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("League name cannot be null or empty");
        }
        this.name = name;
    }

    public void addTeam(Team team) {
        if (team == null) {
            throw new IllegalArgumentException("Team cannot be null");
        }
        if (teams.containsKey(team.getId())) {
            throw new IllegalStateException("Team already exists");
        }
        teams.put(team.getId(), team);
    }

    public void addMatch(Match match) {
        if (match == null) {
            throw new IllegalArgumentException("Match cannot be null");
        }
        if (!teams.containsKey(match.getHomeTeam().getId())) {
            throw new IllegalStateException("Home team is not in the league: " + match.getHomeTeam().getName());
        }
        if (!teams.containsKey(match.getAwayTeam().getId())) {
            throw new IllegalStateException("Away team is not in the league: " + match.getAwayTeam().getName());
        }
        matches.add(match);
    }

    public List<Team> getTeams() {
        return new ArrayList<>(teams.values());
    }

    public List<Match> getMatches() {
        return new ArrayList<>(matches);
    }

    public String getName() {
        return name;
    }

    public List<TableRow> table(PointsRule rule) {
        Map<Integer, TableRow> rows = new HashMap<>();
        for (Team team : teams.values()) {
            rows.put(team.getId(), new TableRow(team));
        }
        for (Match match : matches) {
            if (match.isPlayed()) {
                int homeGoals = match.getHomeGoals();
                int awayGoals = match.getAwayGoals();

                TableRow homeRow = rows.get(match.getHomeTeam().getId());
                TableRow awayRow = rows.get(match.getAwayTeam().getId());

                homeRow.addResult(homeGoals, awayGoals, rule.pointsFor(homeGoals, awayGoals));
                awayRow.addResult(awayGoals, homeGoals, rule.pointsFor(awayGoals, homeGoals));
            }
        }
        List<TableRow> result = new ArrayList<>(rows.values());
        result.sort((a, b) -> Integer.compare(b.getPoints(), a.getPoints()));
        return result;
    }

}
