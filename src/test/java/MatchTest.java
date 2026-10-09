import football.model.Match;
import football.model.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MatchTest {
    private Team homeTeam;
    private Team awayTeam;
    private Match match;

    @BeforeEach
    public void setUp() {
        homeTeam = new Team("Arsenal");
        awayTeam = new Team("Chelsea");
        match = new Match(homeTeam, awayTeam);
    }

    @Test
    public void newMatchKeepsItsTeams() {
        assertEquals(homeTeam, match.getHomeTeam());
        assertEquals(awayTeam, match.getAwayTeam());
    }

    @Test
    public void addingNullHomeTeamRejectsTest() {
        assertThrows(IllegalArgumentException.class, () -> new Match(null, awayTeam));
    }

    @Test
    public void addingNullAwayTeamRejectsTest() {
        assertThrows(IllegalArgumentException.class, () -> new Match(homeTeam, null));
    }

    @Test
    public void addingTheSameTeamRejectsTest() {
        assertThrows(IllegalArgumentException.class, () -> new Match(homeTeam, homeTeam));
    }

    @Test
    void toStringShowsScoreAfterResult() {
        match.recordResult(2, 1);
        assertEquals("Arsenal 2-1 Chelsea", match.toString());
    }

    @Test
    void toStringShowsVsBeforeMatch() {
        assertEquals("Arsenal vs Chelsea", match.toString());
    }

    @Test
    void recordResultStoresBothNumbersAndMakeTheMatchPlayed() {
        match.recordResult(2, 1);
        assertEquals(2, match.getHomeGoals());
        assertEquals(1, match.getAwayGoals());
        assertTrue(match.isPlayed());
    }

    @Test
    void matchIsNotPlayed() {
        assertFalse(match.isPlayed());
    }

    @Test
    void negativeHomeGoalsRejected() {
        assertThrows(IllegalArgumentException.class, () -> match.recordResult(-1, 2));
    }

    @Test
    void negativeAwayGoalsRejected() {
        assertThrows(IllegalArgumentException.class, () -> match.recordResult(2, -1));
    }

    @Test
    void rejectedResultLeavesMatchUnplayed() {
        assertThrows(IllegalArgumentException.class, () -> match.recordResult(-1, 2));
        assertFalse(match.isPlayed());
    }

    @Test
    void rejectsSecondScoreTest() {
        match.recordResult(2, 1);
        assertThrows(IllegalStateException.class, () -> match.recordResult(1, 1));
    }
}
