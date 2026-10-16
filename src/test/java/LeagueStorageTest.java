import football.model.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LeagueStorageTest {
    @TempDir
    Path tempDir;

    @Test
    void saveWritesTeamsAndPlayedMatches() throws IOException {
        League league = new League("Premier League");
        Team arsenal = new Team("Arsenal");
        Team chelsea = new Team("Chelsea");
        league.addTeam(arsenal);
        league.addTeam(chelsea);
        Match match = new Match(arsenal, chelsea);
        league.addMatch(match);
        match.recordResult(2, 1);

        Path file = tempDir.resolve("league.txt");
        new LeagueStorage().save(league, file.toString());

        String text = Files.readString(file);

        assertTrue(text.contains("TEAM|Arsenal"));
        assertTrue(text.contains("TEAM|Chelsea"));
        assertTrue(text.contains("MATCH|Arsenal 2-1 Chelsea"));
    }

    @Test
    void aSavedLeagueCanBeLoadedBack() throws IOException {
        League league = new League("Premier League");
        Team arsenal = new Team("Arsenal");
        Team chelsea = new Team("Chelsea");
        league.addTeam(arsenal);
        league.addTeam(chelsea);
        Match match = new Match(arsenal, chelsea);
        league.addMatch(match);
        match.recordResult(2, 1);

        Path file = tempDir.resolve("league.txt");
        LeagueStorage storage = new LeagueStorage();
        storage.save(league, file.toString());

        League loaded = storage.load("Premier League", file.toString());

        assertEquals(2, loaded.getTeams().size());
        assertEquals(1, loaded.getMatches().size());
        assertEquals(2, loaded.getMatches().get(0).getHomeGoals());
        assertEquals(1, loaded.getMatches().get(0).getAwayGoals());
    }
}