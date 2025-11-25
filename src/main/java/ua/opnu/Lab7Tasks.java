package ua.opnu; // Переконайтеся, що пакет відповідає вашій структурі

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Lab7Tasks {

    // =================================================================
    // ЗАВДАННЯ 1: Предикат для простого числа
    // =================================================================
    public static final Predicate<Integer> isPrime = n -> {
        if (n <= 1) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    };

    // =================================================================
    // ЗАВДАННЯ 2: Метод фільтрації студентів
    // =================================================================
    public static List<Student> filterStudents(Student[] input, Predicate<Student> p) {
        List<Student> result = new ArrayList<>();
        for (Student s : input) {
            if (p.test(s)) {
                result.add(s);
            }
        }
        return result;
    }

    // =================================================================
    // ЗАВДАННЯ 3: Фільтрація за двома умовами
    // =================================================================
    public static int[] filterTwoConditions(int[] input, Predicate<Integer> p1, Predicate<Integer> p2) {
        // Комбінуємо два предикати за допомогою вбудованого методу and()
        Predicate<Integer> combinedPredicate = p1.and(p2);

        int[] result = new int[input.length];
        int counter = 0;
        for (int i : input) {
            if (combinedPredicate.test(i)) {
                result[counter] = i;
                counter++;
            }
        }
        return Arrays.copyOfRange(result, 0, counter);
    }

    // =================================================================
    // ЗАВДАННЯ 4: Метод forEach для Consumer
    // =================================================================
    public static void forEachStudent(Student[] input, Consumer<Student> action) {
        for (Student s : input) {
            action.accept(s);
        }
    }

    // =================================================================
    // ЗАВДАННЯ 5: Метод processIf
    // =================================================================
    public static void processIf(int[] input, Predicate<Integer> p, Consumer<Integer> c) {
        for (int i : input) {
            if (p.test(i)) {
                c.accept(i);
            }
        }
    }

    // =================================================================
    // ЗАВДАННЯ 6: Метод обробки масиву (Function)
    // =================================================================
    public static int[] processArray(int[] input, Function<Integer, Integer> func) {
        int[] result = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            result[i] = func.apply(input[i]);
        }
        return result;
    }

    // =================================================================
    // ЗАВДАННЯ 7: Метод stringify()
    // =================================================================
    public static String[] stringify(int[] input, Function<Integer, String> func) {
        String[] result = new String[input.length];
        for (int i = 0; i < input.length; i++) {
            result[i] = func.apply(input[i]);
        }
        return result;
    }


    // =================================================================
    // МЕТОД MAIN (Точка входу та перевірка)
    // =================================================================
    public static void main(String[] args) {
        System.out.println("=========================================");
        System.out.println("           ЛАБОРАТОРНА РОБОТА 7          ");
        System.out.println("=========================================");

        // --- Перевірка Завдання 1 ---
        System.out.println("\n--- ЗАВДАННЯ 1: Predicate isPrime ---");
        System.out.println("Число 7 просте? " + isPrime.test(7));
        System.out.println("Число 10 просте? " + isPrime.test(10));
        System.out.println("-----------------------------------------\n");


        // --- Перевірка Завдання 2 ---
        Student s1 = new Student("Іванов", "КН-1", new int[]{85, 92, 78});
        Student s2 = new Student("Петров", "КН-1", new int[]{55, 60, 95}); // Заборгованість (55 < 60)
        Student s3 = new Student("Сидоров", "КН-2", new int[]{90, 88, 80});
        Student[] allStudents = {s1, s2, s3};

        // Предикат: студент не має заборгованостей (всі оцінки >= 60)
        Predicate<Student> noDebt = student -> {
            for (int mark : student.getMarks()) {
                if (mark < 60) {
                    return false; // Знайдена заборгованість
                }
            }
            return true; // Немає заборгованостей
        };

        List<Student> filteredStudents = filterStudents(allStudents, noDebt);

        System.out.println("--- ЗАВДАННЯ 2: Фільтрація студентів (без заборгованостей) ---");
        System.out.println("Всі студенти: " + Arrays.asList(allStudents));
        System.out.println("Результат: " + filteredStudents);
        System.out.println("-----------------------------------------\n");


        // --- Перевірка Завдання 3 ---
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        Predicate<Integer> isEven = n -> n % 2 == 0;
        Predicate<Integer> isGreaterThan5 = n -> n > 5;

        int[] res3 = filterTwoConditions(numbers, isEven, isGreaterThan5);

        System.out.println("--- ЗАВДАННЯ 3: Фільтрація (Парне AND > 5) ---");
        System.out.println("Вхідний масив: " + Arrays.toString(numbers));
        System.out.println("Результат: " + Arrays.toString(res3));
        System.out.println("-----------------------------------------\n");


        // --- Перевірка Завдання 4 ---
        // Consumer: виводить ПРІЗВИЩЕ + ІМ'Я
        Consumer<Student> printName = student -> System.out.println(student.getName().toUpperCase());

        System.out.println("--- ЗАВДАННЯ 4: Вивід імені студента (Consumer) ---");
        forEachStudent(allStudents, printName);
        System.out.println("-----------------------------------------\n");


        // --- Перевірка Завдання 5 ---
        // Consumer: виводить квадрат числа
        Consumer<Integer> printSquare = n -> System.out.println("Квадрат " + n + ": " + (n * n));

        System.out.println("--- ЗАВДАННЯ 5: processIf (Вивід квадрата лише для парних чисел) ---");
        int[] numbers5 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        processIf(numbers5, isEven, printSquare);
        System.out.println("-----------------------------------------\n");


        // --- Перевірка Завдання 6 ---
        int[] numbers6 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        // Function: n -> 2^n
        Function<Integer, Integer> powerOfTwo = n -> (int) Math.pow(2, n);

        int[] res6 = processArray(numbers6, powerOfTwo);

        System.out.println("--- ЗАВДАННЯ 6: Обчислення 2^n (Function) ---");
        System.out.println("Результат 2^n: " + Arrays.toString(res6));
        System.out.println("-----------------------------------------\n");


        // --- Перевірка Завдання 7 ---
        int[] numbers7 = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

        // Function: число -> рядок
        Function<Integer, String> numberToString = n -> {
            return switch (n) {
                case 0 -> "нуль";
                case 1 -> "один";
                case 2 -> "два";
                case 3 -> "три";
                case 4 -> "чотири";
                case 5 -> "п'ять";
                case 6 -> "шість";
                case 7 -> "сім";
                case 8 -> "вісім";
                case 9 -> "дев'ять";
                default -> String.valueOf(n);
            };
        };

        String[] res7 = stringify(numbers7, numberToString);

        System.out.println("--- ЗАВДАННЯ 7: Перетворення чисел на рядки (stringify) ---");
        System.out.println("Результат: " + Arrays.toString(res7));
        System.out.println("-----------------------------------------\n");
    }
}
