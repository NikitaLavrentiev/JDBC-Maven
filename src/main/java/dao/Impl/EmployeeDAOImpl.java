package dao.Impl;

import dao.EmployeeDAO;
import models.City;
import models.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {

    private final Connection connection;

    public EmployeeDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Employee employee) {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO employe (first_name, last_name, gender, age, city_id)" +
                        "VALUES ((?), (?), (?), (?), (?))"
        )) {

            statement.setString(1, employee.getFirst_name());
            statement.setString(2, employee.getLast_name());
            statement.setString(3, employee.getGender());
            statement.setInt(4, employee.getAge());
            statement.setInt(5, employee.getCity().getCity_id());

            statement.executeQuery();
            System.out.println("Объект добавлен в БД.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Employee readById(int id) {
        Employee employee = new Employee();
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM employee INNER JOIN city ON employee.city_id = city.city_id AND id = (?)"
        )) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                employee.setId(resultSet.getInt("id"));
                employee.setFirst_name(resultSet.getString(2));
                employee.setLast_name(resultSet.getString(3));
                employee.setGender(resultSet.getString(4));
                employee.setAge(resultSet.getInt(5));
                employee.setCity(new City(resultSet.getInt("city_id"), resultSet.getString("city_name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    @Override
    public List<Employee> readAll() {
        List<Employee> employeeList = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM employee INNER JOIN city ON employee.city_id = city.city_id"
        )) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String first_name = resultSet.getString(2);
                String last_name = resultSet.getString(3);
                String gender = resultSet.getString(4);
                int age = resultSet.getInt(5);
                City city = new City(resultSet.getInt("city_id"), resultSet.getString("city_name"));

                employeeList.add(new Employee(id, first_name, last_name, gender, age, city));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employeeList;
    }

    @Override
    public void updateEntityById(int id, int cityId) {

        try (PreparedStatement statement = connection.prepareStatement(
                "UPDATE employee SET city_id = (?) WHERE id = (?)"
        )) {

            statement.setInt(1, cityId);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteEntityById(int id) {

        try (PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM employee WHERE id = (?)"
        )) {
            statement.setInt(1, id);
            statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}