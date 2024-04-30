package trackBasicJDBC;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class Main {
    private final static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int option = -1;
        while (option != 0) {
            cli();
            try {
                option = sc.nextInt();
                handleOption(option);
            } catch (InputMismatchException e) {
                System.out.println("Error: no és una opció vàlida");
                sc.nextLine();
            }
        }
    }

    private static void cli() {
        System.out.println("""
                ESCULL UNA OPCIÓ
                1. Llistar Tracks
                2. Buscar Track per ID
                3. Crear Track
                4. Actualitzar Track
                5. Eliminar Track""");
    }

    private static void handleOption(int option) {
        switch (option) {
            case 1:
                listAllTracks();
                break;
            case 2:
                searchTrackById();
                break;
            case 3:
                createTrack();
                break;
            case 4:
                updateTrack();
                break;
            case 5:

                break;
            case 0:
                System.exit(0);
                System.out.println("Es tanca el programa...");
                break;
            default:
                System.out.println("Error: no és una opció vàlida");
                break;
        }
    }

    private static void listAllTracks() {
        List<Track> tracks = Track.listAll();
        if (tracks != null) {
            for (Track t : tracks) {
                System.out.println(t);
            }
        } else System.out.println("Error: no s'han trobat dades");
    }

    private static void searchTrackById() {
        try {
            System.out.println("Introdueix l'ID del Track a cercar");
            Track track = Track.search(sc.nextInt());
            if (track != null) {
                System.out.println(track);
            } else System.out.println("No s'han trobat dades de Tracks amb aquest ID");
        } catch (InputMismatchException e) {
            System.out.println("Error: no és un caràcter vàlid");
        }
    }

    private static void createTrack() {
        System.out.println("Introdueix el nou nom del Track");
        String name = sc.nextLine();
        if (Track.create(name)) {
            System.out.println("S'ha creat un nou Track correctament.");
        } else System.out.println("Error: no s'ha pogut crear el nou Track.");
    }

    private static void updateTrack() {
        try {
            System.out.println("Introdueix l'ID del Track a cercar");
            Track track = Track.search(sc.nextInt());
            sc.nextLine();
            if (track != null) {
                System.out.println("Introdueix el nou nom del Track");
                String name = sc.nextLine();
                if (track.update(name)) {
                    System.out.println("El Track s'ha actualitzat correctament");
                } else System.out.println("Error: no s'ha pogut actualitzar el Track");
            } else System.out.println("No s'han trobat dades de Tracks amb aquest ID");
        } catch (InputMismatchException e) {
            System.out.println("Error: no és un caràcter vàlid");
        }
    }
}
