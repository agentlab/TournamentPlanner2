package tournament.client;

import java.io.IOException;
import java.net.URL;

import javax.annotation.PostConstruct;

import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.fx.core.di.LocalInstance;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MainApplication {

	@PostConstruct
	void run(IApplicationContext applicationContext, javafx.application.Application jfxApplication, Stage primaryStage, @LocalInstance FXMLLoader loader) {
		URL resource = getClass().getResource("MainView.fxml"); //$NON-NLS-1$
        loader.setLocation(resource);
		try {
			HBox pane = loader.load();
			Scene s = new Scene(pane, 700, 700);
			primaryStage.setScene(s);
			primaryStage.setTitle("Tournament Planner"); //$NON-NLS-1$
			primaryStage.show();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}

