package football.model;

public class Goalkeeper extends Player {
    public Goalkeeper(String name, String nationality, int shirtNumber, int age) {
        super(name, nationality, shirtNumber, Position.GOALKEEPER, age);
    }

    @Override
    public String role() {
        return "Goalkeeper";
    }
}
