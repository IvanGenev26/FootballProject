package football.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Team {
    private String name;
    private final int id;
    private static int nextId = 1;
    private final Set<Player> teamPlayers = new HashSet<>();

    public Team(String name) {
        validateName(name);
        this.name = name;
        id = nextId++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        validateName(name);
        this.name = name;
    }

    private void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("name cannot be null or blank");
        }
    }

    private void validatePlayer(Player player) {
        if (player == null) {
            throw new IllegalArgumentException("name cannot be null or blank");
        }
    }

    public void addPlayer(Player player) {
        validatePlayer(player);
        if (!teamPlayers.add(player)) {
            throw new IllegalStateException(player.getName() + " is already in team");
        }
    }
    public void removePlayer(Player player) {
        validatePlayer(player);
        if (!teamPlayers.remove(player)) {
            throw new IllegalStateException(player.getName() + " is not in team");
        }
    }

    public int getId() {
        return id;
    }


    public Set<Player> getTeamPlayers() {
        return new HashSet<>(teamPlayers);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return id == team.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Team{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", players=" + teamPlayers.size() +
                '}';
    }
}
