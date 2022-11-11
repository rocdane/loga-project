package app.service.manager;

import app.model.Automobile;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class ManageAutomobileRequest extends HttpServlet {
    private final Automobile automobile;

    /*public void get(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("automobile",automobile);
        this.getServletContext().getRequestDispatcher("/fxml/automobile.fxml").forward(request,response);
    }*/
}
