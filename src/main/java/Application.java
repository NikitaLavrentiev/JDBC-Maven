public class Application {

    public static void main(String[] args) {


        //================================================================================

//        Employee man = new Employee("11", "11", "female", 23, new City(2));

//        employeeDAO.create(man);

//        System.out.println(employeeDAO.readById(24));

//          List<Employee> employees = employeeDAO.readAll();
//        for (Employee employee : employees) {
//            System.out.println(employee);
//        }

//        EmployeeDAO.updateEmployeeEntity(new Employee(17,"11", "11", "female",35,3));

        //  employeeDAO.deleteEmployeeEntity(new Employee(2));
        /*try (Connection connection = DriverManager.getConnection(url, user, pass)) {
            EmployeeDAO employeeDAO = new EmployeeDAOImpl(connection);
            City city = new City(2);
            Employee man = new Employee("12", "12", "male", 23, city);
            //добавление в БД
//            employeeDAO.create(man); - добавлено
            List<Employee> list = new ArrayList<>(employeeDAO.readAll());
            for (Employee employee : list) {
                System.out.println(employee);
            } // список печется
//            employeeDAO.deleteEntityById(22); // удаление работает
            employeeDAO.updateEntityById(2, 1); // изменение города работает.
            System.out.println("employeeDAO.readById(2) = " + employeeDAO.readById(2)); // получение по id работает
        } catch (SQLException e) {
            e.printStackTrace();
        }*/


        /*        try (Connection connection = DriverManager.getConnection(url, user, pass)) {
            Statement statement = connection.createStatement();
            int id = 1; // id работника, которого нужно получить
            // Запрос для получения данных из таблицы employee и city
            String sql = "SELECT e.first_name, e.last_name, e.age, e.gender, c.city_name " +
                    "FROM employee s " +
                    "JOIN city c ON e.city_id = c.city_id " +
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