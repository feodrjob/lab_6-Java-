package org.example;

import org.junit.jupiter.api.Test;

import static org.example.CollectionsDemo.getPeoplesByMaxAge;
import static org.junit.jupiter.api.Assertions.*;
import java.util.HashSet;
import java.util.ArrayList;

class ListDemoTest {

    @Test
    void testEmptyInput() {
        assertThrows(IllegalArgumentException.class, () -> {
            getPeoplesByMaxAge(new HashSet<>());
        });
    }

    @Test
    void testNullInput() {
        assertThrows(IllegalArgumentException.class, () -> {
            getPeoplesByMaxAge(null);
        });
    }

    @Test
    void testSingleHuman() {
        HashSet<Human> humans = new HashSet<>();
        humans.add(new Human("Ivanov", "Ivan", "Ivanovich", 30));

        ArrayList<?extends Human> result = getPeoplesByMaxAge(humans);

        assertEquals(1, result.size());
        assertEquals(30, result.get(0).getAge());
    }

    @Test
    void testSingleStudent() {
        HashSet<Human> humans = new HashSet<>();
        humans.add(new Student("Petrov", "Petr", "Petrovich", 25, "Computer Science"));

        ArrayList<?extends Human> result = getPeoplesByMaxAge(humans);

        assertEquals(1, result.size());
        assertEquals(25, result.get(0).getAge());
        assertTrue(result.get(0) instanceof Student);
    }

    @Test
    void testMultipleHumansSameAge() {
        HashSet<Human> humans = new HashSet<>();
        humans.add(new Human("Ivanov", "Ivan", "Ivanovich", 30));
        humans.add(new Human("Petrov", "Petr", "Petrovich", 30));
        humans.add(new Human("Sidorov", "Sidor", "Sidorovich", 30));

        ArrayList<?extends Human> result = getPeoplesByMaxAge(humans);

        assertEquals(3, result.size());
        assertEquals(30, result.get(0).getAge());
        assertEquals(30, result.get(1).getAge());
        assertEquals(30, result.get(2).getAge());
    }

    @Test
    void testMixedHumansAndStudents() {
        HashSet<Human> humans = new HashSet<>();
        humans.add(new Human("Ivanov", "Ivan", "Ivanovich", 30));
        humans.add(new Student("Petrov", "Petr", "Petrovich", 35, "Physics"));
        humans.add(new Human("Sidorov", "Sidor", "Sidorovich", 25));
        humans.add(new Student("Smirnov", "Alex", "Alexeevich", 35, "Mathematics"));

        ArrayList<?extends Human> result = getPeoplesByMaxAge(humans);

        assertEquals(2, result.size());
        assertEquals(35, result.get(0).getAge());
        assertEquals(35, result.get(1).getAge());

        // Проверяем, что в результате есть оба человека с максимальным возрастом
        boolean hasPetrov = result.stream().anyMatch(h ->
                h.getSurname().equals("Petrov") && h.getName().equals("Petr"));
        boolean hasSmirnov = result.stream().anyMatch(h ->
                h.getSurname().equals("Smirnov") && h.getName().equals("Alex"));

        assertTrue(hasPetrov);
        assertTrue(hasSmirnov);
    }

    @Test
    void testDifferentAges() {
        HashSet<Human> humans = new HashSet<>();
        humans.add(new Human("Ivanov", "Ivan", "Ivanovich", 20));
        humans.add(new Human("Petrov", "Petr", "Petrovich", 25));
        humans.add(new Human("Sidorov", "Sidor", "Sidorovich", 30));
        humans.add(new Human("Smirnov", "Alex", "Alexeevich", 25));

        ArrayList<?extends Human> result = getPeoplesByMaxAge(humans);

        assertEquals(1, result.size());
        assertEquals(30, result.get(0).getAge());
        assertEquals("Sidorov", result.get(0).getSurname());
    }
}
