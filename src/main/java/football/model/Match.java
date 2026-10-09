package football.model;

import java.util.Objects;

public class Match {
    private final int id;
    private static int nextId = 1;
    private final Team homeTeam;
    private final Team awayTeam;
    private int homeGoals;
    private int awayGoals;
    private boolean played;

    public Match(Team homeTeam, Team awayTeam) {
        if (homeTeam == null) {
            throw new IllegalArgumentException("home team cannot be null");
        }
        if (awayTeam == null) {
            throw new IllegalArgumentException("away team cannot be null");
        }
        if (homeTeam.equals(awayTeam)) {
            throw new IllegalArgumentException("home team and away team cannot be the same");
        }
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.id = nextId++;
    }

    public int getId() {
        return id;
    }
    public Team getHomeTeam() {
        return homeTeam;
    }
    public Team getAwayTeam() {
        return awayTeam;
    }

    public void recordResult(int homeGoals, int awayGoals) {
        if (homeGoals < 0 || awayGoals < 0) {
            throw new IllegalArgumentException("home/away goals cannot be negative");
        }
        if (played) {
            throw new IllegalStateException("Match has already been recorded");
        }
        this.homeGoals = homeGoals;
        this.awayGoals = awayGoals;
        this.played = true;
    }

    public int getHomeGoals() { return homeGoals; }
    public int getAwayGoals() { return awayGoals; }
    public boolean isPlayed() { return played; }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Match match = (Match) o;
        return id == match.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return homeTeam.getName() + (isPlayed() ? " " + homeGoals + "-" + awayGoals + " " : " vs ") + awayTeam.getName();
    }
}
