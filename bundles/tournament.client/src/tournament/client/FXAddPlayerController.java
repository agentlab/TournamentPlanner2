package tournament.client;


import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.core.contexts.IEclipseContext;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import tournament.model.ITournamentService;
import tournament.model.Player;

public class FXAddPlayerController {
	@Inject
	private ITournamentService service;

	@FXML
	Button addPlayerButton;

	@FXML
	TextField namePlayer;

	@FXML
	TextField idPlayer;

	@FXML
	TextField pointsPlayer;

	@FXML
	Button deletePlayerButton;

	@PostConstruct
	void created(IEclipseContext context) {
		System.err.println("Add Player post construction"); //$NON-NLS-1$
	}

	@FXML
	void initialize() {
		System.err.println("Add Player controller"); //$NON-NLS-1$
	}

	@FXML
	void keyPress(ActionEvent event) {
		Player player = new Player();
		player.setId(Integer.parseInt(idPlayer.getText()));
		player.setPoints(Integer.parseInt(pointsPlayer.getText()));
		player.setName(namePlayer.getText());
		service.addPlayer(player);
	}
}