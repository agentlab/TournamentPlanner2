/**
 *
 */
package tournament.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author Vilkova
 *
 */
public class SingleEliminationTournament
    extends AbstractTournament {
	private Collection<Player> initialPlayers;
//	private Map<Player, Integer> playerToRatingMap;
	private Collection<Match> currentRoundMatchs;
	private Player extraPlayer = null;

	public SingleEliminationTournament(List<Player> players) {
		super(players);
//		playerToRatingMap = getPlayerToRatingMap(players);
		initialPlayers = new ArrayList<>(players);
		currentRoundMatchs = new ArrayList<>();
	}

	@Override
	public Collection<Match> generateMatchs() {
		Collection<Match> matchs = new ArrayList<>();

		if (!roundFinished(currentRoundMatchs)) {
			// TODO: throw not all matchs played exception
			throw new IllegalArgumentException();
		}
		if (players.isEmpty()) {
			return matchs;
		}

		if (extraPlayer != null) {
			players.add(extraPlayer);
			extraPlayer = null;
		}
		Collections.shuffle(players);
		if (players.size() % 2 != 0) {
			extraPlayer = players.remove(0);
		}
		for (int i = 0; i < players.size(); i += 2) {
			matchs.add(new Match(Arrays.asList(players.get(i), players.get(i + 1))));
		}
		currentRoundMatchs = matchs;
		return matchs;
	}

	/**
	 * Removes round losers.
	 *
	 * @throws roundNotFinished exception if round not finished
	 */
	public void removeLosers() {
		removeLosers(players, currentRoundMatchs);
	}

	@Override
	public List<Player> getRating() {
		if (players.size() + ((extraPlayer != null) ? 1 : 0) > 1) {
			return null;
		}
		else {
			return generateRating();
		}
	}

	private List<Player> generateRating() {
		List<Player> rating = new ArrayList<>(initialPlayers);
		Collections.sort(rating, new PlayersComparator());

		return rating;
	}

	private void removeLosers(List<Player> players, Collection<Match> currentRoundMatchs) {
		currentRoundMatchs.stream().forEach(match -> {
			players.remove(match.getLoser());
		});
	}

	private boolean roundFinished(Collection<Match> currentRoundMatchs) {
		for (Match match : currentRoundMatchs) {
			if (match.getWinner() == null) {
				return false;
			}
		}

		return true;
	}

	/*private Map<Player, Integer> getPlayerToRatingMap(List<Player> players) {
		Map<Player, Integer> playerToRatingMap = new HashMap<>();
		players.stream().forEach(player -> {
			playerToRatingMap.put(player, 0);
		});
	
		return playerToRatingMap;
	}
	*/

	private class PlayersComparator implements Comparator<Player> {
		@Override
		public int compare(Player player, Player otherPlayer) {
			return otherPlayer.getPoints() - player.getPoints();
		}
	}
}
