package diploma.web;

import diploma.statistics.MacroClusteringStatistics;
import diploma.statistics.dao.MacroClusteringStatisticsDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author Никита
 */
public class IndexServlet extends HttpServlet {
    private MacroClusteringStatisticsDao macroClusteringStatisticsDao;

    @Override
    public void init() throws ServletException {
        macroClusteringStatisticsDao = new MacroClusteringStatisticsDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<Integer, List<MacroClusteringStatistics>> statistics = macroClusteringStatisticsDao.getMacroClusteringStatistics();
        req.setAttribute("statistics", statistics);
        req.getRequestDispatcher("WEB-INF/index.jsp").forward(req, resp);
    }
}
