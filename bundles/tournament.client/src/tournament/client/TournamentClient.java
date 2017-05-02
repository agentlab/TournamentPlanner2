/**
 *
 */
package tournament.client;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferencePolicy;

import tournament.service.ITournamentService;

/**
 * @author Vilkova
 *
 */
@Component(name = "tournament.client")
public class TournamentClient {

	private static TournamentClient instance;

	private ITournamentService tournamentService;

	/**
	 * @return the instance
	 */
	public static TournamentClient getInstance() {
		if (instance == null) {
			instance = new TournamentClient();
		}

		return instance;
	}

	/**
	 * Default constructor
	 */
	public TournamentClient() {
		// Does nothing
	}

	@Activate
	void activate() {
		System.out.println("Tournament client started"); //$NON-NLS-1$
	}

	@Reference(policy = ReferencePolicy.DYNAMIC)
	void bindService(ITournamentService tournamentService) {
		this.tournamentService = tournamentService;
	}

	void unbindService(ITournamentService tournamentService) {
		if (tournamentService.equals(this.tournamentService)) {
			this.tournamentService = null;
		}
	}

	/**
	 * @return the tournamentService
	 */
	public ITournamentService getTournamentService() {
		return tournamentService;
	}

	public void openRegistration() {
		tournamentService.openRegistration();
	}

//	private void keyPress() {
//		tournamentService.openRegistration();
//	}
}
