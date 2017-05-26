package tournament.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.core.contexts.IEclipseContext;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextInputDialog;
import tournament.model.ITournamentService;
import tournament.model.Player;

public class FXRegistrationController {
	@Inject
	private ITournamentService service;

	@FXML
	Button openRegButton;

	@FXML
	Button closeRegButton;

	@FXML
	Button addPlayerButton;

	@FXML
	Button removePlayerButton;

	public FXRegistrationController() {
		System.err.println("Creating FXRegistrationController"); //$NON-NLS-1$
	}

	@PostConstruct
	void created(IEclipseContext context) {
		System.err.println("Parent post construction"); //$NON-NLS-1$
	}

	@FXML
	void initialize() {
		System.err.println("Parent controller"); //$NON-NLS-1$
	}

	@FXML
	public void keyPress() {
		service.openRegistration();
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Dialog"); //$NON-NLS-1$
		alert.setHeaderText(null);
		alert.setContentText("Registration was opened!"); //$NON-NLS-1$
		alert.showAndWait();
	}

	@FXML
	public void keyClosePress() {
		service.closeRegistration();
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Dialog"); //$NON-NLS-1$
		alert.setHeaderText(null);
		alert.setContentText("Registration was closed!"); //$NON-NLS-1$
		alert.showAndWait();
	}

	@FXML
	public void keyaddPress() {
		TextInputDialog dialog = new TextInputDialog("Name"); //$NON-NLS-1$
		dialog.setTitle("Add Player Dialog"); //$NON-NLS-1$
		dialog.setHeaderText("Add a new player"); //$NON-NLS-1$
		dialog.setContentText("Please enter name of a new player:"); //$NON-NLS-1$

		Optional<String> name = dialog.showAndWait();
		service.addPlayer(name.get());
	}

	@FXML
	public void keyremovePress() {
		List<String> choices = new ArrayList<>();
		choices.addAll(service.getNamePlayers());

		ChoiceDialog<String> dialog = new ChoiceDialog<>("Name", choices); //$NON-NLS-1$
		dialog.setTitle("Remove Player"); //$NON-NLS-1$
		dialog.setHeaderText("Remove player"); //$NON-NLS-1$
		dialog.setContentText("Choose name:"); //$NON-NLS-1$

		Optional<String> name = dialog.showAndWait();
		Player player = null;
		for (int i = 0; i < service.getPlayers().size(); i++) {
			if (service.getPlayers().get(i).getName() == name.get()) {
				player = service.getPlayers().get(i);
				break;
			}
		}
		service.removePlayer(player);
	}

}
