/**
 *
 */
package com.bmstu.coursework.oomph;

import java.util.Collection;
import java.util.List;

import com.bmstu.coursework.oomph.model.AbstractTournament;
import com.bmstu.coursework.oomph.model.Match;
import com.bmstu.coursework.oomph.model.Player;
import com.bmstu.coursework.oomph.model.SetWinnerRequest;

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

}
