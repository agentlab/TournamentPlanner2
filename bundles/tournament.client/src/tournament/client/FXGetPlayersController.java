package tournament.client;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.core.contexts.IEclipseContext;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import tournament.model.ITournamentService;
import tournament.model.Player;

public class FXGetPlayersController {
	private ObservableList<Player> playersData = FXCollections.observableArrayList();

	@Inject
	private ITournamentService service;

	@FXML
	TableView<Player> tablePlayers;

	@FXML
	TableColumn<Player, Integer> idColumn;

	@FXML
	TableColumn<Player, String> nameColumn;

	@FXML
	TableColumn<Player, Integer> pointsColumn;

	@FXML
	Button getPlayersButton;

	@PostConstruct
	void created(IEclipseContext context) {
		System.err.println("GetPlayers post construction"); //$NON-NLS-1$
	}

	@FXML
	void initialize() {
		System.err.println("GetPlayers controller"); //$NON-NLS-1$
		idColumn.setCellValueFactory(new PropertyValueFactory<Player, Integer>("id")); //$NON-NLS-1$
		nameColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("name")); //$NON-NLS-1$
		pointsColumn.setCellValueFactory(new PropertyValueFactory<Player, Integer>("points")); //$NON-NLS-1$
	}

	private void initData() {
		for (int i = 0; i < service.getPlayers().size(); i++) {
			playersData.add(service.getPlayers().get(i));
		}
	}

	@FXML
	public void keyPress() {
		tablePlayers.getItems().clear();
		initData();
		tablePlayers.setItems(playersData);
	}

}
