package diploma.web;

import diploma.clustering.EnhancedStatus;
import diploma.clustering.statusesfilters.SportsBetsFilter;
import diploma.clustering.statusesfilters.TweetLengthFilter;
import twitter4j.TwitterException;
import twitter4j.TwitterObjectFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @author Никита
 */
public class ManualClusteringServlet extends HttpServlet {
    private ManualClusteringDao dao;
    private TweetLengthFilter tweetLengthFilter;
    private SportsBetsFilter sportsBetsFilter;

    @Override
    public void init() throws ServletException {
        dao = new ManualClusteringDao();
        tweetLengthFilter = new TweetLengthFilter();
        sportsBetsFilter = new SportsBetsFilter();
    }

    /**
     * Получение следующего твита из файла
     * @param req - запрос
     * @param resp - ответ
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // пришлось перенести из-за того, что можно много раз обновлять страницу и читатель будет двигаться по файлу,
        // в так он будет всегда отображать твит следующий за последним успешно обработанным
//        try (BufferedReader reader = new BufferedReader(new FileReader(new File(filename)))) {
//            for (int i = 1; i <= lastProcessedTweetPosition; i++)
//                reader.readLine();
//            position = lastProcessedTweetPosition + 1;
//            String line = reader.readLine();
//            EnhancedStatus status;
//            if (line != null) {
//                try {
//                    status = new EnhancedStatus(TwitterObjectFactory.createStatus(line));
//                    while (true) {
//                        if (tweetLengthFilter.filter(status) && sportsBetsFilter.filter(status))
//                            break;
//                        status = new EnhancedStatus(TwitterObjectFactory.createStatus(reader.readLine()));
//                        position++;
//                    }
//                    req.setAttribute("tweetId", Long.toString(status.getStatus().getId()));
//                    req.setAttribute("manualClusters", dao.getManualClusters());
//                } catch (TwitterException ignored) {}
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        String tweetId = dao.getRandomTweetId();
        req.setAttribute("tweetId", tweetId);
        req.setAttribute("manualClusters", dao.getManualClusters());
        req.getRequestDispatcher("WEB-INF/manual.jsp").forward(req, resp);
    }

    /**
     * Кластеризовать твит
     * @param req - запрос
     * @param resp - ответ
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tweetId = req.getParameter("tweetId");
        if (req.getParameter("clusterId") != null) {
            dao.clusterTweet(tweetId, Integer.parseInt(req.getParameter("clusterId")));
        }
        else {
            int clusterId = dao.createNewCluster(req.getParameter("clusterName"));
            dao.clusterTweet(tweetId, clusterId);
        }
        resp.setStatus(200);
    }

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("D:\\MSU\\diploma\\tweets-sets\\2017-04-09-sport-events.txt")))) {
            EnhancedStatus status;
            String line;
            do {
                line = reader.readLine();
                try {
                    status = new EnhancedStatus(TwitterObjectFactory.createStatus(line));
                    System.out.println(status.getStatus().getId());
                } catch (TwitterException ignored) {}
            } while (line != null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
