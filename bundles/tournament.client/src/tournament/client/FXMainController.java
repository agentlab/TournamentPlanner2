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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tournament.model.ITournamentService;
/**
 * @author Vilkova
 *
 */
public class FXMainController {
	@Inject
	private ITournamentService service;

	FXMLLoader loader;

	FXMLLoader loader2;

	FXMLLoader loader3;

	@FXML
	Button RegButton;

	@FXML
	Button PlayersButton;

	@FXML
	Button DrawButton;

	public FXMainController() {
		System.err.println("Creating FXMainController"); //$NON-NLS-1$
	}

	@PostConstruct
	void created(IEclipseContext context, @LocalInstance FXMLLoader loader, @LocalInstance FXMLLoader loader2, @LocalInstance FXMLLoader loader3) {
		System.err.println("Parent post construction"); //$NON-NLS-1$
		this.loader = loader;
		this.loader2 = loader2;
		this.loader3 = loader3;
	}

	@FXML
	void initialize() {
		System.err.println("Parent controller"); //$NON-NLS-1$

	}

	@FXML
	public void keyRegPress() {
		try {
			loader.setLocation(getClass().getResource("Registration.fxml")); //$NON-NLS-1$

			VBox regpane = loader.load();
			Scene scene = new Scene(regpane, 1024, 768);

			Stage nStage = new Stage();
			nStage.setScene(scene);
			nStage.setMaximized(false);
			nStage.setTitle("Registration"); //$NON-NLS-1$

			nStage.show();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void keyPlayersPress() {
		try {
			loader2.setLocation(getClass().getResource("GetPlayers.fxml")); //$NON-NLS-1$

			VBox playerspane = loader2.load();
			Scene scene = new Scene(playerspane, 300, 450);

			Stage playerStage = new Stage();
			playerStage.setScene(scene);
			playerStage.setMaximized(false);
			playerStage.setTitle("Players"); //$NON-NLS-1$

			playerStage.show();
		}
		catch (IOException exc) {
			exc.printStackTrace();
		}
	}

	@FXML
	public void keyDrawPress() {
		XRoot matchsroot = new XRoot();
		try {
			loader3.setLocation(getClass().getResource("GenerateMatchs.fxml")); //$NON-NLS-1$

			matchsroot = loader3.load();
			Scene scene = new Scene(matchsroot, 1024, 768);

			Stage playerStage = new Stage();
			playerStage.setScene(scene);
			playerStage.setMaximized(false);
			playerStage.setTitle("Generate Matchs"); //$NON-NLS-1$

			playerStage.show();
		}
		catch (IOException excep) {
			excep.printStackTrace();
		}
	}
}

