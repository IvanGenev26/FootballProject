import football.model.ParsedResult;
import football.model.ResultParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    private ResultParser parser;
    @BeforeEach
    public void setUp() {
        parser = new ResultParser();
    }

    @Test
    void aNormalResultIsValid() {
        assertTrue(parser.isValid("Levski 2-1 CSKA"));
    }

    @Test
    void aTeamNameWithASpaceIsValid() {
        assertTrue(parser.isValid("Slavia Sofia 0-0 Botev Plovdiv"));
    }

    @Test
    void notAResultIsInvalid() {
        assertFalse(parser.isValid("not a result"));
    }

    @Test
    void notANumberResultIsInvalid() {
        assertFalse(parser.isValid("Levski x-1 CSKA"));
    }

    @Test
    void nullResultIsInvalid() {
        assertFalse(parser.isValid(null));
    }

    @Test
    void onlyOneTeamIsInvalid() {
        assertFalse(parser.isValid("Levski 2-1"));
    }

    @Test
    void parseTakesOutTheFourPieces() {
        ParsedResult result = parser.parse("Levski 2-1 CSKA");

        assertEquals(new ParsedResult("Levski", 2, 1, "CSKA"), result);
    }

    @Test
    void parseKeepsSpacesInTeamNames() {
        ParsedResult result = parser.parse("Slavia Sofia 0-0 Botev Plovdiv");

        assertEquals(new ParsedResult("Slavia Sofia", 0, 0, "Botev Plovdiv"), result);
    }

    @Test
    void parseRejectsABadLine() {
        assertThrows(IllegalArgumentException.class, () -> parser.parse("not a result"));
    }

    @Test
    void parseHandlesBigNumbers() {
        ParsedResult result = parser.parse("Levski 10-0 CSKA");

        assertEquals(new ParsedResult("Levski", 10, 0, "CSKA"), result);
    }
}
