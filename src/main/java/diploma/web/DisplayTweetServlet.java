package diploma.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Никита
 */
public class DisplayTweetServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("tweetId", req.getParameter("tweetId"));
        req.getRequestDispatcher("WEB-INF/display.jsp").forward(req, resp);
    }
}
