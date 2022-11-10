package chernova.weebo;

import chernova.weebo.beans.ResponseBean;
import chernova.weebo.beans.TableBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@WebServlet("/check")
public class AreaCheckServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TableBean table = (TableBean) req.getSession().getAttribute("table");
        ResponseBean response = new ResponseBean();
        String xString = req.getParameter("x");
        if(xString.length()>=10) {xString = xString.substring(0,11);}
        double x = Double.parseDouble(xString);
        String yString = req.getParameter("y");
        if(yString.length()>=10) {yString = yString.substring(0,11);}
        double y = Double.parseDouble(yString);
        String rString = req.getParameter("r");
        if(rString.length()>=10) {rString = rString.substring(0,11);}
        double r = Double.parseDouble(rString);
        long start = System.nanoTime();
        String curTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        long exTime = (System.nanoTime() - start) / 1000;
        response.setX(x);
        response.setY(y);
        response.setR(r);
        response.setCurrentTime(curTime);
        response.setExecutionTime(exTime);
        response.setResult(hit(x, y, r));
        table.add(response);
        req.getSession().setAttribute("table", table);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    private boolean hitRectangle(double x, double y, double r) {
        return (x >= -r && x <= 0) && (y >= 0 && y <= r / 2);
    }

    private boolean hitTriangle(double x, double y, double r) {
        return (x <= 0 && x >= -r && y >= -r && y <= 0 && y >= -x - r);
    }

    private boolean hitSector(double x, double y, double r) {
        return (x >= 0 && x <= r && y >= 0 && y <= r && x * x + y * y <= r * r);
    }

    private boolean hit(double x, double y, double r) {
        return hitRectangle(x, y, r) || hitTriangle(x, y, r) || hitSector(x, y, r);
    }
}