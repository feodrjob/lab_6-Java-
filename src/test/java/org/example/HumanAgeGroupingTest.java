package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.example.CollectionsDemo.getMapAge;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

public class HumanAgeGroupingTest {

    private HashSet<Human> humans;
    private HashSet<Human> emptySet;
    private HashSet<Human> singlePersonSet;
    private HashSet<Human> sameAgeGroup;

    @BeforeEach
    public void setUp() {
        // Основной тестовый набор
        humans = new HashSet<>();
        humans.add(new Human("Smith", "John", "M", 25));
        humans.add(new Human("Johnson", "Alice", "P", 30));
        humans.add(new Student("Williams", "Bob", "Q", 25, "CS"));
        humans.add(new Human("Brown", "Emma", "R", 30));

        // Пустой набор
        emptySet = new HashSet<>();

        // Один человек
        singlePersonSet = new HashSet<>();
        singlePersonSet.add(new Human("Doe", "Jane", "S", 40));

        // Все одного возраста
        sameAgeGroup = new HashSet<>();
        sameAgeGroup.add(new Human("Taylor", "Roy", "T", 20));
        sameAgeGroup.add(new Student("Anderson", "Tom", "U", 20, "Math"));
    }

    @Test
    public void testGetMapAge_NormalCase() {
        Map<Integer, ArrayList<Human>> result = getMapAge(humans);

        assertEquals(2, result.size()); // Два уникальных возраста

        // Проверяем группу 25 лет
        ArrayList<Human> age25 = result.get(25);
        assertEquals(2, age25.size());
        assertTrue(age25.stream().anyMatch(h -> h.getSurname().equals("Smith")));
        assertTrue(age25.stream().anyMatch(h -> h.getSurname().equals("Williams")));

        // Проверяем группу 30 лет
        ArrayList<Human> age30 = result.get(30);
        assertEquals(2, age30.size());
        assertTrue(age30.stream().anyMatch(h -> h.getSurname().equals("Johnson")));
        assertTrue(age30.stream().anyMatch(h -> h.getSurname().equals("Brown")));
    }

    @Test
    public void testGetMapAge_SinglePerson() {
        Map<Integer, ArrayList<Human>> result = getMapAge(singlePersonSet);

        assertEquals(1, result.size());
        assertEquals(1, result.get(40).size());
        assertEquals("Doe", result.get(40).get(0).getSurname());
    }

    @Test
    public void testGetMapAge_SameAgeGroup() {
        Map<Integer, ArrayList<Human>> result = getMapAge(sameAgeGroup);

        assertEquals(1, result.size());
        assertEquals(2, result.get(20).size());
    }

    @Test
    public void testGetMapAge_EmptyInput() {
        assertThrows(IllegalArgumentException.class, () -> {
            getMapAge(emptySet);
        });
    }

    @Test
    public void testGetMapAge_WithStudents() {
        HashSet<Human> mixedSet = new HashSet<>();
        mixedSet.add(new Student("Wilson", "Eva", "V", 22, "Physics"));
        mixedSet.add(new Human("Davis", "Liam", "W", 22));

        Map<Integer, ArrayList<Human>> result = getMapAge(mixedSet);

        assertEquals(1, result.size());
        assertEquals(2, result.get(22).size());
        assertTrue(result.get(22).get(0) instanceof Student || result.get(22).get(1) instanceof Student);
    }

    @Test
    public void testGetMapAge_AgeZero() {
        HashSet<Human> zeroAgeSet = new HashSet<>();
        zeroAgeSet.add(new Human("Miller", "Grace", "X", 0));

        Map<Integer, ArrayList<Human>> result =getMapAge(zeroAgeSet);

        assertEquals(1, result.size());
        assertEquals(0, result.get(0).get(0).getAge());
    }

    @Test
    public void testGetMapAge_NoDuplicateEntries() {
        // Проверяем что в группах нет дубликатов
        Human person = new Human("Clark", "Bruce", "Y", 35);
        HashSet<Human> setWithDuplicates = new HashSet<>();
        setWithDuplicates.add(person);
        setWithDuplicates.add(person); // HashSet не добавит дубликат

        Map<Integer, ArrayList<Human>> result = getMapAge(setWithDuplicates);

        assertEquals(1, result.size());
        assertEquals(1, result.get(35).size());
    }
}