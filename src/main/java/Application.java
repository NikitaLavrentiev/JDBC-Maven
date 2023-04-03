import dao.EmployeeDAO;
import dao.Impl.EmployeeDAOImpl;
import models.City;
import models.Employee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Application {

    public static void main(String[] args) {
        final String user = "postgres";
        final String pass = "postgres";
        final String url = "jdbc:postgresql://localhost:5432/skypro";


        try (Connection connection = DriverManager.getConnection(url, user, pass)) {

            EmployeeDAO employeeDAO = new EmployeeDAOImpl(connection);

            City city = new City(2);
            Employee man = new Employee("1", "2", "male", 32, city);
            //добавление в БД


            List<Employee> list = new ArrayList<>(employeeDAO.readAll());

            for (Employee employee : list) {
                System.out.println(employee);
            } // список печатается


//           employeeDAO.deleteEntityById(11); // удаление работает

            employeeDAO.updateEntityById(2, 1); // изменение города работает.

            System.out.println("employeeDAO.readById(2) = " + employeeDAO.readById(2)); // получение по id работает


        } catch (SQLException e) {
            e.printStackTrace();
        }


/*        try (Connection connection = DriverManager.getConnection(url, user, pass)) {
            Statement statement = connection.createStatement();
            int id = 1; // id работника, которого нужно получить
            // Запрос для получения данных из таблицы employee и city
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