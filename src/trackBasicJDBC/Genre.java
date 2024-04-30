package trackBasicJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Genre {
    private static Connection con = SQLConnection.getConnection();
    private int id;
    private String name;

    public Genre(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Genre search(int id) {
        String query = "SELECT * FROM Genre WHERE GenreId = ?";
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            String name = result.getString(2);
            result.close();
            statement.close();
            return new Genre(id, name);
        } catch (Exception e) {
            return null;
        }
    }
    @Override
    public String toString() {
        return "Genre{" +
               "id=" + id +
               ", name='" + name + '\'' +
               '}';
    }
}
