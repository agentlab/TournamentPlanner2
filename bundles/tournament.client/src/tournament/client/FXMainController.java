package tournament.client;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.fx.core.di.LocalInstance;

import de.fxdiagram.core.XRoot;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
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

	@FXML
	StackPane acContent;

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
	public void keyRegPress(ActionEvent event) {
		try {
			loader.load(getClass().getResource("Registration.fxml").openStream());
		}
		catch (IOException e) {
		}
		VBox root = loader.getRoot();
		acContent.getChildren().clear();
		acContent.getChildren().add(root);

	}

	@FXML
	public void keyPlayersPress(ActionEvent event) {
		try {
			loader2.load(getClass().getResource("GetPlayers.fxml").openStream()); //$NON-NLS-1$
		}
		catch (IOException e) {
		}
		VBox root2 = loader2.getRoot();
		acContent.getChildren().clear();
		acContent.getChildren().add(root2);
	}

	@FXML
	public void keyDrawPress(ActionEvent event) {
		XRoot matchsroot = new XRoot();
		try {
			loader3.load(getClass().getResource("GenerateMatchs.fxml").openStream()); //$NON-NLS-1$
		}
		catch (IOException e) {
		}
		matchsroot = loader3.getRoot();
		acContent.getChildren().clear();
		acContent.getChildren().add(matchsroot);
	}
}

