package diploma.web;

import com.google.gson.Gson;
import diploma.statistics.GeneralStatistics;
import diploma.statistics.MacroClusteringStatistics;
import diploma.statistics.dao.MacroClusteringStatisticsDao;
import javafx.util.Pair;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

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
        Map<Integer, MacroClusteringStatistics> statistics = macroClusteringStatisticsDao.getMacroClusteringStatistics();
        statistics = sortStatistics(statistics);
        GeneralStatistics generalStatistics = macroClusteringStatisticsDao.getGeneralStatistics();
        req.setAttribute("statistics", statistics);
        req.setAttribute("generalStatistics", generalStatistics);
        req.getRequestDispatcher("WEB-INF/index.jsp").forward(req, resp);
    }

    /**
     * Отсортировать по количеству документов
     * @param statistics
     * @return
     */
    private Map<Integer, MacroClusteringStatistics> sortStatistics(Map<Integer, MacroClusteringStatistics> statistics) {
        List<Map.Entry<Integer, MacroClusteringStatistics>> sorting = new LinkedList<Map.Entry<Integer, MacroClusteringStatistics>>(statistics.entrySet());
        Collections.sort(sorting, new Comparator<Map.Entry<Integer, MacroClusteringStatistics>>() {
            public int compare(Map.Entry<Integer, MacroClusteringStatistics> o1, Map.Entry<Integer, MacroClusteringStatistics> o2) {
                return -1 * ((Integer) o1.getValue().getNumberOfDocuments()).compareTo(o2.getValue().getNumberOfDocuments());
            }
        });
        Map<Integer, MacroClusteringStatistics> sorted = new LinkedHashMap<Integer, MacroClusteringStatistics>();
        for (Map.Entry<Integer, MacroClusteringStatistics> entry : sorting) {
            sorted.put(entry.getKey(), entry.getValue());
        }
        return sorted;
    }
}
