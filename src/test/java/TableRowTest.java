import football.model.TableRow;
import football.model.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TableRowTest {
    TableRow row;

    @BeforeEach
    void setUp() {
        row = new TableRow(new Team("Arsenal"));
    }
    @Test
    void newRowHasAllZeros() {
        assertEquals(0, row.getPlayed());
        assertEquals(0, row.getWon());
        assertEquals(0, row.getDrawn());
        assertEquals(0, row.getLost());
        assertEquals(0, row.getGoalsFor());
        assertEquals(0, row.getGoalsAgainst());
        assertEquals(0, row.getPoints());
    }

    @Test
    void aWinAddsToPlayedWonAndPoints() {
        row.addResult(2, 1, 3);
        assertEquals(1, row.getPlayed());
        assertEquals(1, row.getWon());
        assertEquals(2, row.getGoalsFor());
        assertEquals(1, row.getGoalsAgainst());
        assertEquals(0, row.getDrawn());
        assertEquals(0, row.getLost());
        assertEquals(3, row.getPoints());
    }

    @Test
    void aLossAddsToPlayedAndLost() {
        row.addResult(0, 2, 0);
        assertEquals(1, row.getPlayed());
        assertEquals(0, row.getWon());
        assertEquals(0, row.getGoalsFor());
        assertEquals(2, row.getGoalsAgainst());
        assertEquals(0, row.getDrawn());
        assertEquals(1, row.getLost());
        assertEquals(0, row.getPoints());
    }

    @Test
    void aDrawAddsToPlayedAndDrawn() {
        row.addResult(1, 1, 1);
        assertEquals(1, row.getPlayed());
        assertEquals(0, row.getWon());
        assertEquals(1, row.getGoalsFor());
        assertEquals(1, row.getGoalsAgainst());
        assertEquals(1, row.getDrawn());
        assertEquals(0, row.getLost());
        assertEquals(1, row.getPoints());
    }

    @Test
    void goalsAreAddedAcrossResults() {
        row.addResult(2, 1, 3);
        row.addResult(0, 3, 0);

        assertEquals(2, row.getGoalsFor());
        assertEquals(4, row.getGoalsAgainst());
        assertEquals(2, row.getPlayed());
    }

    @Test
    void goalDifferenceIsGoalsForMinusGoalsAgainst() {
        row.addResult(2, 1, 3);
        row.addResult(0, 3, 0);

        assertEquals(-2, row.getGoalDifference());
    }

    @Test
    void negativeGoalsAreRejectedAndRowStaysUnchanged() {
        assertThrows(IllegalArgumentException.class, () -> row.addResult(-1, 2, 0));

        assertEquals(0, row.getPlayed());
        assertEquals(0, row.getGoalsFor());
        assertEquals(0, row.getGoalsAgainst());
        assertEquals(0, row.getPoints());
    }

    @Test
    void toStringShowsTheRow() {
        row.addResult(2, 1, 3);
        assertEquals("Arsenal P:1 W:1 D:0 L:0 GF:2 GA:1 GD:1 Pts:3", row.toString());
    }


}
