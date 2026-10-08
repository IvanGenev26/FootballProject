import football.model.FieldPlayer;
import football.model.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FieldPlayerTest {
    @Test
    public void newFieldPlayerKeepsGivenPosition() {
        FieldPlayer fieldPlayer = new FieldPlayer("Bukayo Saka", "England", 7, Position.FORWARD, 26);
        assertEquals(Position.FORWARD, fieldPlayer.getPosition());
    }

    @Test
    public void constructorRejectsGoalkeeperPosition() {
        assertThrows(IllegalArgumentException.class, () -> new FieldPlayer("David Raya", "Spain", 1, Position.GOALKEEPER, 26));
    }

    @Test
    public void settingPositionIsValid() {
        FieldPlayer fieldPlayer = new FieldPlayer("Bukayo Saka", "England", 7, Position.FORWARD, 26);
        fieldPlayer.setPosition(Position.DEFENDER);
        assertEquals(Position.DEFENDER, fieldPlayer.getPosition());
    }

    @Test
    public void setPositionRejectsGoalkeeper() {
        FieldPlayer fieldPlayer = new FieldPlayer("Bukayo Saka", "England", 7, Position.FORWARD, 26);
        assertThrows(IllegalArgumentException.class, () -> fieldPlayer.setPosition(Position.GOALKEEPER));
        assertEquals(Position.FORWARD, fieldPlayer.getPosition());
    }

    @Test
    public void roleReturnsFieldPlayer() {
        FieldPlayer fieldPlayer = new FieldPlayer("Bukayo Saka", "England", 7, Position.FORWARD, 26);

        assertEquals("Field player", fieldPlayer.role());
    }
}
