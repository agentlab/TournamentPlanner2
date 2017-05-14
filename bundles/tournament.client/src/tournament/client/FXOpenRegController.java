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
public class FXOpenRegController {
	@Inject
	private ITournamentService service;

	@FXML
	Button openRegButton;

	@PostConstruct
	void created(IEclipseContext context) {
		System.err.println("Parent post construction"); //$NON-NLS-1$
	}

	@FXML
	void initialize() {
		System.err.println("Parent controller"); //$NON-NLS-1$
	}

	@FXML
	void keyPress() {
		service.openRegistration();
	}
}

