package sudoku.dao;

import java.sql.*;

public class DbStatisticsDao {

    private Connection connection;

    public DbStatisticsDao(String dbFile) {
        connection = connect(dbFile);
        init();
    }

    private Connection connect(String dbFile) {
        try {
            connection = DriverManager.getConnection(dbFile);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }

        return connection;
    }

    public void insertGame(int time) {
        String sqlInsert = "INSERT INTO Games(duration) VALUES (?)";

        try (PreparedStatement statement = connection.prepareStatement(sqlInsert)) {
            statement.setInt(1, time);
            statement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

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

    private void init() {
        String sqlCreate = "CREATE TABLE IF NOT EXISTS Games (duration INT)";

        try (Statement statement = connection.createStatement()) {
            statement.execute(sqlCreate);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}
