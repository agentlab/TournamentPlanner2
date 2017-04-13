/**
 *
 */
package com.bmstu.coursework.oomph.ds.host;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;

import com.bmstu.coursework.oomph.ITournamentService;
import com.bmstu.coursework.oomph.model.AbstractTournament;
import com.bmstu.coursework.oomph.model.Match;
import com.bmstu.coursework.oomph.model.Player;
import com.bmstu.coursework.oomph.model.SetWinnerRequest;
import com.bmstu.coursework.oomph.model.SingleEliminationTournament;

/**
 * @author Vilkova
 *
 */
@Component(enabled = true, immediate = true,
    property = { "service.exported.interfaces=*", "service.exported.configs=ecf.jaxrs.jersey.server", "ecf.jaxrs.jersey.server.urlContext=http://localhost:8080", "ecf.jaxrs.jersey.server.alias=/tournament", "service.pid=com.bmstu.coursework.oomph.ds.host.TournamentService" })
public class TournamentService implements ITournamentService {

	public static final int MAX_NUMBER_PLAYER = 100;
	private AbstractTournament tournament;
	private List<Player> players = new ArrayList<>();
	private boolean isRegistrationOpen;

	@Override
	public void openRegistration() {
		players = new ArrayList<>();
		isRegistrationOpen = true;
	}

	@Override
	public void closeRegistration() {
		tournament = new SingleEliminationTournament(players);
		isRegistrationOpen = false;
	}

	@Override
	public AbstractTournament getManagedTournament() {
		return tournament;
	}

	@Override
	public void addPlayer(Player player) {
		if ((players.size() < MAX_NUMBER_PLAYER) && isRegistrationOpen && (player != null)) {
			players.add(player);
		}
	}

	@Override
	public void removePlayer(Player player) {
		if (isRegistrationOpen) {
			players.remove(player);
		}
	}

	@Override
	public void setWinner(SetWinnerRequest setWinnerRequest) {
		setWinnerRequest.getMatch().setWinner(setWinnerRequest.getPlayer());
	}

	@Override
	public List<Player> getPlayers() {
		return players;
	}

	@Override
	public Collection<Match> getMatchs() {
		return getManagedTournament().generateMatchs();
	}

	@Activate
	public void activate(ComponentContext context) throws IOException {
		//tournament = new ArrayList<>();
		System.out.println("TournamentManager started");
	}

	@Deactivate
	public void deactivate(ComponentContext context) {
		System.out.println("TournamentManager stopped");
	}

	@Modified
	public void modify() {
		System.out.println("TournamentManager modified");
	}

}
