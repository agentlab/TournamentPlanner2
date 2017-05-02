/**
 *
 */
package tournament.model;

/**
 * @author Vilkova
 *
 */
public class Player {
	private int id;
	private static int nextId = 1;
	private String name;
	private int points;

	public Player(String name) {
		id = nextId;
		this.name = name;
		nextId++;
	}

	/**
	 *
	 */
	public Player() {

	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public void addPoints(int points) {
		this.points += points;
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (obj instanceof Player) {
			Player other = (Player)obj;

			return (other.getId() == this.getId());
		}

		return false;
	}
}
