package albumDao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class MainDAO
{
    public static void main( String args[] ) throws SQLException {
        Connection c = Connexio.getConnection();
        AlbumDao albumDao = new AlbumDaoImplementacio();

        Scanner sc = new Scanner(System.in);
        int opcio = sc.nextInt(); sc.nextLine();

        while (opcio!=0){
            switch(opcio){
                case 1: {
                    albumDao.getAlbums();
                    break;
                }
                case 2:{
                    System.out.println("Introdueix quin album vols veure");
                    int idAlbum = sc.nextInt();
                    albumDao.getAlbum(idAlbum);
                    break;
                }
                case 3:{
                    System.out.println("Introdueix el titol nou");
                    String titol = sc.nextLine();
                    System.out.println("Introdueix idArtista nou");
                    int idArtista = sc.nextInt();
                    //System.out.println(albumDao.add(titol, idArtista));
                    break;
                }
                case 4:{
                    System.out.println("Introdueix quin album vols modificar");
                    int idAlbum = sc.nextInt();
                    System.out.println("Introdueix el nou títol");
                    String titol = sc.nextLine();
                    System.out.println("Introdueix el idArtista nou");
                    int idArtista = sc.nextInt();
                    //album.modificaAlbum(idAlbum, titol, idArtista);
                    break;
                }
                case 5:{
                    System.out.println("Introdueix quin album vols eliminar");
                    int idAlbum = sc.nextInt();
                    //album.eliminaAlbum(idAlbum);
                    break;
                }
                case 0:{

                    break;
                }
                default:{
                    System.out.println("Introdueix un nombre vàlid del 0 al 6");
                    break;
                }
            }

            //main.menu();
            opcio = sc.nextInt();sc.nextLine();
        }

        c.close();
    }
}