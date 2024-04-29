package albumBasicJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Artista {
    private int id;
    private String nom;
    private static Connection con = Connexio.getConnection();

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public Artista(int idArtista, String nom) {
        this.id = idArtista;
        this.nom = nom;
    }
    public static void creaArtista(Artista artista) {
        if (verificarArtista(artista.getId())) {
            String query = "INSERT INTO Artist (ArtistId, Name) VALUES (?, ?)";
            try (PreparedStatement ps = con.prepareStatement(query)) {
                con.setAutoCommit(false);
                ps.setInt(1, artista.getId());
                ps.setString(2, artista.getNom());
                ps.executeUpdate();
                con.commit();
                System.out.println("S'ha inserit correctament el nou artista ID: " + artista);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                System.out.println("Error: no s'ha pogut connectar amb la base de dades");
            }
        } else {
            System.out.println("Error: ja existeix un artista amb aquest ID");
        }
    }
    public static boolean verificarArtista(int idArtista) {
        String query = "SELECT * FROM Artist WHERE ArtistId=?";
        try (PreparedStatement ps = con.prepareStatement(query);) {
            con.setAutoCommit(false);
            ps.setInt(1, idArtista);
            ResultSet rs = ps.executeQuery();
            return !rs.next();
        } catch (SQLException e) {
            System.err.println(e.getClass().getName());
            return true;
        }
    }

    @Override
    public String toString() {
        return "ID: " + id +
               " Nom: " + nom;
    }
}
