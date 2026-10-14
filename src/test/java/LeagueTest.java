import football.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LeagueTest {
    private League league;
    private Team arsenal;
    private Team chelsea;

    @BeforeEach
    public void setUp() {
        league = new League("Premier League");
        arsenal = new Team("Arsenal");
        chelsea = new Team("Chelsea");
    }

    @Test
    public void aNewLeagueHasNoMatchesAndTeams() {
        assertTrue(league.getTeams().isEmpty());
        assertTrue(league.getMatches().isEmpty());
    }

    @Test
    public void addTeamAddsTeamToLeagueTest() {
        league.addTeam(arsenal);
        assertTrue(league.getTeams().contains(arsenal));
    }

    @Test
    public void addingTheSameTeamToLeagueBreaksTest() {
        league.addTeam(arsenal);
        assertThrows(IllegalStateException.class, () -> league.addTeam(arsenal));
    }

    @Test
    public void addMatchAppearsInGetMatchesTest() {
        league.addTeam(arsenal);
        league.addTeam(chelsea);
        Match match = new Match(arsenal, chelsea);
        league.addMatch(match);
        assertTrue(league.getMatches().contains(match));
    }

    @Test
    public void homeTeamIsNotInTheLeagueTest() {
        league.addTeam(chelsea);
        Match match = new Match(arsenal, chelsea);
        assertThrows(IllegalStateException.class, () -> league.addMatch(match));
    }

    @Test
    public void awayTeamIsNotInTheLeagueTest() {
        league.addTeam(arsenal);
        Match match = new Match(arsenal, chelsea);
        assertThrows(IllegalStateException.class, () -> league.addMatch(match));
    }

    @Test
    public void getMatchesReturnsACopyOfMatchesTest() {
        List<Match> matches = league.getMatches();
        matches.add(new Match(arsenal, chelsea));
        assertEquals(0,  league.getMatches().size());
    }

    @Test
    void tableCountsAPlayedMatch() {
        league.addTeam(arsenal);
        league.addTeam(chelsea);
        Match match = new Match(arsenal, chelsea);
        league.addMatch(match);
        match.recordResult(2, 1);

        List<TableRow> table = league.table(new ThreePointRule());

        assertEquals(3, rowOf(table, arsenal).getPoints());
        assertEquals(0, rowOf(table, chelsea).getPoints());
    }

    private TableRow rowOf(List<TableRow> table, Team team) {
        for (TableRow row : table) {
            if (row.getTeam().equals(team)) {
                return row;
            }
        }
        throw new IllegalStateException("No row for " + team.getName());
    }

    @Test
    void tableLeaderIsFirst() {
        league.addTeam(arsenal);
        league.addTeam(chelsea);
        Match match = new Match(arsenal, chelsea);
        league.addMatch(match);
        match.recordResult(2, 1);

        List<TableRow> table = league.table(new ThreePointRule());

        assertEquals(arsenal, table.get(0).getTeam());
    }

    @Test
    void badResultKeepsTheFixtureInTheQueue() {
        league.addTeam(arsenal);
        league.addTeam(chelsea);
        Match match = new Match(arsenal, chelsea);
        league.addFixture(match);
        assertThrows(IllegalArgumentException.class, () -> league.playNextFixture(-2, 3));
        league.playNextFixture(2, 3);
        assertEquals(1, league.getMatches().size());
        assertTrue(match.isPlayed());
    }
}
