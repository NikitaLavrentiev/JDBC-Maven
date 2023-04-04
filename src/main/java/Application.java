import dao.EmployeeDAO;
import dao.Impl.EmployeeDAOImpl;
import models.Employee;

import java.util.List;

public class Application {

    public static void main(String[] args) {

        EmployeeDAO employeeDAO = new EmployeeDAOImpl();

        Employee man = new Employee("22", "22", "female", 23, 2);

        employeeDAO.create(man);

        System.out.println(employeeDAO.readById(24));
          List<Employee> employees = employeeDAO.readAll();
        for (Employee employee : employees) {
            System.out.println(employee);
        }

//        EmployeeDAO.updateEmployeeEntity(new Stuff(11,"22", "33", "female",35,3));

        employeeDAO.deleteEmployeeEntity(new Employee(2));
        /*try (Connection connection = DriverManager.getConnection(url, user, pass)) {
            EmployeeDAO employeeDAO = new EmployeeDAOImpl(connection);
            City city = new City(2);
            Employee man = new Employee("11", "11", "male", 23, city);
            //добавление в БД
//            employeeDAO.create(man); - добавлено
            List<Employee> list = new ArrayList<>(employeeDAO.readAll());
            for (Employee employee : list) {
                System.out.println(employee);
            } // список печатается
//            employeeDAO.deleteEntityById(22); // удаление работает
            employeeDAO.updateEntityById(2, 1); // изменение города работает.
            System.out.println("employeeDAO.readById(2) = " + employeeDAO.readById(2)); // получение по id работает
        } catch (SQLException e) {
            e.printStackTrace();
        }*/


        /*        try (Connection connection = DriverManager.getConnection(url, user, pass)) {
            Statement statement = connection.createStatement();
            int id = 1; // id работника, которого нужно получить
            // Запрос для получения данных из таблицы stuff и city
            String sql = "SELECT e.first_name, e.last_name, e.age, e.gender, e.city_name " +
                    "FROM employee e " +
                    "JOIN city c ON e.city_id = e.city_id " +
                    "WHERE e.id = " + id;
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                System.out.println("Name: " + resultSet.getString("first_name"));
                System.out.println("Surname: " + resultSet.getString("last_name"));
                System.out.println("Gender: " + resultSet.getString(4));
                System.out.println("Age: " + resultSet.getInt("age"));
                System.out.println("City: " + resultSet.getString("city_name"));
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
    }
}

