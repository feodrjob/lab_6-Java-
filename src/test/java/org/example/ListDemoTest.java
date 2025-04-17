package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.example.CollectionsDemo.getPeoplesByMaxAge;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class ListDemoTest {
    private Set<Human> humans;
    private Human ivanov;
    private Human petrov;
    private Human sidorov;
    private Student student;

    @BeforeEach
    public void setUp() {
        ivanov = new Human("Ivanov", "Ivan", "Ivanovich", 20);
        petrov = new Human("Petrov", "Petr", "Petrovich", 25);
        sidorov = new Human("Sidorov", "Sidor", "Sidorovich", 25);
        student = new Student("Kuznetsov", "Alex", "Sergeevich", 30, "Computer Science");

        humans = new HashSet<>();
        humans.add(ivanov);
        humans.add(petrov);
        humans.add(sidorov);
    }

    @Test
    public void testBasicFunctionality() {
        ArrayList<Human> expected = new ArrayList<>(Arrays.asList(petrov, sidorov));
        ArrayList<Human> result = new ArrayList<>(getPeoplesByMaxAge(humans));

        // Сортируем для стабильного сравнения
        expected.sort(Human::compareTo);
        result.sort(Human::compareTo);

        assertEquals(expected, result);
    }

    @Test
    public void testSingleMaxAge() {
        humans.remove(sidorov);
        ArrayList<Human> expected = new ArrayList<>(Collections.singletonList(petrov));
        ArrayList<Human> result = new ArrayList<>(getPeoplesByMaxAge(humans));

        assertEquals(expected, result);
    }

    @Test
    public void testWithStudents() {
        humans.add(student);
        ArrayList<Human> expected = new ArrayList<>(Collections.singletonList(student));
        ArrayList<Human> result = new ArrayList<>(getPeoplesByMaxAge(humans));

        assertEquals(expected, result);
    }

    @Test
    public void testAllSameAge() {
        ivanov.setAge(30);
        petrov.setAge(30);
        sidorov.setAge(30);

        ArrayList<Human> expected = new ArrayList<>(humans);
        ArrayList<Human> result = new ArrayList<>(getPeoplesByMaxAge(humans));

        // Сортируем для стабильного сравнения
        expected.sort(Human::compareTo);
        result.sort(Human::compareTo);

        assertEquals(expected, result);
    }

    @Test
    public void testEmptyInput() {
        Set<Human> emptySet = Collections.emptySet();
        assertThrows(IllegalArgumentException.class, () -> getPeoplesByMaxAge(emptySet));
    }

    @Test
    public void testNullInput() {
        assertThrows(IllegalArgumentException.class, () -> getPeoplesByMaxAge(null));
    }
}