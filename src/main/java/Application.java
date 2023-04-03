import java.sql.*;

public class Application {

    public static void main(String[] args) throws SQLException {

        // Создаем переменные с данными для подключения к базе
        final String user = "postgres";
        final String password = "5833118";
        final String url = "jdbc:postgresql://localhost:5432/skypro";

        // Создаем соединение с базой с помощью Connection
        // Формируем запрос к базе с помощью PreparedStatement
        try (final Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM book WHERE book_id = (?)")) {

            // Подставляем значение вместо wildcard
            statement.setInt(1, 6);

            // Делаем запрос к базе и результат кладем в ResultSet
            final ResultSet resultSet = statement.executeQuery();

            // Методом next проверяем есть ли следующий элемент в resultSet
            // и одновременно переходим к нему, если таковой есть
            while (resultSet.next()) {

                // С помощью методов getInt и getString получаем данные из resultSet
                String titleOfBook = "Title: " + resultSet.getString("title");
                String authorOfBook = "Author_id: " + resultSet.getString("author_id");
                int amountOfBook = resultSet.getInt(4);

                // Выводим данные в консоль
                System.out.println(titleOfBook);
                System.out.println(authorOfBook);
                System.out.println("Amount: " + amountOfBook);

            }
        }
    }
}
