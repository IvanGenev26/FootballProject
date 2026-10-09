import football.model.FieldPlayer;
import football.model.Player;
import football.model.Position;
import football.model.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class TeamTest {
    private Team team;
    private FieldPlayer fieldPlayer;

    @BeforeEach
    public void setUp() {
        team = new Team("Arsenal");
        fieldPlayer = new FieldPlayer("Bukayo Saka", "England", 7, Position.FORWARD, 26);
    }

    @Test
    public void nullTeamNameTest() {
        assertThrows(IllegalArgumentException.class, () -> new Team(null));
    }

    @Test
    public void createTeamRejectsBlankNameTest() {
        assertThrows(IllegalArgumentException.class, () -> new Team(" "));
    }

    @Test
    public void addPlayerRejectsNull() {
        assertThrows(IllegalArgumentException.class, () -> team.addPlayer(null));
    }


    @Test
    public void createAteamWithZeroPlayersTest() {
        assertEquals(0, team.getTeamPlayers().size());
    }

    @Test
    public void addPlayerTest() {
        team.addPlayer(fieldPlayer);
        assertTrue(team.getTeamPlayers().contains(fieldPlayer));
    }

    @Test
    public void addingTheSamePlayerTest() {
        team.addPlayer(fieldPlayer);
        assertThrows(IllegalStateException.class, () -> team.addPlayer(fieldPlayer));
    }

    @Test
    public void getTeamPlayersReturnsACopy() {
        Set<Player> players = team.getTeamPlayers();
        players.add(fieldPlayer);
        assertEquals(0, team.getTeamPlayers().size());
    }

    @Test
    public void removePlayerTest() {
        team.addPlayer(fieldPlayer);
        team.removePlayer(fieldPlayer);
        assertFalse(team.getTeamPlayers().contains(fieldPlayer));
    }

    @Test
    public void removeAPlayerThatIsNotInTeamTest() {
        assertThrows(IllegalStateException.class, () -> team.removePlayer(fieldPlayer));
    }
}
