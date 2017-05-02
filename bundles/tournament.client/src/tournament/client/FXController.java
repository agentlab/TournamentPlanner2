/**
 *
 */
package tournament.client;

import javax.annotation.PostConstruct;

import org.eclipse.e4.core.contexts.IEclipseContext;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
/**
 * @author Vilkova
 *
 */
public class FXController {
	private PrintService service;

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
		service.print("Registration was opened!");
//		TournamentClient.getInstance().getTournamentService().openRegistration();
		TournamentClient.getInstance().openRegistration();
	}
}

