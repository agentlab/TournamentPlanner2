/**
 *
 */
package tournament.model;

//import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * @author Vilkova
 *
 */
public class Match {
	public static final int POINT_WINNER = 2;
	public static final int POINT_LOSER = 0;
	//public static final int POINT_DRAW = 1;
	private int id;
	private static int nextId = 1;
	private List<Player> matchPlayers;
	//private Collection<Match> matchs;
	private Player winner = null;
	private Player loser = null;

	public Match(List<Player> players) {
		matchPlayers = players;
		id = nextId;
		nextId++;
	}

	/**
	 *
	 */
	public Match() {
		// TODO Auto-generated constructor stub
	}

	public void setWinner(Player player) {
		winner = player;
		loser = getAnotherPlayer(matchPlayers, player);
		player.addPoints(POINT_WINNER);
	}

	public Player getWinner() {
		return winner;
	}

	public Player getLoser() {
		return loser;
	}

	public List<Player> getMatchPlayers() {
		return matchPlayers;
	}

	public boolean isPlayed() {
		if (winner == null || loser == null) {
			return false;
		}
		else {
			return true;
		}
	}

	@Override
	public String toString() {
		String result = "Math(" + id + "), players: ";
		for (Player player : matchPlayers) {
			result += player.toString() + ", ";
		}
		result = result.substring(0, result.length() - 2);

		return result;
	}

	private Player getAnotherPlayer(List<Player> players, Player player) {
		Iterator<Player> playerIterator = players.iterator();
		while (playerIterator.hasNext()) {
			Player nextPlayer = playerIterator.next();
			if (!nextPlayer.equals(player)) {
				return nextPlayer;
			}
		}

		return null;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @param matchPlayers the matchPlayers to set
	 */
	public void setMatchPlayers(List<Player> matchPlayers) {
		this.matchPlayers = matchPlayers;
	}

	/**
	 * @param loser the loser to set
	 */
	public void setLoser(Player loser) {
		this.loser = loser;
	}
}