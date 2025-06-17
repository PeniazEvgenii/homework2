package by.astondev.homework2.taskstream;

import by.astondev.homework2.taskstream.dto.Book;
import by.astondev.homework2.taskstream.dto.Student;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Main1 {
    private static final int BOOK_FILTER_YEAR = 2000;
    private static final int BOOK_LIMIT = 3;
    private static final String NOT_FOUND_MESSAGE = "Книга отсутствует в перечне";
    private static final String FOUND_MESSAGE_PATTERN = "Найдена книга %d года";

    public static void main(String[] args) {
        List<Student> students = List.of(
                new Student("Vasia", 18, generateBook(10)),
                new Student("Petya", 19, generateBook(10)),
                new Student("Nastia", 20, generateBook(10)),
                new Student("Katya", 25, generateBook(10)),
                new Student("Lena", 19, generateBook(10)),
                new Student("Dima", 23, generateBook(10))
        );

        students.stream()
                .peek(System.out::println)
                .map(Student::getBooks)
                .flatMap(Collection::stream)
                .sorted(Comparator.comparing(Book::getCountPage))
                .distinct()
                .filter(book -> book.getYear() > BOOK_FILTER_YEAR)
                .limit(BOOK_LIMIT)
                .map(Book::getYear)
                .findFirst()
                .ifPresentOrElse(
                        year -> System.out.printf(FOUND_MESSAGE_PATTERN, year),
                        () -> System.out.println(NOT_FOUND_MESSAGE)
                );
    }


    private static List<Book> generateBook(int count) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        List<Book> books = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            books.add(new Book(
                    UUID.randomUUID(),
                    random.nextInt(100, 500),
                    random.nextInt(1980, 2021)
            ));
        }
        return books;
    }
}
