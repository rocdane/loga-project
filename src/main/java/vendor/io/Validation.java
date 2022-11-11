package vendor.io;

import javafx.scene.control.*;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;

public class Validation
{
    private static DateTimeFormatter dateTimeFormatter;
    
    public static boolean is_empty_field(TextField jfxTextField) {
        if (jfxTextField.getText().trim().isEmpty()) {
            return true;
        }
        return false;
    }
    
    public static boolean is_empty_area(TextArea jfxTextField) {
        if (jfxTextField.getText().trim().isEmpty()) {
            return true;
        }
        return false;
    }
    
    public static boolean is_input_size(TextField jfxTextField, int size) {
        if (jfxTextField.getText().trim().length() > size) {
            return false;
        }
        return true;
    }
    
    public static boolean is_valid_email(String text) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        if (!text.trim().matches(regex)) {
            return false;
        }
        return true;
    }
    
    public static boolean is_valid_period(DatePicker debut, DatePicker fin) {
        if (((LocalDate)debut.getValue()).isAfter((ChronoLocalDate)fin.getValue())) {
            return false;
        }
        return true;
    }
    
    public static boolean check_selected(ComboBox jfxComboBox) {
        if (jfxComboBox.getSelectionModel().getSelectedIndex() == -1) {
            return true;
        }
        return false;
    }
    
    public static LocalDate string_to_date(String text) {
        LocalDate date = null;
        if (text != null && !text.trim().isEmpty()) {
            date = LocalDate.parse(text, Validation.dateTimeFormatter);
        }
        return date;
    }
    
    public static String date_to_string(LocalDate localDate) {
        String text = null;
        if (localDate != null) {
            text = Validation.dateTimeFormatter.format(localDate);
        }
        return text;
    }
    
    static {
        Validation.dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    }
}
