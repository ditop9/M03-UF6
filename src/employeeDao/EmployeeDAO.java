package employeeDao;

import java.util.List;

public interface EmployeeDAO {
    int createNewId();
    List<Employee> listAll();
    Employee searchOneById(int id);
    boolean create(Employee employee);
    boolean delete(Employee employee);
}
