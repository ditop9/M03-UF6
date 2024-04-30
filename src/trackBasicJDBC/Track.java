package trackBasicJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Track {
    private static Connection con = SQLConnection.getConnection();
    private int id;
    private String name;
    private Genre genre;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Track(int id, String name) {
        this.id = id;
        this.name = name;
    }

    private static int createNewTrackId() {
        try {
            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery("SELECT MAX(TrackId) FROM Track");
            return result.getInt(1) + 1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }

    public static List<Track> listAll() {
        List<Track> tracks = new ArrayList<>();
        String query = "SELECT * FROM Track";
        try (PreparedStatement statement = con.prepareStatement(query)) {
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                tracks.add(new Track(result.getInt(1), result.getString(2)));
            }
            result.close();
            statement.close();
            return tracks;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static Track search(int id) {
        String query = "SELECT * FROM Track WHERE TrackId = ?";
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            String name = result.getString(2);
            result.close();
            statement.close();
            return new Track(id, name);
        } catch (Exception e) {
            return null;
        }
    }

    public static boolean create(String name) {
        int id = createNewTrackId();
        String query = "CREATE Track INSERT (TrackId, Name) VALUES (?, ?)";
        try (PreparedStatement statement = con.prepareStatement(query)) {
            con.setAutoCommit(false);
            statement.setInt(1, id);
            statement.setString(2, name);
            ResultSet result = statement.executeQuery();
            con.commit();
            result.close();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean update(String name) {
        String query = "UPDATE Track SET Name = ? WHERE TrackId = ?";
        try (PreparedStatement statement = con.prepareStatement(query)) {
            con.setAutoCommit(false);
            statement.setString(1, name);
            statement.setInt(2, id);
            int rowsAffected = statement.executeUpdate();
            con.commit();
            return rowsAffected > 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static boolean delete(int id) {
        String query = "DELETE Track WHERE TrackId = ?";
        try (PreparedStatement statement = con.prepareStatement(query)) {
            con.setAutoCommit(false);
            statement.setInt(1, id);
            int rowsAffected = statement.executeUpdate();
            con.commit();
            return rowsAffected > 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public String toString() {
        return "Track" + " ID " + id + " Nom " + name;
    }


}
