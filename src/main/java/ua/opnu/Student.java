package ua.opnu; // Переконайтеся, що пакет відповідає вашій структурі

import java.util.Arrays;

class Student {
    private String name;
    private String group;
    private int[] marks; // Для зберігання оцінок

    // 1. Створіть конструктор для ініціалізації всіх полів
    public Student(String name, String group, int[] marks) {
        this.name = name;
        this.group = group;
        this.marks = marks;
    }

    // 2. Додайте гетери (натисніть Alt+Insert -> Getter)
    public String getName() { return name; }
    public String getGroup() { return group; }
    public int[] getMarks() { return marks; }

    // 3. Перевизначте метод toString() для зручного виводу
    @Override
    public String toString() {
        return name + " (" + group + "), Marks: " + Arrays.toString(marks);
    }
}
