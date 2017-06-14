package junit;

import java.util.Collection;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import tournament.model.Match;
import tournament.model.SingleEliminationTournament;
import tournament.service.host.TournamentService;

public class SingleEliminationTest {

	private static final int PLAYERS_COUNT = 2000;

	private TournamentService tournamentService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		tournamentService = new TournamentService();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void singleEliminationTest() {
		tournamentService.openRegistration();
		fillPlayers();
		tournamentService.closeRegistration();
		if (tournamentService.getManagedTournament() instanceof SingleEliminationTournament)
		{
			SingleEliminationTournament singleEliminationTournament =
			    (SingleEliminationTournament)tournamentService.getManagedTournament();
			System.err.println("AllPlayers: " + tournamentService.getPlayers());
			while (singleEliminationTournament.getRating() == null)
			{
				Collection<Match> matchs = singleEliminationTournament.generateMatchs();
				System.err.println("Mathces: \n" + matchs);
				matchs.forEach(match -> {
					match.setWinner(match.getMatchPlayers().iterator().next());
				});

				singleEliminationTournament.removeLosers();
			}

			System.err.println("RoundWinner: " + singleEliminationTournament.getRating().get(0));
		}
	}

	/*@Test
	public void testAdd()
	{
		tournamentManager.openRegistration();
		for (int playerIndex = 0; playerIndex <= PLAYERS_COUNT; playerIndex++)
		{
			tournamentManager.addPlayer(new Player("Player " + playerIndex));
			System.out.println(tournamentManager.getPlayers());
		}
		tournamentManager.closeRegistration();
	}*/

	private void fillPlayers() {
		for (int playerIndex = 0; playerIndex < PLAYERS_COUNT; playerIndex++)
		{
			tournamentService.addPlayer("Player " + playerIndex);
		}
	}
}
