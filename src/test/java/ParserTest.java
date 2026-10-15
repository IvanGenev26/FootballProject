import football.model.ResultParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
}
