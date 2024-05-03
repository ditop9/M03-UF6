package employeeDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmployeeSQLite implements EmployeeDAO {
    private final static Connection con = SQLConnection.getConnection();

    private static Employee extractQueryInfo(ResultSet rs) {
        try {
            Employee reported = new EmployeeSQLite().searchOneById(rs.getInt(7));
            return new Employee(rs.getInt(1), rs.getString(2), rs.getString(3),
                    rs.getString(4), reported, rs.getString(5), rs.getString(6),
                    rs.getString(7), rs.getString(8), rs.getString(9),
                    rs.getString(10), rs.getString(11), rs.getInt(12),
                    rs.getString(13), rs.getString(14));
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public int createNewId() {
        try (Statement statement = con.createStatement()) {
            ResultSet result = statement.executeQuery("SELECT MAX(EmployeeId) FROM Employee");
            return result.getInt(1) + 1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }

    @Override
    public List<Employee> listAll() {
        String query = "SELECT * FROM Employee";
        try (PreparedStatement statement = con.prepareStatement(query)) {
            List<Employee> employees = new ArrayList<>();
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                employees.add(extractQueryInfo(result));
            }
            result.close();
            return employees;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Employee searchOneById(int id) {
        String query = "SELECT * FROM Employee WHERE EmployeeId = ?";
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            return EmployeeSQLite.extractQueryInfo(result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public boolean create(Employee employee) {
        String query = "INSERT INTO Employee (EmployeeId, Email, FirstName, LastName) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = con.prepareStatement(query)) {
            con.setAutoCommit(false);
            statement.setInt(1, employee.getId());
            statement.setString(2, employee.getEmail());
            statement.setString(3, employee.getFirstName());
            statement.setString(4, employee.getLastName());
            statement.executeUpdate();
            con.commit();
            return statement.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(Employee employee) {
        String query = "DELETE FROM Employee WHERE EmployeeId = ?";
        try (PreparedStatement statement = con.prepareStatement(query)) {
            con.setAutoCommit(false);
            statement.setInt(1, employee.getId());
            statement.executeUpdate();
            con.commit();
            return statement.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
