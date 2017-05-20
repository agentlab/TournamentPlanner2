package tournament.client;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.fx.core.di.LocalInstance;

import de.fxdiagram.core.XConnection;
import de.fxdiagram.core.XDiagram;
import de.fxdiagram.core.XRoot;
import de.fxdiagram.lib.simple.SimpleNode;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
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

	@FXML
	XRoot myroot;

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

		XDiagram mydiagram = new XDiagram();
		mydiagram.minHeight(600);
		mydiagram.minWidth(600);
		myroot.setRootDiagram(mydiagram);

		myroot.activate();

		SimpleNode player1 = new SimpleNode("Player1"); //$NON-NLS-1$
		SimpleNode player2 = new SimpleNode("Player2"); //$NON-NLS-1$
		player2.setLayoutY(100);
		player2.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				service.openRegistration();
			};
		});
		mydiagram.nodesProperty().add(player1);
		mydiagram.nodesProperty().add(player2);
		XConnection link = new XConnection(player1, player2);
		mydiagram.connectionsProperty().add(link);

	}

	@FXML
	void keyPress() {
		service.openRegistration();
	}

	@FXML
	public void keyClosePress() {
		service.closeRegistration();
		Parent root = null;
		try {
			loader.setLocation(getClass().getResource("GetPlayers.fxml")); //$NON-NLS-1$
			root = loader.load();

			Scene scene = new Scene(root);
			Stage nStage = new Stage();
			nStage.setScene(scene);
			nStage.setMaximized(false);
			nStage.setTitle("Get Players"); //$NON-NLS-1$
			nStage.show();
		}
		catch (IOException e) {
			e.printStackTrace();
		}

	}
}

