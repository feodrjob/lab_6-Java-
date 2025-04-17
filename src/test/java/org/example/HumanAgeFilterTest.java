package org.example;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.example.CollectionsDemo.getMapAge;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class HumanAgeFilterTest {
    private Set<Human> humans;
    private Human person20_1;
    private Human person20_2;
    private Human person25;
    private Student student20;

    @BeforeEach
    public void setUp() {
        // Инициализация тестовых данных
        person20_1 = new Human("Ivanov", "Ivan", "Ivanovich", 20);
        person20_2 = new Human("Petrov", "Petr", "Petrovich", 20);
        person25 = new Human("Sidorov", "Sidor", "Sidorovich", 25);
        student20 = new Student("Abramov", "Alex", "Sergeevich", 20, "CS");

        humans = new HashSet<>();
        humans.add(person20_1);
        humans.add(person20_2);
        humans.add(person25);
        humans.add(student20);
    }

    @Test
    public void testBasicAgeGrouping() {
        Map<Integer, ArrayList<Human>> result = getMapAge(humans);

        // Проверка возраста 20
        ArrayList<Human> expected20 = new ArrayList<>(Arrays.asList(
                person20_1, person20_2, student20
        ));
        ArrayList<Human> actual20 = result.get(20);
        assertEquals(expected20.size(), actual20.size());
        assertTrue(actual20.containsAll(expected20));

        // Проверка возраста 25
        ArrayList<Human> expected25 = new ArrayList<>(Collections.singletonList(person25));
        assertEquals(expected25, result.get(25));
    }

    @Test
    public void testSingleAgeGroup() {
        humans.clear();
        humans.add(person20_1);
        humans.add(person20_2);

        Map<Integer, ArrayList<Human>> result = getMapAge(humans);

        assertEquals(1, result.size());
        assertEquals(2, result.get(20).size());
    }

    @Test
    public void testAllUniqueAges() {
        humans.clear();
        humans.add(new Human("A", "B", "C", 18));
        humans.add(new Human("D", "E", "F", 19));
        humans.add(new Human("G", "H", "I", 20));

        Map<Integer, ArrayList<Human>> result = getMapAge(humans);

        assertEquals(3, result.size());
        assertEquals(1, result.get(18).size());
        assertEquals(1, result.get(19).size());
        assertEquals(1, result.get(20).size());
    }

    @Test
    public void testWithStudents() {
        Map<Integer, ArrayList<Human>> result = getMapAge(humans);

        ArrayList<Human> age20 = result.get(20);
        assertTrue(age20.contains(student20));
    }

    @Test
    public void testEmptyInput() {
        humans.clear();
        assertThrows(IllegalArgumentException.class, () -> getMapAge(humans));
    }

    @Test
    public void testAgeZero() {
        humans.add(new Human("Smith", "John", "Doe", 0));

        Map<Integer, ArrayList<Human>> result = getMapAge(humans);
        assertEquals(1, result.get(0).size());
    }

    @Test
    public void testDuplicateHumans() {
        humans.add(person20_1); // Добавляем дубликат

        Map<Integer, ArrayList<Human>> result = getMapAge(humans);
        assertEquals(3, result.get(20).size()); // Должны быть все три объекта
    }
}