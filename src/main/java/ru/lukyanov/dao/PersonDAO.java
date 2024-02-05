package ru.lukyanov.dao;

import org.springframework.stereotype.Component;
import ru.lukyanov.model.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Component
public class PersonDAO {
    private static int IDCOUNT = 4;

    private final static String URL = "jdbc:postgresql://localhost:5433/persons";
    private final static String USERNAME = "postgres";
    private final static String PASSWORD = "postgres";
    private static final Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public List<Person> index() {
        List<Person> people = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM persons.person";
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                Person person = new Person();
                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setAge(resultSet.getInt("age"));
                person.setEmail(resultSet.getString("email"));
                people.add(person);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return people;
    }

    public void save(Person person) {
        try {
            String SQL = "INSERT INTO persons.person VALUES (?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(SQL);
            statement.setInt(1,++IDCOUNT);
            statement.setString(2, person.getName());
            statement.setInt(3, person.getAge());
            statement.setString(4, person.getEmail());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(int id, Person person) {
        String SQL = "UPDATE persons.person SET name = ?, age = ?, email = ? WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(SQL);
            statement.setString(1, person.getName());
            statement.setInt(2, person.getAge());
            statement.setString(3, person.getEmail());
            statement.setInt(4, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(int id) {
        String SQL = "DELETE FROM persons.person WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(SQL);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Person show(int id) {
        Person person = null;
        try {
            String SQL = "SELECT * FROM persons.person WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(SQL);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                person = new Person();
                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setAge(resultSet.getInt("age"));
                person.setEmail(resultSet.getString("email"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return person;
    }
}