package chernova.weebo;

import chernova.weebo.beans.TableBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/controller")
public class ControllerServlet extends HttpServlet {

    private final TableBean table = new TableBean();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.getSession().setAttribute("table", table);
        String xString = req.getParameter("x");
        String yString = req.getParameter("y");
        String rString = req.getParameter("r");
        Double x = null;
        Double y = null;
        Double r = null;
        if (xString != null && yString != null && rString != null) {
            try {
                x = Double.parseDouble(xString);
                y = Double.parseDouble(yString);
                r = Double.parseDouble(rString);
            } catch (NumberFormatException ignored) {
            }
        }
        if (x != null && y != null && r != null) {
            getServletContext().getRequestDispatcher("/check").forward(req, resp);
        } else {
            getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
        }
    }
}