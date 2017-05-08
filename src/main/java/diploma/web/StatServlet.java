package diploma.web;

import com.google.gson.Gson;
import diploma.statistics.MacroClusteringStatistics;
import diploma.statistics.TimeAndProcessedPerUnit;
import diploma.statistics.dao.MacroClusteringStatisticsDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * @author Никита
 */
public class StatServlet extends HttpServlet {
    private MacroClusteringStatisticsDao macroClusteringStatisticsDao;

    @Override
    public void init() throws ServletException {
        macroClusteringStatisticsDao = new MacroClusteringStatisticsDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        String[] pathParts = pathInfo.split("/");
        int id = 0;
        try {
            id = Integer.parseInt(pathParts[1]);
        }
        catch (NumberFormatException ex) {
            resp.setStatus(400);
            return;
        }

//        Map<Integer, List<MacroClusteringStatistics>> statistics = macroClusteringStatisticsDao.getMacroClusteringStatistics();
//        statistics = sortStatistics(statistics);
//        for (Map.Entry<Integer, List<MacroClusteringStatistics>> stat : statistics.entrySet()) {
//            MacroClusteringStatistics s = stat.getValue().get(stat.getValue().size() - 1);
//            s.setTopTerms(macroClusteringStatisticsDao.getTopTerms(s.getId()));
//        }
//        req.setAttribute("statistics", statistics);
//        req.getRequestDispatcher("WEB-INF/index.jsp").forward(req, resp);

        MacroClusteringStatistics statistics = macroClusteringStatisticsDao.getMacroClusteringStatistics(id);
        List<TimeAndProcessedPerUnit> timeAndProcessedPerUnitList = macroClusteringStatisticsDao.getTimeAndProcessedPerUnitList(statistics.getClusterId());
        StatResponse response = new StatResponse(statistics, timeAndProcessedPerUnitList);
        String json = new Gson().toJson(response);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json);
    }
}
