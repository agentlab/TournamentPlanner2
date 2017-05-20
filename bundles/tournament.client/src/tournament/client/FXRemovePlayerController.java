package tournament.client;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.core.contexts.IEclipseContext;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import tournament.model.ITournamentService;
import tournament.model.Player;

public class FXRemovePlayerController {
	@Inject
	private ITournamentService service;

	@FXML
	Button removePlayerButton;

	@FXML
	ChoiceBox<String> playerBox;

	@FXML
	Button selectPlayerButton;

	@PostConstruct
	void created(IEclipseContext context) {
		System.err.println("Remove Player post construction"); //$NON-NLS-1$
	}

	@FXML
	void initialize() {
		System.err.println("Remove Player controller"); //$NON-NLS-1$
	}

	@FXML
	void keyPress() {
		Player player = null;
		for (int i = 0; i < service.getPlayers().size(); i++) {
			if (service.getPlayers().get(i).getName() == playerBox.getValue()) {
				player = service.getPlayers().get(i);
				break;
			}
		}
		service.removePlayer(player);
	}

	@FXML
	public void keyPressSelect() {
		ObservableList<String> playerList = FXCollections.observableArrayList(service.getNamePlayers());
		playerBox.setItems(playerList);
	}
}