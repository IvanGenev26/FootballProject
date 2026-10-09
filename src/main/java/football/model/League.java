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
}
