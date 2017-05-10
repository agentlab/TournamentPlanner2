/**
 *
 */
package tournament.client;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.core.contexts.IEclipseContext;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import tournament.service.ITournamentService;
/**
 * @author Vilkova
 *
 */
public class FXController {
	@Inject
	private ITournamentService service;

	@FXML
	Button button;

	@PostConstruct
	void created(IEclipseContext context) {
		System.err.println("Parent post construction");
	}

	@FXML
	void initialize() {
		System.err.println("Parent controller");
	}

	@FXML
	void keyPress() {
//		TournamentClient.getInstance().getTournamentService().openRegistration();
		service.openRegistration();
	}
}

