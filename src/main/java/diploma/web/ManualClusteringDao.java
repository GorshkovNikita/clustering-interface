package diploma.web;

import diploma.statistics.dao.BaseDao;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author Никита
 */
public class ManualClusteringDao extends BaseDao {
    public String getRandomTweetId() {
        String tweetId = "";
        Connection connection = getConnection();
        Statement statement = null;
        int clusterId = 0;
        try {

            String queryCount = "SELECT COUNT(*) AS total FROM manualclustering WHERE clusterId IS NULL";
            String query = "SELECT tweetId FROM manualclustering where clusterId IS NULL";
            statement = connection.createStatement();
            ResultSet resultSetCount = statement.executeQuery(queryCount);
            resultSetCount.next();
            int count = resultSetCount.getInt("total");
            ResultSet resultSet = statement.executeQuery(query);

            resultSet.absolute((new Random()).nextInt(count) + 1);
            tweetId = resultSet.getString(1);

            statement.close();
            connection.close();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally {
            try {
                if (statement != null)
                    statement.close();
            }
            catch (SQLException ignored) {}
            try {
                connection.close();
            }
            catch (SQLException ignore) {}
        }
        return tweetId;
    }

    public void clusterTweet(String tweetId, int clusterId) {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        try {
            String query = "UPDATE manualclustering SET clusterId = ? WHERE tweetId = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, clusterId);
            statement.setString(2, tweetId);
            statement.executeUpdate();

            statement.close();
            connection.close();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (statement != null)
                    statement.close();
            }
            catch (SQLException ignored) {}
            try {
                connection.close();
            }
            catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public int createNewCluster(String name) {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        int clusterId = 0;
        try {
            String query = "INSERT INTO manualclusters (name) VALUES (?)";
            statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, name);
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            while (generatedKeys.next()) {
                clusterId = generatedKeys.getInt(1);
            }

            statement.close();
            connection.close();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally {
            try {
                if (statement != null)
                    statement.close();
            }
            catch (SQLException ignored) {}
            try {
                connection.close();
            }
            catch (SQLException ignore) {}
        }
        return clusterId;
    }

    public Map<Integer, String> getManualClusters() {
        Map<Integer, String> manualClusters = new HashMap<>();
        Connection connection = getConnection();
        Statement statement = null;
        int clusterId = 0;
        try {
            String query = "SELECT id, name FROM manualclusters";
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next())
                manualClusters.put(resultSet.getInt(1), resultSet.getString(2));

            statement.close();
            connection.close();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        finally {
            try {
                if (statement != null)
                    statement.close();
            }
            catch (SQLException ignored) {}
            try {
                connection.close();
            }
            catch (SQLException ignore) {}
        }
        return manualClusters;
    }
}
