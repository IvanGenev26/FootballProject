package football.model;

import java.util.Objects;

public class Match {
    private final int id;
    private static int nextId = 1;
    private final Team homeTeam;
    private final Team awayTeam;

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
        return homeTeam.getName() + " vs " + awayTeam.getName();
    }
}
