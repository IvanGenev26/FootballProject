import football.model.Match;
import football.model.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MatchTest {
    private Team homeTeam;
    private Team awayTeam;

    @BeforeEach
    public void setUp() {
        homeTeam = new Team("Arsenal");
        awayTeam = new Team("Chelsea");
    }

    @Test
    public void newMatchKeepsItsTeams() {
        Match match = new Match(homeTeam, awayTeam);
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

}
