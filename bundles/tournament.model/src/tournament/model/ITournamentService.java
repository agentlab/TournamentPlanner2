/**
 *
 */
package tournament.model;

import java.util.Collection;
import java.util.List;

/**
 * @author Vilkova
 *
 */
public interface ITournamentService {

	void openRegistration();

	void closeRegistration();

	AbstractTournament getManagedTournament();

	void addPlayer(String name);

	void removePlayer(Player player);

	void setWinner(SetWinnerRequest setWinnerRequest);

	List<Player> getPlayers();

	List<String> getNamePlayers();

	Collection<Match> getMatchs();

	Player getRating();

	/**
	 * TODO JavaDoc
	 *
	 * @return
	 */
	Collection<Match> generateMatchs();

}
