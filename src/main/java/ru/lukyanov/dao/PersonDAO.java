package ru.lukyanov.dao;

import org.springframework.stereotype.Component;
import ru.lukyanov.model.Person;

import java.util.ArrayList;
import java.util.List;


@Component
public class PersonDAO {
    private static int PEOPLE_COUNT;
    private List<Person> people;

    {
        people = new ArrayList<>();

        people.add(new Person(++PEOPLE_COUNT, "Tom", 24, "24mail@mail.com"));
        people.add(new Person(++PEOPLE_COUNT, "Bob", 25, "25mail@mail.com"));
        people.add(new Person(++PEOPLE_COUNT, "Mike", 26, "26mail@mail.com"));
        people.add(new Person(++PEOPLE_COUNT, "Katie", 27, "27mail@mail.com"));
    }

    public List<Person> index() {
        return people;
    }

    public void save(Person person) {
        person.setId(++PEOPLE_COUNT);
        people.add(person);
    }

    public void update(int id, Person person) {
        Person toUpdate = show(id);
        toUpdate.setName(person.getName());
        toUpdate.setAge(person.getAge());
        toUpdate.setEmail(person.getEmail());
    }

    public void delete(int id) {
        people.removeIf(person -> person.getId() == id);
    }

    public Person show(int id) {
        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }
}