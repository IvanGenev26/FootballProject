package football.model;

public class FieldPlayer extends Player {
    public FieldPlayer(String name, String nationality, int shirtNumber , Position position, int age) {
        super(name, nationality, shirtNumber, position, age);
        goalkeeperPosition(position);
    }

    public void setPosition(Position position) {
        goalkeeperPosition(position);
        updatePosition(position);
    }

    private void goalkeeperPosition(Position position) {
        if (position == Position.GOALKEEPER) {
            throw new IllegalArgumentException("Field player cannot be a GOALKEEPER");
        }
    }

    @Override
    public String role() {
        return "Field player";
    }
}

