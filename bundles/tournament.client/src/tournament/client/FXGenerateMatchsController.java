package tournament.client;

import java.util.Collection;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.fx.core.di.LocalInstance;

import de.fxdiagram.core.XDiagram;
import de.fxdiagram.core.XRoot;
import de.fxdiagram.lib.simple.SimpleNode;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import tournament.model.ITournamentService;
import tournament.model.Match;

public class FXGenerateMatchsController {
	static int intervalY = 100;
	static int intervalX = 0;

	@Inject
	private ITournamentService service;

	@FXML
	XRoot matchsroot;

	FXMLLoader loader;

	public FXGenerateMatchsController() {
		System.err.println("Creating FXGenerateMatchsController"); //$NON-NLS-1$
	}

	@PostConstruct
	void created(IEclipseContext context, @LocalInstance FXMLLoader loader) {
		System.err.println("Parent post construction"); //$NON-NLS-1$
		this.loader = loader;
	}

	@FXML
	void initialize() {
		System.err.println("Parent controller"); //$NON-NLS-1$

		XDiagram matchsdiagram = new XDiagram();
		matchsroot.setRootDiagram(matchsdiagram);
		matchsroot.activate();

		SimpleNode generateMatchs = new SimpleNode("Generate Matchs"); //$NON-NLS-1$
		generateMatchs.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				Collection<Match> matchs = service.generateMatchs();
				matchs.forEach(match -> {
					String name1 = match.getMatchPlayers().get(0).getName();
					String name2 = match.getMatchPlayers().get(1).getName();
					SimpleNode player = new SimpleNode(name1 + "\n\n" + name2); //$NON-NLS-1$
					player.setLayoutX(intervalX);
					player.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
						@Override
						public void handle(MouseEvent e) {
							Alert alert = new Alert(AlertType.CONFIRMATION);
							alert.setTitle("Set Winner"); //$NON-NLS-1$
							alert.setHeaderText("Who won?"); //$NON-NLS-1$
							alert.setContentText("Choose winner of this match"); //$NON-NLS-1$

							ButtonType buttonTypeOne = new ButtonType(match.getMatchPlayers().get(0).getName());
							ButtonType buttonTypeTwo = new ButtonType(match.getMatchPlayers().get(1).getName());
							ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE); //$NON-NLS-1$

							alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeCancel);
							Optional<ButtonType> result = alert.showAndWait();
							if (result.get() == buttonTypeOne) {
								match.setWinner(match.getMatchPlayers().get(0));
								service.getManagedTournament().removeLosers();
							}
							else if (result.get() == buttonTypeTwo) {
								match.setWinner(match.getMatchPlayers().get(1));
								service.getManagedTournament().removeLosers();
							}
						}
					});
					player.setLayoutY(intervalY);
					intervalY += 100;
					matchsdiagram.nodesProperty().add(player);
				});
				intervalY = 150;
				intervalX += 100;
			};
		});
		matchsdiagram.nodesProperty().add(generateMatchs);

//		mydiagram.nodesProperty().add(player2);
//		XConnection link = new XConnection(player1, player2);
//		mydiagram.connectionsProperty().add(link);

	}
}