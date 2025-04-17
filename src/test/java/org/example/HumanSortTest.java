package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.example.CollectionsDemo.getSortedSet;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class HumanSortTest {
    private Set<Human> humans;
    private Human ivanov;
    private Human petrov;
    private Human sidorov;
    private Student student;

    @BeforeEach
    public void setUp() {
        // Инициализация тестовых данных
        ivanov = new Human("Ivanov", "Ivan", "Ivanovich", 20);
        petrov = new Human("Petrov", "Petr", "Petrovich", 25);
        sidorov = new Human("Sidorov", "Sidor", "Sidorovich", 25);
        student = new Student("Abramov", "Alex", "Sergeevich", 30, "Computer Science");

        humans = new HashSet<>();
        humans.add(ivanov);
        humans.add(petrov);
        humans.add(sidorov);
    }

    @Test
    public void testBasicSorting() {
        // Ожидаемый порядок: Abramov, Ivanov, Petrov, Sidorov
        humans.add(student); // Добавляем студента с фамилией "Abramov" (должен быть первым)

        ArrayList<Human> expected = new ArrayList<>(Arrays.asList(
                student,  // Abramov
                ivanov,   // Ivanov
                petrov,   // Petrov
                sidorov   // Sidorov
        ));

        ArrayList<Human> result = getSortedSet(humans);

        assertEquals(expected, result, "Список должен быть отсортирован по ФИО");
    }

    @Test
    public void testWithEqualNames() {
        // Добавляем человека с такой же фамилией, но именем, которое должно быть раньше по алфавиту
        Human ivanov2 = new Human("Ivanov", "Alex", "Ivanovich", 22);
        humans.add(ivanov2);

        ArrayList<Human> expected = new ArrayList<>(Arrays.asList(
                ivanov2,  // Ivanov Alex (A раньше I)
                ivanov,   // Ivanov Ivan
                petrov,
                sidorov
        ));

        ArrayList<Human> result = getSortedSet(humans);

        assertEquals(expected, result, "При одинаковых фамилиях сортировка по имени");
    }

    @Test
    public void testWithStudentsOnly() {
        Set<Human> students = new HashSet<>();
        Student s1 = new Student("Petrov", "A", "B", 20, "Math");
        Student s2 = new Student("Ivanov", "B", "C", 21, "Physics");
        students.add(s1);
        students.add(s2);

        ArrayList<Human> expected = new ArrayList<>(Arrays.asList(s2, s1));
        ArrayList<Human> result = getSortedSet(students);

        assertEquals(expected, result, "Должен работать с наследниками Human");
    }

    @Test
    public void testEmptyInput() {
        Set<Human> emptySet = Collections.emptySet();
        assertThrows(IllegalArgumentException.class, () -> getSortedSet(emptySet));
    }

    @Test
    public void testNullInput() {
        assertThrows(IllegalArgumentException.class, () -> getSortedSet(null));
    }
}