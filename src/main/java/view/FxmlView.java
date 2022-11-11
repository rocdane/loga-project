package view;

import java.util.ResourceBundle;

public enum FxmlView {
	SIGNUP{
		@Override
		String getTitle() {
			return getStringFromResourceBundle("auth.title");
		}

		@Override
		String getFxmlFile() {
			return "/fxml/sign-up.fxml";
		}

	},
	LOGIN{
		@Override
		String getTitle() {
			return getStringFromResourceBundle("auth.title");
		}
		
		@Override
		String getFxmlFile() {
			return "/fxml/auth-signin.fxml";
		}
		
	},
	MAIN{
		@Override
		String getTitle() {
			return getStringFromResourceBundle("main.title");
		}

		@Override
		String getFxmlFile() {
			return "/fxml/main.fxml";
		}

	};
	
	abstract String getTitle();
	abstract String getFxmlFile();
	
	String getStringFromResourceBundle(String key) {
		return ResourceBundle.getBundle("Bundle").getString(key);
	}
}
