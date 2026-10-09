package football.model;

public class ThreePointRule implements PointsRule {
    @Override
    public int pointsFor(int goalsScored, int goalsConceded) {
        if (goalsScored > goalsConceded) {
            return 3;
        }
        if (goalsScored < goalsConceded) {
            return 0;
        }
        return 1;
    }
}
