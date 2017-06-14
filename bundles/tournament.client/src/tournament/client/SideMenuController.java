/**
 *
 */
package tournament.client;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.fx.core.di.LocalInstance;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXListView;

import io.datafx.controller.ViewController;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
/**
 * @author Vilkova
 *
 */
@ViewController(value = "/tournament/client/SideMenu.fxml", title = "Material Design Example")
public class SideMenuController {

	@FXML
	JFXDrawer acContent;

	@FXMLViewFlowContext
	private ViewFlowContext context;

	FXMLLoader loader;

	@FXML
	JFXListView sideList;

	@FXML
	Label RegButton;
	@FXML
	Label PlayersButton;
	@FXML
	Label DrawButton;

	/**
	   * init fxml when loaded.
	   */
	@PostConstruct
	void created(IEclipseContext context, @LocalInstance FXMLLoader loader/*, @LocalInstance FXMLLoader loader2, @LocalInstance FXMLLoader loader3*/) {
		System.err.println("Parent post construction"); //$NON-NLS-1$
		this.loader = loader;
	}

	public SideMenuController() {
	}

	@FXML
	public void keyRegPress() {
		try {
			loader.load(getClass().getResource("Registration.fxml").openStream());
		}
		catch (IOException e) {
		}

		VBox root = loader.getRoot();
		acContent.setSidePane(root);
	}

	@FXML
	public void keyPlayersPress() {
	}

	@FXML
	public void keyDrawPress() {
	}

}
