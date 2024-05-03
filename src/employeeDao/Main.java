package employeeDao;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        int option = -1;
        do {
            cli();
            try {
                option = sc.nextInt();
                handleOption(option);
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage() + "\nError: no és un caràcter vàlid.");
                sc.nextLine();
            }
        } while (option != 0);
    }
    private static void cli() {
        System.out.println("""
                ESCULL UNA OPCIÓ
                1. Cercar tots els empleats
                2. Cercar un empleat per ID
                3. Insertar nou empleat
                4. Actualitzar empleat
                5. Eliminar empleat""");
    }

    private static void handleOption(int option) {
        EmployeeDAO manager = new EmployeeSQLite();
        switch (option) {
            case 1:
                listAllEmployees(manager);
                break;
            case 2:
                searchOneEmployeeById(manager);
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 0:
                System.out.println("Es tanca el programa");
                System.exit(0);
                break;
            default:
                System.out.println("Error: no és una opció vàlida");
                break;
        }
    }
    private static void listAllEmployees(EmployeeDAO m) {
        List<Employee> employees = m.listAll();
        if (employees != null) {
            for (Employee e : employees) {
                System.out.println(e);
            }
        }
    }

    private static void searchOneEmployeeById(EmployeeDAO m) {
        try {
            System.out.println("Introdueix l'ID de l'empleat.");
            int id = sc.nextInt();
            Employee e = m.searchOneById(id);
            if (e != null) {
                System.out.println(e);
            } else System.out.println("No s'ha trobat un empleat amb aquest ID.");
        } catch (InputMismatchException e) {
            System.out.println("Error: no és un caràcter vàlid. L'ID és tipus numéric.");
        }
    }

    private static void insertNewEmployee(EmployeeDAO m) {
        try {
            int id = m.createNewId();
            System.out.println("Introdueix el cognom de l'empleat.");
            String name = sc.nextLine();
            m.create();
        } catch (Exception e) {

        }
    }
}
