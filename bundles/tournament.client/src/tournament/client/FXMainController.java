package tournament.client;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.fx.core.di.LocalInstance;

import com.jfoenix.controls.JFXDrawer;

import io.datafx.controller.ViewController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import tournament.model.ITournamentService;
/**
 * @author Vilkova
 *
 */
@ViewController(value = "/tournament/client/MainView.fxml", title = "Material Design Example")
public class FXMainController {
	@Inject
	private ITournamentService service;

	FXMLLoader loader;

	/*FXMLLoader loader2;

	FXMLLoader loader3;*/


	@FXML
	JFXDrawer acContent;

	/*@FXML
    private StackPane titleBurgerContainer;

	@FXML
    private JFXHamburger titleBurger;*/

	/*@FXML
	Label RegButton;

	@FXML
	Label PlayersButton;

	@FXML
	Label DrawButton;*/

	public FXMainController() {
		System.err.println("Creating FXMainController"); //$NON-NLS-1$
	}

	@PostConstruct
	void created(IEclipseContext context, @LocalInstance FXMLLoader loader/*, @LocalInstance FXMLLoader loader2, @LocalInstance FXMLLoader loader3*/) {
		System.err.println("Parent post construction"); //$NON-NLS-1$
		this.loader = loader;
		/*this.loader2 = loader2;
		this.loader3 = loader3;*/

		// init the title hamburger icon
		/*acContent.setOnDrawerOpening(e -> {
            final Transition animation = titleBurger.getAnimation();
            animation.setRate(1);
            animation.play();
        });
		acContent.setOnDrawerClosing(e -> {
            final Transition animation = titleBurger.getAnimation();
            animation.setRate(-1);
            animation.play();
        });
        titleBurgerContainer.setOnMouseClicked(e -> {
            if (acContent.isHidden() || acContent.isHidding()) {
            	acContent.open();
            } else {
            	acContent.close();
            }
        });*/
	}

	@FXML
	void initialize() {
		System.err.println("Parent controller"); //$NON-NLS-1$
	}

	/*@FXML
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
	}*/
}

