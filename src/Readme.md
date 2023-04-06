# Введение в JDBC

## Введение в JDBC

На предыдущих уроках мы взаимодействовали с базой данных и с информацией, которая в ней хранится напрямую через командную строку.

Но все-таки, так как умение работать с базами данных нам требуется в процессе разработки, то и к информации у нас должен быть доступ напрямую из среды разработки.

Для этого у разработчиков есть инструмент — **JDBC.**

**JDBC** (Java DataBase Connectivity — соединение с базами данных на Java)  представляет собой стандартный API, который предназначен для взаимодействия Java-приложения с различными системами управления базами данных (СУБД).

**JDBC** позволяет устанавливать соединение с базой данных согласно специально описанному URL с помощью драйверов, которые могут загружаться во время работы программы динамически.

![jdbc-2-9726360.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/ff296ab2-5bea-4e00-9eac-530cccc9a451/jdbc-2-9726360.png)

# Maven

## Maven

В коммерческой разработке проекты зачастую очень масштабные и даже составные, которые могут содержать в себе несколько подпроектов. А также состоят из большого количества сторонних библиотек.

Собирать все это вместе руками в один большой корректно работающий проект будет очень сложно. Для облегчения этой задачи существуют различные инструменты для автоматизации сборки проектов.

Одним из наиболее популярных таких инструментов является **maven.**

maven - фреймворк, который отвечает за автоматизацию сборки проектов, а также позволяет легко управлять зависимостями для проекта.

maven создает локальный репозиторий в памяти машины и загружает туда все необходимые библиотеки для быстрого доступа к ним.

Основным инструментом самого мейвена является файл pom.xml

pom.xml(*Project Object Model*) - файл, который представляет собой декларативное описание проекта, то есть содержит конкретные спецификации, на основе которых и происходит сборка проекта, такие как зависимости и плагины.

