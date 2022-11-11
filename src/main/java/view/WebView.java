package view;

import java.util.ResourceBundle;

public enum WebView {

    HOME{
        @Override
        String getTitle() {
            return getStringFromResourceBundle("login.title");
        }

        @Override
        String getHtmlFile() {
            return "/fxml/auth-signin.fxml";
        }

    };

    abstract String getTitle();
    abstract String getHtmlFile();

    String getStringFromResourceBundle(String key) {
        return ResourceBundle.getBundle("Bundle").getString(key);
    }
}
