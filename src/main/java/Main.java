import view.FxmlView;
import view.StageManager;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

@SpringBootApplication
public class Main extends Application {

	protected ConfigurableApplicationContext springContext; 
	protected StageManager stageManager;
	
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	@Override
	public void init() throws IOException {
		System.setProperty("spring.config.name", "application");
		springContext = bootstrapSpringApplicationContext();
	}

	@Override
	public void start(Stage stage) throws Exception {
		stageManager = springContext.getBean(StageManager.class,stage);
		displayInitialScene();
	}
	
	@Override
	public void stop() {
		springContext.close();
		Platform.exit();
	}
	
	protected void displayInitialScene() {
		stageManager.switchScene(FxmlView.LOGIN);
	}
	
	private ConfigurableApplicationContext bootstrapSpringApplicationContext() {
		SpringApplicationBuilder builder = new SpringApplicationBuilder(Main.class);
		String[] args = getParameters().getRaw().stream().toArray(String[]::new);
		builder.headless(false);
		return builder.run(args);
	}
}
