package albumDao;

import java.sql.SQLException;
import java.util.List;


public class Driver {

    public static void main(String[] args) throws SQLException
    {

        Album album = new Album();
        album.setNom("Haiderrrrrrr");
        album.setIdArtista(1);
        AlbumDaoImplementacio albumDao = new AlbumDaoImplementacio();

        // add
        albumDao.add(album);

        // read
        Album albumRead = albumDao.getAlbum(1);
        System.out.println(albumRead.getIdAlbum() + " "
                + albumRead.getNom() + " "
                + albumRead.getIdArtista());

        // read All
        List<Album> albums = albumDao.getAlbums();
        for (Album a : albums) {
            System.out.println(a);
        }

        // update
        Album tempAlbum = albumDao.getAlbum(1);
        tempAlbum.setNom("NOM NOU");
        albumDao.update(tempAlbum);

        // delete
        albumDao.delete(346);
    }
}