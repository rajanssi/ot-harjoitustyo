package sudoku.dao;

import java.sql.*;

/**
 * Loads and saves data to database.
 */
public class DbStatisticsDao {

    private Connection connection;

    /**
     * Connects database to the url and sets up a new database if one hasn't
     * been created yet.
     *
     * @param url name of the database url
     */
    public DbStatisticsDao(String url) {
        connection = connect(url);
        init();
    }

    private Connection connect(String dbFile) {
        try {
            connection = DriverManager.getConnection(dbFile);
        } catch (SQLException e) {
            return null;
        }

        return connection;
    }

    /**
     * Inserts a completed game to the database.
     *
     * @param time Takes in the duration of the completed game
     * @return true on success
     */
    public boolean insertGame(int time) {
        String sqlInsert = "INSERT INTO Games(duration) VALUES (?)";

        try (PreparedStatement statement = connection.prepareStatement(sqlInsert)) {
            statement.setInt(1, time);
            statement.executeUpdate();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Receives all the data from the database.
     *
     * @return integer array that contains count, average, minimum and maximum
     * durations for each game.
     */
    public int[] getAll() {
        String query = "SELECT COUNT(*), AVG(Duration), MIN(Duration), MAX(Duration) FROM Games";
        int[] data = new int[4];
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet results = statement.executeQuery();
            data[0] = results.getInt("COUNT(*)");
            data[1] = results.getInt("AVG(Duration)");
            data[2] = results.getInt("MIN(Duration)");
            data[3] = results.getInt("MAX(Duration)");
        } catch (Exception e) {
            return null;
        }
        return data;
    }

    private boolean init() {
        String sqlCreate = "CREATE TABLE IF NOT EXISTS Games (duration INT)";

        try (Statement statement = connection.createStatement()) {
            statement.execute(sqlCreate);
        } catch (SQLException e) {
            return false;
        }

        return true;
    }

    /**
     * Delete all records from this table (mainly for testing purposes).
     *
     * @return true on success
     */
    public boolean emptyTables() {
        String sqlCreate = "DROP TABLE Games";
        try (Statement statement = connection.createStatement()) {
            statement.execute(sqlCreate);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
