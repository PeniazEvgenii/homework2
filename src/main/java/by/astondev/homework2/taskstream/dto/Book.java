package by.astondev.homework2.taskstream.dto;

import java.util.Objects;
import java.util.UUID;

public class Book {
    private final UUID id;
    private final int countPage;
    private final int year;

    public Book(UUID name, int countPage, int year) {
        this.id = name;
        this.countPage = countPage;
        this.year = year;
    }

    public UUID getId() {
        return id;
    }

    public int getCountPage() {
        return countPage;
    }

    public int getYear() {
        return year;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Book book)) return false;
        return countPage == book.countPage && year == book.year && Objects.equals(id, book.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, countPage, year);
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + id + '\'' +
                ", countPage=" + countPage +
                ", year=" + year +
                '}';
    }
}
