package tournament.client;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.fx.core.di.LocalInstance;

import de.fxdiagram.core.XRoot;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import tournament.model.ITournamentService;
/**
 * @author Vilkova
 *
 */
public class FXRegistrationController {
	@Inject
	private ITournamentService service;

	@FXML
	Button openRegButton;

	@FXML
	Button closeRegButton;

	FXMLLoader loader;

	public FXRegistrationController() {
		System.err.println("Creating FXRegistrationController"); //$NON-NLS-1$
	}

	@PostConstruct
	void created(IEclipseContext context, @LocalInstance FXMLLoader loader) {
		System.err.println("Parent post construction"); //$NON-NLS-1$
		this.loader = loader;
	}

	@FXML
	void initialize() {
		System.err.println("Parent controller"); //$NON-NLS-1$

	}

	@FXML
	void keyPress() {
		service.openRegistration();
	}

	@FXML
	public void keyClosePress() {
		service.closeRegistration();
		XRoot root = null;
		try {
			loader.setLocation(getClass().getResource("GenerateMatchs.fxml")); //$NON-NLS-1$

			root = loader.load();
			Scene scene = new Scene(root, 1024, 768);

			Stage nStage = new Stage();
			nStage.setScene(scene);
			nStage.setMaximized(false);
			nStage.setTitle("Generate Matchs"); //$NON-NLS-1$

			nStage.show();
		}
		catch (IOException e) {
			e.printStackTrace();
		}

	}
}

