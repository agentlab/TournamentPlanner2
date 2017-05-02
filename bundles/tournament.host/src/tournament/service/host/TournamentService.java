/**
 *
 */
package tournament.service.host;

//import java.awt.PageAttributes.MediaType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import tournament.model.AbstractTournament;
import tournament.model.Match;
import tournament.model.Player;
import tournament.model.SetWinnerRequest;
import tournament.model.SingleEliminationTournament;
import tournament.service.ITournamentService;

/**
 * @author Vilkova
 *
 */
@Component(enabled = true, immediate = true,
    property = { "service.exported.interfaces=*", "service.exported.configs=ecf.jaxrs.jersey.server", "ecf.jaxrs.jersey.server.urlContext=http://localhost:8080", "ecf.jaxrs.jersey.server.alias=/tournament", "service.pid=tournament.service.host.TournamentService" })
//@Path("/beautyblog")
public class TournamentService implements ITournamentService {

	public static final int MAX_NUMBER_PLAYER = 100;
	private AbstractTournament tournament;
	private List<Player> players = new ArrayList<>();
	private boolean isRegistrationOpen;
	private Collection<Match> matchsHistory = new ArrayList<>();

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
	public void addPlayer(String player) {
		ObjectMapper mapper = new ObjectMapper();
		Player p = null;

		try {
			p = mapper.readValue(player, Player.class);
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if ((players.size() < MAX_NUMBER_PLAYER) && isRegistrationOpen && (player != null)) {
			players.add(p);
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
		Player playerWinner = null;
		for (int i = 0; i < players.size(); i++) {
			if (setWinnerRequest.getIdPlayer() == players.get(i).getId()) {
				playerWinner = players.get(i);
				break;
			}
		}
		setWinnerRequest.getMatch().setWinner(playerWinner);
	}

	@Override
	public Collection<Match> generateMatchs() {
		Collection<Match> generatedMatchs = getManagedTournament().generateMatchs();
		matchsHistory.addAll(generatedMatchs);
		return generatedMatchs;
	}


	@Override
	public String getPlayers() {
		ObjectMapper mapper = new ObjectMapper();
		Player p = new Player();
		String playersStr = null;

		try {
			playersStr = mapper.writeValueAsString(players);
		}
		catch (JsonProcessingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return playersStr;
	}

	@Override
	public Collection<Match> getMatchs() {
		return matchsHistory;
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
