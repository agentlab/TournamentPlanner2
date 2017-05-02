/**
 *
 */
package tournament.service;

import java.util.Collection;
import java.util.List;

import tournament.model.AbstractTournament;
import tournament.model.Match;
import tournament.model.Player;
import tournament.model.SetWinnerRequest;

/**
 * @author Vilkova
 *
 */
public interface ITournamentService {

	void openRegistration();

	void closeRegistration();

	AbstractTournament getManagedTournament();

	void addPlayer(Player player);

	void removePlayer(Player player);

	void setWinner(SetWinnerRequest setWinnerRequest);

	List<Player> getPlayers();

	Collection<Match> getMatchs();

	/**
	 * TODO JavaDoc
	 *
	 * @return
	 */
	Collection<Match> generateMatchs();

}
