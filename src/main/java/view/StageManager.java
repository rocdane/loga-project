package view;

import java.util.Objects;

import vendor.config.SpringFXMLLoader;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StageManager {

	private static final Logger LOG = getLogger(StageManager.class);
	private final Stage primaryStage;
	private final SpringFXMLLoader springFXMLLoader;
	
	public StageManager(SpringFXMLLoader springFXMLLoader, Stage stage) {
		this.springFXMLLoader = springFXMLLoader;
		this.primaryStage = stage;
	}
	
	public void switchScene(final FxmlView view) {
		Parent viewRootNodeHierarchy = loadViewNodeHierarchy(view.getFxmlFile());
		show(viewRootNodeHierarchy,view.getTitle());
	}
	
	private Parent loadViewNodeHierarchy(String fxmlFile) {

		Parent rootNode = null;

		try {
			rootNode = springFXMLLoader.load(fxmlFile);
			Objects.requireNonNull(rootNode,"A Root FXML node must not be null");
		}catch(Exception exception) {
			logAndExit("Unable to load FXML view"+fxmlFile,exception);
		}
				
		return rootNode;
	}

	private void logAndExit(String msg, Exception exception) {
		// TODO Auto-generated method stub
	}

	public void show(final Parent rootnode,String title){
		Scene scene = prepareScene(rootnode);
		
		primaryStage.setTitle(title);
		primaryStage.setScene(scene);
		primaryStage.sizeToScene();
		primaryStage.centerOnScreen();
		
		try {
			primaryStage.show();
		}catch(Exception exception) {
			logAndExit("Unable to show scene for title "+title,exception);
			LOG.debug(title, exception);
		}
	} 

	private Scene prepareScene(Parent rootnode) {
		Scene scene = primaryStage.getScene();
		if(scene==null) {
			scene = new Scene(rootnode);
		}
		scene.setRoot(rootnode);
		return scene;
	}
}
