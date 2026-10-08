package football.model;

import java.util.Objects;


public abstract class Player {
    private final String name;
    private final String nationality;
    private final int id;
    private static int nextId = 1;
    private int shirtNumber;
    private Position position;
    private int age;

    public Player(String name, String nationality, int shirtNumber,Position position, int age) {
        if(name == null || name.isBlank()) {
            throw new IllegalArgumentException("Player name cannot be null or empty");
        }
        if (age <= 0 || age >= 100) {
            throw new IllegalArgumentException("Player age must be between 1 and 99");
        }
        if (nationality == null || nationality.isBlank()) {
            throw new IllegalArgumentException("Player nationality cannot be null or empty");
        }
        validateShirtNumber(shirtNumber);
        validatePosition(position);
        this.name = name;
        this.nationality = nationality;
        this.shirtNumber = shirtNumber;
        this.position = position;
        this.age = age;
        this.id = nextId++;
    }

    private void validateShirtNumber(int shirtNumber) {
        if (shirtNumber <= 0 || shirtNumber >= 100) {
            throw new IllegalArgumentException("Player shirtNumber must be between 1 and 99");
        }
    }

    private void validatePosition(Position position) {
        if (position == null) {
            throw new IllegalArgumentException("Player position cannot be null");
        }
    }

    public String getName() {
        return name;
    }

    public String getNationality() {
        return nationality;
    }

    public int getId() {
        return id;
    }

    public int getShirtNumber() {
        return shirtNumber;
    }

    public Position getPosition() {
        return position;
    }

    public int getAge() {
        return age;
    }

    public void setShirtNumber(int shirtNumber) {
        validateShirtNumber(shirtNumber);
        this.shirtNumber = shirtNumber;
    }

    protected void updatePosition(Position position) {
        validatePosition(position);
        this.position = position;
    }

    public abstract String role();

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return id == player.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", nationality='" + nationality + '\'' +
                ", id=" + id +
                ", shirtNumber=" + shirtNumber +
                ", position=" + position +
                ", age=" + age +
                '}';
    }
}
