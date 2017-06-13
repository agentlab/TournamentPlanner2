package tournament.client;

import java.io.IOException;
import java.net.URL;

import javax.annotation.PostConstruct;

import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.fx.core.di.LocalInstance;

import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MainApplication {
	@FXMLViewFlowContext
    private ViewFlowContext flowContext;

	@PostConstruct
	void run(IApplicationContext applicationContext, javafx.application.Application jfxApplication, Stage stage, @LocalInstance FXMLLoader loader) {
		//Flow flow = new Flow(FXMainController.class);
        //DefaultFlowContainer container = new DefaultFlowContainer();
        //flowContext = new ViewFlowContext();
        //flowContext.register("Stage", stage); //$NON-NLS-1$
        try {
			//flow.createHandler(flowContext).start(container);

			//JFXDecorator pane = new JFXDecorator(stage, container.getView());
			//pane.setCustomMaximize(true);

			URL resource = getClass().getResource("MainView.fxml"); //$NON-NLS-1$
	        loader.setLocation(resource);
	        StackPane pane = loader.load();

			Scene scene = new Scene(pane, 800, 850);//decorator
			final ObservableList<String> stylesheets = scene.getStylesheets();
	        stylesheets.addAll( MainApplication.class.getResource("/css/jfoenix-components.css").toExternalForm(),
	        					MainApplication.class.getResource("/css/jfoenix-main-demo.css").toExternalForm());
			stage.setScene(scene);
			stage.setTitle("Tournament Planner"); //$NON-NLS-1$
			stage.show();
		}
		//catch (FlowException e1) {
		//	e1.printStackTrace();
		//}
        catch (IOException e) {
			e.printStackTrace();
		}
	}
}
