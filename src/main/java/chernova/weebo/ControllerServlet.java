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
                if(xString.length()>=10) {xString = xString.substring(0,11);req.setAttribute("x",xString);}
                x = Double.parseDouble(xString);
                if(yString.length()>=10) {yString = yString.substring(0,11);req.setAttribute("y",yString);}
                y = Double.parseDouble(yString);
                if(rString.length()>=10) {rString = rString.substring(0,11);req.setAttribute("r",rString);}
                r = Double.parseDouble(rString);
            } catch (NumberFormatException ignored) {
            }
        }
        if ("XMLHttpRequest".equals(req.getHeader("X-Requested-With"))) {
            if (x != null && y != null && r != null) {
                getServletContext().getRequestDispatcher("/check").forward(req, resp);
            } else {
                getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
            }
        }
        else {
            if (x != null && y != null && checkX(x) && checkY(y) && checkR(r)) {
                getServletContext().getRequestDispatcher("/check").forward(req, resp);
            } else {
                getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
            }
    }}
    final boolean checkX(Double x) {
        return  x==3||x==2||x==1||x==0||x==-1||x==-2||x==-3||x==-4||x==-5;
    }

    final boolean checkY(Double y) {
        return y>-5&&y<5;

    }

    final boolean checkR(Double r) {
        return r==1||r==1.5||r==2||r==2.5||r==3;
    }
}
