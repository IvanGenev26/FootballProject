import football.model.League;
import football.model.Match;
import football.model.Team;
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
}
