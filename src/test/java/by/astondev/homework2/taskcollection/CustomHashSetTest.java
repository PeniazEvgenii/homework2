package by.astondev.homework2.taskcollection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class CustomHashSetTest {
    private CustomHashSet<String> set;

    @BeforeEach
    void init() {
        set = new CustomHashSet<>();
        set.add("one");
        set.add("two");
        set.add("three");
    }

    @Nested
    class AddMethodTest {

        @Test
        void addShouldReturnTrue() {
            assertTrue(set.add("four"));
        }

        @Test
        void addShouldReturnFalseOnDuplicate() {
            assertFalse(set.add("one"));
        }

        @Test
        void addShouldReturnFalseOnNull() {
            assertFalse(set.add(null));
        }

        @Test
        void addShouldResizeAndIncreaseTable() throws NoSuchFieldException, IllegalAccessException {
            Field data = CustomHashSet.class.getDeclaredField("data");
            data.setAccessible(true);
            Object[] objects = (Object[]) data.get(set);
            int sizeOld = objects.length;

            assertEquals(16, sizeOld);

            for (int i = 0; i < 10; i++) {
                set.add(String.valueOf(i));
            }

            objects = (Object[]) data.get(set);
            int sizeNew = objects.length;

            assertEquals(32, sizeNew);
        }
    }

    @Nested
    class RemoveMethodTest {
        @BeforeEach
        void init() {
            set.add("four");
            set.add("five");
            set.add("six");
            set.add("seven");
            set.add("eight");
            set.add("nine");
            set.add("ten");
        }

        @Test
        void shouldReturnTrueOnRemoveFirst() {
            assertTrue(set.remove("one"));
        }

        @Test
        void shouldReturnTrueOnRemoveLast() {
            assertTrue(set.remove("ten"));
        }

        @Test
        void shouldReturnTrueOnRemoveIntermediate() {
            assertTrue(set.remove("six"));
        }

        @Test
        void shouldReturnFalseOnRemoveNull() {
            assertFalse(set.remove(null));
        }

        @Test
        void shouldReduceSizeAfterRemove() {
            int sizeOld = set.size();
            set.remove("six");

            int sizeNew = set.size();

            assertEquals(sizeOld - 1, sizeNew);
        }
    }

}