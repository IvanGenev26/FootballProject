import football.model.ThreePointRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ThreePointRuleTest {
    private ThreePointRule rule;

    @BeforeEach
    void setUp() {
        rule = new ThreePointRule();
    }

    @Test
    void aWinGivesThreePoints() {
        assertEquals(3, rule.pointsFor(2, 0));
    }

    @Test
    void aDrawGivesOnePoint() {
        assertEquals(1, rule.pointsFor(1, 1));
    }

    @Test
    void aLossGivesNoPoints() {
        assertEquals(0, rule.pointsFor(0, 2));
    }

    @Test
    void aWinByOneGoalStillGivesThreePoints() {
        assertEquals(3, rule.pointsFor(1, 0));
    }
}