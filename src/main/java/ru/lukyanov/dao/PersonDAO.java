package ru.lukyanov.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.lukyanov.model.Person;

import java.util.List;

//SPRING JDBC TEMPLATE DB CONNECTION
@Component
public class PersonDAO {
    private static int IDCOUNT = 4;

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        String SQL = "SELECT * FROM persons.person";
        return jdbcTemplate.query(SQL, new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id) {
        String SQL = "SELECT * FROM persons.person WHERE id = ?";
        return jdbcTemplate.query(SQL, new Object[]{id}, new PersonMapper()).stream().findAny().orElse(null);
    }

    public void save(Person person) {
        String SQL = "INSERT INTO persons.person VALUES (?,?,?,?)";
        jdbcTemplate.update(SQL, ++IDCOUNT, person.getName(), person.getAge(), person.getEmail());
    }

    public void update(int id, Person person) {
        String SQL = "UPDATE persons.person SET name = ?, age = ?, email = ? WHERE id = ?";
        jdbcTemplate.update(SQL, person.getName(), person.getAge(), person.getEmail(), id);
    }

    public void delete(int id) {
        String SQL = "DELETE FROM persons.person WHERE id = ?";
        jdbcTemplate.update(SQL, id);

    }
}