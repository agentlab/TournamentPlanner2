package tournament.client;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.fx.core.di.LocalInstance;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXRippler;

import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.animation.Transition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;
import tournament.model.ITournamentService;
/**
 * @author Vilkova
 *
 */
//@ViewController(value = "/tournament/client/MainView.fxml", title = "Material Design Example")
public class FXMainController {
	@Inject
	private ITournamentService service;

	FXMLLoader loader;

	/*FXMLLoader loader2;

	FXMLLoader loader3;*/

	@FXMLViewFlowContext
	private ViewFlowContext context;

	@FXML
	JFXDrawer acContent;

	@FXML
    private StackPane titleBurgerContainer;

	@FXML
	private JFXHamburger titleBurger;

	@FXML
	JFXRippler optionsRippler;

	@FXML
	StackPane optionsBurger;

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
	}

	@FXML
	void initialize() throws FlowException {
		System.err.println("Parent controller"); //$NON-NLS-1$

		// init the title hamburger icon
		acContent.setOnDrawerOpening(e -> {
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
			}
			else {
				acContent.close();
			}
		});

		// create the inner flow and content
		/*context = new ViewFlowContext();
		// set the default controller
		Flow innerFlow = new Flow(FXRegistrationController.class);
		
		final FlowHandler flowHandler = innerFlow.createHandler(context);
		context.register("ContentFlowHandler", flowHandler);
		context.register("ContentFlow", innerFlow);
		final Duration containerAnimationDuration = Duration.millis(320);
		acContent.setContent(flowHandler.start(new ExtendedAnimatedFlowContainer(containerAnimationDuration, SWIPE_LEFT)));
		context.register("ContentPane", acContent.getContent().get(0));
		
		Flow sideMenuFlow = new Flow(SideMenuController.class);
		final FlowHandler sideMenuFlowHandler = sideMenuFlow.createHandler(context);
		acContent.setSidePane(sideMenuFlowHandler.start(new ExtendedAnimatedFlowContainer(containerAnimationDuration, SWIPE_LEFT)));*/

		try {
			loader.load(getClass().getResource("SideMenu.fxml").openStream());
		}
		catch (IOException e) {
		}

		StackPane root = loader.getRoot();
		acContent.setSidePane(root);
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

