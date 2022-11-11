package vendor.config;

import java.io.IOException;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

@Component
public class SpringFXMLLoader {
	private final ResourceBundle resourceBundle;
	private final ApplicationContext context;
	
	@Autowired
	public SpringFXMLLoader(ApplicationContext context,ResourceBundle resourceBundle) {
		this.resourceBundle = resourceBundle;
		this.context = context;
	}
	
	public Parent load(String pathToFxml) throws IOException{
		FXMLLoader loader = new FXMLLoader();
		loader.setControllerFactory(context::getBean);//Spring FXML controller factory
		loader.setResources(resourceBundle);
		loader.setLocation(getClass().getResource(pathToFxml));
		return loader.load();		
	}
}
