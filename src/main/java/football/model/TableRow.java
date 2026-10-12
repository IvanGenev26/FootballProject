package football.model;

public class TableRow {
    private final Team team;
    private int played;
    private int won;
    private int drawn;
    private int lost;
    private int goalsFor;
    private int goalsAgainst;
    private int points;

    public TableRow(Team team) {
        if (team == null) {
            throw new IllegalArgumentException("Team cannot be null");
        }
        this.team = team;
    }

    public void addResult(int goalsScored, int goalsConceded, int pointsEarned) {
        if (goalsScored < 0 || goalsConceded < 0) {
            throw new IllegalArgumentException("goals cannot be negative");
        }
        if (pointsEarned < 0) {
            throw new IllegalArgumentException("points cannot be negative");
        }
        played++;

        if (goalsScored > goalsConceded) {
            won++;

        }else if (goalsScored < goalsConceded) {
            lost++;
        }else {
            drawn++;
        }
        goalsFor += goalsScored;
        goalsAgainst += goalsConceded;
        points += pointsEarned;
    }

    public int getPoints() {
        return points;
    }

    public int getGoalsAgainst() {
        return goalsAgainst;
    }

    public int getGoalsFor() {
        return goalsFor;
    }

    public int getLost() {
        return lost;
    }

    public int getDrawn() {
        return drawn;
    }

    public int getWon() {
        return won;
    }

    public int getPlayed() {
        return played;
    }

    public Team getTeam() {
        return team;
    }

    public int getGoalDifference() {
        return goalsFor - goalsAgainst;
    }

    @Override
    public String toString() {
        return String.format("%s P:%d W:%d D:%d L:%d GF:%d GA:%d GD:%d Pts:%d", team.getName(), played, won, drawn, lost, goalsFor, goalsAgainst, getGoalDifference(), points);
    }
}