Зачастую в качестве источника необходимых элементов используется ресурс [https://mvnrepository.com/](https://mvnrepository.com/)

Maven использует принцип *Maven-архетипов*.

Архетип — это инструмент шаблонов, каждый из которых определён паттерном или моделью. Благодаря применению того или иного архетипа мы получаем стандартную структуру каталогов(пакетов).

maven также определяет жизненный цикл проекта.

Жизненный цикл maven-проекта — это список поименованных фаз, определяющий порядок действий при его построении. Жизненный цикл Maven содержит три независимых порядка выполнения:

- clean — жизненный цикл для очистки проекта. Содержит следующие фазы:
    1. pre-clean
    2. clean
    3. post-clean
- default — основной жизненный цикл, содержащий следующие фазы:
    1. validate — выполняется проверка, является ли структура проекта полной и правильной.
    2. compile — компилируются исходные тексты.
    3. test — собранный код тестируется заранее подготовленным набором тестов.
    4. package — упаковка откомпилированных классов и прочих ресурсов. Например, в JAR-файл.
    5. install — установка программного обеспечения в локальный Maven-репозиторий, чтобы сделать его доступным для других проектов текущего пользователя.
    6. deploy — стабильная версия программного обеспечения распространяется на удаленный Maven-репозиторий, чтобы сделать его доступным для других пользователей.
- site — жизненный цикл генерации проектной документации. Состоит из фаз:
    1. pre-site
    2. site
    3. post-site
    4. site-deploy

# Создание проекта

Для того, чтобы начать работать с базой данных через среду разработки необходимо создать новый проект, выбрать в самом начале использование maven и подходящий архетип(Так как архетипов очень много разных, перед созданием проекта лучше загуглить, какой более подойдет для ваших целей).

В нашем случае можно выбрать архетип:

*org.apache.maven.archetypes:maven-archetype-webapp*

Также при создании проекта вам нужно будет заполнить 3 поля:

- groupId
- artifactId
- version

**groupId** указывает на разработчика ПО, там вы можете указать доменное имя своей компании.

**artifactId** — название проекта. Maven оперирует так называемыми артефактами. Это приложения, плагины, архетипы и другие проекты. И ваша разработка также будет считаться артефактом.

**version —** версия вашего проекта

Дальше начнется первоначальная сборка проекта по итогу которой у вас будет сформирован изначальный вариант файла pom.xml и некоторая структура папок.

![Снимок экрана 2023-02-02 в 23.53.11.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/b2f68814-1818-420f-a7b5-13f2bc6bc528/%D0%A1%D0%BD%D0%B8%D0%BC%D0%BE%D0%BA_%D1%8D%D0%BA%D1%80%D0%B0%D0%BD%D0%B0_2023-02-02_%D0%B2_23.53.11.png)

Так как нашей задачей является соединение с базой postgresql, необходимо добавить в наш пом-файл соответствующую зависимость.

Для этого переходим на сайт [https://mvnrepository.com/](https://mvnrepository.com/) и через поисковую строку ищем postgresql.

Переходим по варианту со ссылкой на org.postgresql.

![Снимок экрана 2023-02-03 в 00.00.38.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/a5a1c256-e135-4367-a444-1f5556570a02/%D0%A1%D0%BD%D0%B8%D0%BC%D0%BE%D0%BA_%D1%8D%D0%BA%D1%80%D0%B0%D0%BD%D0%B0_2023-02-03_%D0%B2_00.00.38.png)

Далее выбираем наиболее свежую и популярную версию.

![Снимок экрана 2023-02-03 в 00.04.09.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/cb7ec90d-8729-4383-b724-3287877baf5e/%D0%A1%D0%BD%D0%B8%D0%BC%D0%BE%D0%BA_%D1%8D%D0%BA%D1%80%D0%B0%D0%BD%D0%B0_2023-02-03_%D0%B2_00.04.09.png)

Далее нам нужно скопировать код который прописан на странице. Для этого достаточно один раз кликнуть на область с кодом.

![Снимок экрана 2023-02-03 в 00.07.36.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/1a8c4999-53bb-4fb3-ac16-4ee169c7ade9/%D0%A1%D0%BD%D0%B8%D0%BC%D0%BE%D0%BA_%D1%8D%D0%BA%D1%80%D0%B0%D0%BD%D0%B0_2023-02-03_%D0%B2_00.07.36.png)

Теперь вставляем этот код в наш пом-файл внутрь тега dependencies рядом с уже имеющейся зависимостью (dependency).

После чего кликаем на значок обновления пом-файла.

![Снимок экрана 2023-02-03 в 00.13.42.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/0f39925e-63bb-4ba2-a807-ed390f465e3b/%D0%A1%D0%BD%D0%B8%D0%BC%D0%BE%D0%BA_%D1%8D%D0%BA%D1%80%D0%B0%D0%BD%D0%B0_2023-02-03_%D0%B2_00.13.42.png)

Давайте настроим пакеты

Нужно раскрыть папку src, далее нажать правой кнопкой мыши на папку main → new → Directory.

Тут нужно создать папку с названием java(с маленькой буквы). Среда разработки должна вам сама предложить создать именно эту папку.

![Снимок экрана 2023-02-03 в 00.23.26.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/f47daa08-c2c0-4a13-8776-8ebd88cf56e3/%D0%A1%D0%BD%D0%B8%D0%BC%D0%BE%D0%BA_%D1%8D%D0%BA%D1%80%D0%B0%D0%BD%D0%B0_2023-02-03_%D0%B2_00.23.26.png)

После этого действия проект обновится и будет готов к работе.

# Соединение с базой и таблицей

## Соединение с базой и таблицей

Давайте в нашем пакете java создадим класс Application, в котором будем пока подключаться к базе и первый раз попробуем взаимодействовать с базой именно в этом классе.

Создаем в классе метод main и в нем создаем 3 строковые переменные:

- **user** - эта строка отвечает за логин при подключении к базе, по-умолчанию является “postgres”
- **password** - пароль для подключения к базе. Нужно прописать тот, который вы установили при установке инструментов базы и которым пользовались при работе с командной строкой
- **url** - адрес, по которому мы сможем найти нашу базу. Содержит указание сервера, порта и название самой базы, внутри которой мы будем работать: “jdbc:postgresql://localhost:5432/skypro”

Стоит сделать эти переменные финальными.

Далее в конструкции try with resources нам необходимо создать 2 ресурса:

1. `final Connection connection = DriverManager.*getConnection*(url, user, password)` - тут мы формируем само наше соединение с базой данных.
   Для этого нам необходимо создать объект класса `Connection` из стандартного пакета java.sql. Создам соединение с помощью статического метода *`getConnection` принадлежащего классу* `DriverManager` из того же пакета java.sql. Класс `DriverManager` как видно из названия отвечает за драйвера для подключения к конкретному типу базы данных. А в параметр метода мы как раз передаем созданные нами переменные.
2. `PreparedStatement statement = connection.prepareStatement("SELECT * FROM book WHERE book_id = (?)")` - в данном случае мы создаем объект класса `PreparedStatement`. Этот класс подготовить запрос к базе данных и выполнить его. В данном случае мы у созданного нами объекта `connection` вызываем метод `prepareStatement`, в параметр которого передаем запрос.
   Обратите внимание, что в запросе присутствует wildcard(?). В это место мы дальше сможем подставить необходимое для нас значение.

Далее выполняем метод `statement.setInt(1, 6)`. С помощью данного метода мы как раз подставляем необходимое значение. В параметр метода передаем порядковый номер конкретного wildcard в запросе (в данном случае он у нас первый и единственный), а также значение, которое хотим поставить на его место.

После этого наш запрос к базе готов к выполнению. Но поскольку мы хотим сделать выборку данных, то нам эти данные нужно куда-то положить.

Для этого в пакете java.sql есть интерфейс ResultSet

**ResultSet** представляет результирующий набор данных и обеспечивает приложению построчный доступ к результатам запросов. При обработке запроса **ResultSet** поддерживает указатель на текущую обрабатываемую строку.

Доступ к данным **ResultSet** обеспечивает посредством набора get-методов, которые организуют доступ к колонкам текущей строки. Метод ResultSet.next используется для перемещения к следующей строке ResultSet, делая ее текущей.

```java
final ResultSet resultSet = statement.executeQuery();
```

С помощью данной команды мы создаем объект, который ссылаем на интерфейс `ResultSet` и наполняем его данными, которые получаем из таблицы.
У объекта `statement` мы вызываем метод `executeQuery`, которым отправляем наш запрос в базу.

Далее с помощью цикла while проходим по всем данным, которые получили благодаря нашему запросу и используем их в наших целях.
В данном случае мы создадим переменные соответствующих типов и присвоим им значения из объекта `resultSet`.

Сделать это мы можем с помощью методов getInt и getString.

```java
String titleOfBook = "Title: " + resultSet.getString("title");
String authorOfBook = "Author_id: " + resultSet.getString("author_id");
int amountOfBook = resultSet.getInt(4);
```

Обратите внимание, что в параметр методов getInt и getString мы можем передавать как название колонки из базы данных, так и порядковый номер колонки.

А далее мы можем распоряжаться полученными данными так как мы пожелаем. Например, можем вывести в консоль.

Полный пример кода выглядит так:

```java
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

/*
Результатом в консоли будет:

Title: Uncle Vanya
Author_id: 4
Amount: 10
*/
```

# Шаблон DAO

## Шаблон DAO

В ходе разработке любого приложения выявляются определенные слои или блоки пакетов и классов, которые отвечают за взаимодействие различных отдельных модулей внутри всей системы.

Например, соединение с базой данных является одной из важнейших составляющих любого проекта. Всегда есть некоторый модуль, который будет ответственным только за взаимодействие с базой данных, а именно за передачу запросов и обработку полученных ответов.

Паттерн, который описывает этот процесс называется Data Access Object или просто DAO.

DAO описывает модуль взаимодействия с базой данных как прослойку между приложением и БД.

Для реализации данного шаблона первоначально используется интерфейс с описанием общих методов, которые будут использоваться при взаимодействии с базой данных.

После чего создается класс, в котором уже будет реализован каждый метод этого интерфейса. Как правило они имеют названия состоящие из имени сущности, с которой работаем и припиской DAO, в классе добавляется приписка Impl, например: интерфейс BookDAO и класс BookDAOImpl (такое название класса среда разработки предложит вам сама).

Реализация DAO на уровне класса подразумевает использование одного единственного коннекта для вызова всех методов унаследованного DAO класса. То есть нам требуется только единоразово в классе объявить поле класса Connection и добавить его в конструктор объекта класса реализации.

# Реализация CRUD-операций через шаблон DAO

## Реализация CRUD-операций через шаблон DAO

При реализации каждого метода, объявленного в интерфейсе, необходимо создать объект PreparedStatement путем вызову с поля Connection метода prepareStatement и, передав в параметр этого метода запрос, который необходимо передать базе для получения желаемого результата.

Так как объект PreparedStatement необходимо обязательно закрывать после его использования, оптимальным вариантом будет использование конструкции try with resources.

Далее мы реализуем методы в зависимости от цели обращения к базе.

Мы можем, например, передавать какие-то данные в запрос при операциях добавления, обновления или удаления данных или получать данные и их обрабатывать.

Давайте разберемся на примерах:

Созданы классы Book и Author

```java
import java.util.Objects;

public class Book {

    private int id;
    private String title;
    private Author author;
    private int amount;

    public Book() {
    }

    public Book(String title, Author author, int amount) {
        this.title = title;
        this.author = author;
        this.amount = amount;
    }

    public Book(int id, String title, Author author, int amount) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id && amount == book.amount && Objects.equals(title, book.title) && Objects.equals(author, book.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, amount);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author=" + author +
                ", amount=" + amount +
                '}';
    }
}
```

```java
import java.util.Objects;

public class Author {

    private int id;
    private String name;

    public Author(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return id == author.id && Objects.equals(name, author.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
```

Создан интерфейс и объявлены в нем методы.

```java
public interface BookDAO {
		
		// Добавление объекта
    void create(Book book);
		// Получение объекта по id
    Book readById(int id);
		// Получение всех объектов
    List<Book> readAll();
		// Изменение объекта по id
    void updateAmountById(int id, int amount);
		// Удаление объекта по id
    void deleteById(int id);

}
```

1. Метод добавления

```java
@Override
public void create(Book book) {

		// Формируем запрос к базе с помощью PreparedStatement 
    try(PreparedStatement statement = connection.prepareStatement(
				"INSERT INTO book (title, author_id, amount) VALUES ((?), (?), (?))")) {
				
				// Подставляем значение вместо wildcard
				// первым параметром указываем порядковый номер wildcard
				// вторым параметром передаем значение
        statement.setString(1, book.getTitle());
        statement.setInt(2, book.getAuthor().getId());
        statement.setInt(3, book.getAmount());

				// С помощью метода executeQuery отправляем запрос к базе
        statement.executeQuery();

    } catch (SQLException e) {
				e.printStackTrace();
    }
}
```

1. Метод получения объекта по id

```java
@Override
public Book readById(int id) {

    Book book = new Book();
				// Формируем запрос к базе с помощью PreparedStatement
        try (PreparedStatement statement = connection.prepareStatement(
"SELECT * FROM book INNER JOIN author ON book.author_id=author.author_id AND book_id=(?)")) {

						// Подставляем значение вместо wildcard
            statement.setInt(1, id);

						// Делаем запрос к базе и результат кладем в ResultSet
            ResultSet resultSet = statement.executeQuery();

						// Методом next проверяем есть ли следующий элемент в resultSet
						// и одновременно переходим к нему, если таковой есть
            while(resultSet.next()) {

								// С помощью методов getInt и getString получаем данные из resultSet
								// и присваиваем их полим объекта
                book.setId(Integer.parseInt(resultSet.getString("book_id")));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(new Author(resultSet.getInt("author_id"),
                        resultSet.getString("name_author")));
                book.setAmount(Integer.parseInt(resultSet.getString("amount")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }
```

1. Получение всех объектов из базы

```java
@Override
public List<Book> readAll() {

		// Создаем список, в который будем укладывать объекты
    List<Book> bookList = new ArrayList<>();

    try(PreparedStatement statement = connection.prepareStatement(
"SELECT * FROM book INNER JOIN author ON book.author_id=author.author_id")) {

        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {

            int id = Integer.parseInt(resultSet.getString("book_id"));
            String title = resultSet.getString("title");
            Author author = new Author(resultSet.getInt("author_id"),
                        resultSet.getString("name_author"));
            int amount = Integer.parseInt(resultSet.getString("amount"));
						

						// Создаем объекты на основе полученных данных 
						// и укладываем их в итоговый список
            bookList.add(new Book(id, title, author, amount));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return bookList;
}
```

1. Метод обновления данных в базе

```java
@Override
public void updateAmountById(int id, int amount) {

    try(PreparedStatement statement = connection.prepareStatement(
"UPDATE book SET amount=(?) WHERE book_id=(?)")) {

        statement.setInt(1, amount);
        statement.setInt(2, id);

        statement.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
```

1. Метод удаления данных из базы

```java
@Override
    public void deleteById(int id) {

        try(PreparedStatement statement = connection.prepareStatement(
"DELETE FROM book WHERE book_id=(?)")) {

            statement.setInt(1, id);
            statement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
```

Для того, чтобы применить эти методы, необходимо в классе Application создать объект класса реализации DAO, передав ему в конструкторе объект Connection.

И далее у этого объекта вызвать соответствующие методы.

Например, добавление нового объекта и последующее получение всех данных из базы будет выглядеть следующим образом:
public class Application {

    public static void main(String[] args) throws SQLException {

        final String user = "postgres";
        final String password = "5833118";
        final String url = "jdbc:postgresql://localhost:5432/skypro";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            
						// Создаем объект класса BookDAOImpl
						BookDAO bookDAO = new BookDAOImpl(connection);

            Author author = new Author(1, "L.N.Tolstoy");
            Book book1 = new Book("Anna Karenina", author, 6);
        
						// Вызываем метод добавления объекта
            bookDAO.create(book1);

						// Создаем список наполняя его объектами, которые получаем
						// путем вызова метода для получения всех элементов таблицы
            List<Book> list = new ArrayList<>(bookDAO.readAll());

						// Выведем список в консоль
            for (Book book : list) {
                System.out.println(book);
            }
        
        }
    }
}



    ```java
    <project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
      xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
      <modelVersion>4.0.0</modelVersion>
      <groupId>org.example</groupId>
      <artifactId>Course3_JDBC</artifactId>
      <packaging>war</packaging>
      <version>1.0-SNAPSHOT</version>
      <name>Course3_JDBC Maven Webapp</name>
      <url>http://maven.apache.org</url>
      <dependencies>
        <dependency>
          <groupId>junit</groupId>
          <artifactId>junit</artifactId>
          <version>3.8.1</version>
          <scope>test</scope>
        </dependency>
    
        <dependency>
          <groupId>org.postgresql</groupId>
          <artifactId>postgresql</artifactId>
          <version>42.5.1</version>
        </dependency>
    
      </dependencies>
      <build>
        <finalName>Course3_JDBC</finalName>
          <plugins>
              <plugin>
                  <groupId>org.apache.maven.plugins</groupId>
                  <artifactId>maven-compiler-plugin</artifactId>
                  <configuration>
                      <source>11</source>
                      <target>11</target>
                  </configuration>
              </plugin>
          </plugins>
      </build>
    </project>
    ```

### Задание 1

1. Создать maven проект с архетипом org.apache.maven.archetypes:maven-archetype-webapp.
2. Добавить зависимость postgresql с сайта [https://mvnrepository.com/](https://mvnrepository.com/) и плагин maven-compiler-plugin в pom.xml файл (пример файла pom.xml ниже).
3. Создать класс Application и настроить в нем подключение к созданной ранее базе данных skypro.
4. Получить и вывести в консоль полные данные об одном из работников (имя, фамилия, пол, город) данные получить по id.
- Файл pom.xml (для примера)
- 
### **Задание 2**

1. Создать классы Employee и City с полями, аналогично созданным таблицам.
2. Создать интерфейс EmployeeDAO.
3. Создать в интерфейсе методы:
    1. Создание(добавление) сущности Employee в таблицу
    2. Получение конкретного объекта Employee по id
    3. Получение списка всех объектов Employee из базы
    4. Изменение конкретного объекта Employee в базе по id
    5. Удаление конкретного объекта Employee из базы по id
4. Реализовать сервис EmployeeDAO и каждый его метод в отдельном классе.
5. Проверить корректность работы всех методов в классе Application.