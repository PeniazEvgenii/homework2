package by.astondev.homework2.taskcollection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class CustomArrayListTest {

    @Nested
    class MethodAdd {
        private CustomArrayList<Integer> list;

        @BeforeEach
        void initList() {
            list = new CustomArrayList<>(2);
        }

        @Test
        void addShouldReturnTrue() {
            assertTrue(list.add(1));
        }

        @Test
        void addCheckIncreaseSizeList() {
            assertEquals(0, list.size());

            list.add(1);
            assertEquals(1, list.size());

            list.add(2);
            assertEquals(2, list.size());
        }

        @Test
        void addShouldReturnTrueOnSaveNull() {
            assertTrue(list.add(null));
            assertEquals(1, list.size());
        }

        @Test
        void addShouldIncreaseArrayAfterThreshold() {
            list.add(1);
            list.add(2);
            list.add(3);

            assertEquals(3, list.size());
        }

        @Test
        void addOnZeroInitialCapacityShouldIncrease() throws NoSuchFieldException, IllegalAccessException {
            CustomArrayList<Integer> list = new CustomArrayList<>(0);

            assertEquals(0, list.size());
            list.add(1);
            assertEquals(1, list.size());
            list.add(2);
            list.add(3);
            assertEquals(3, list.size());

            Field valuesField = CustomArrayList.class.getDeclaredField("values");
            valuesField.setAccessible(true);

            Object[] dataArr = (Object[]) valuesField.get(list);
            assertEquals(4, dataArr.length);
            list.add(2);
            list.add(3);
            assertEquals(5, list.size());

            dataArr = (Object[]) valuesField.get(list);
            assertEquals(7, dataArr.length);
        }
    }

    @Nested
    class MethodGet {
        private CustomArrayList<String> list;

        @BeforeEach
        void initList() {
            list = new CustomArrayList<>();
            list.add("one");
            list.add("two");
            list.add("three");
        }

        @Test
        void getShouldThrowIndexOutOfBoundsIndexLowerZero() {
            assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
        }

        @Test
        void getShouldThrowIndexOutOfBoundsIndexHigherSize() {
            assertThrows(IndexOutOfBoundsException.class, () -> list.get(5));
        }

        @Test
        void getShouldThrowIndexOutOfBoundsIndexEqualsSize() {
            assertThrows(IndexOutOfBoundsException.class, () -> list.get(list.size()));
        }

        @Test
        void getShouldNotThrowIndexOutOfBounds() {
            assertDoesNotThrow(() -> list.get(0));
            assertDoesNotThrow(() -> list.get(1));
            assertDoesNotThrow(() -> list.get(2));
        }

        @Test
        void getShouldReturnFirst() {
            String actualResult = list.get(0);
            String expectedResult = "one";

            assertEquals(expectedResult, actualResult);
        }

        @Test
        void getShouldReturnLast() {
            String actualResult = list.get(list.size() - 1);
            String expectedResult = "three";

            assertEquals(expectedResult, actualResult);
        }

        @Test
        void getFirstReturnFirst() {
            String actualResult = list.getFirst();
            String expectedResult = "one";

            assertEquals(expectedResult, actualResult);
        }

        @Test
        void getFirstShouldThrowNoSuchElementFromEmptyList() {
            list = new CustomArrayList<>();

            assertThrows(NoSuchElementException.class, () -> list.getFirst());
        }

        @Test
        void getLastReturnLast() {
            String actualResult = list.getLast();
            String expectedResult = "three";

            assertEquals(expectedResult, actualResult);
        }

        @Test
        void getLastShouldThrowNoSuchElementFromEmptyList() {
            list = new CustomArrayList<>();

            assertThrows(NoSuchElementException.class, () -> list.getLast());
        }
    }

    @Nested
    class MethodRemove {
        private CustomArrayList<String> list;

        @BeforeEach
        void initList() {
            list = new CustomArrayList<>();
            list.add("one");
            list.add("two");
            list.add("three");
            list.add("three");
        }

        @Test
        void removeByIndexShouldReturnFirstElement() {
            String actualResult = list.remove(0);
            String expectedResult = "one";

            assertEquals(expectedResult, actualResult);
        }

        @Test
        void removeByIndexShouldShitElement() {
            int sizeOld = list.size();
            assertEquals(4, sizeOld);

            list.remove(0);
            int sizeNew = list.size();
            String actualNewResult = list.get(0);
            String expectedNewResult = "two";

            assertEquals(3, sizeNew);
            assertEquals(expectedNewResult, actualNewResult);
        }

        @Test
        void removeByIndexShouldReturnLastElement() {
            String actualResult = list.remove(3);
            String expectedResult = "three";

            assertEquals(expectedResult, actualResult);
        }

        @Test
        void removeByIndexLastElementShouldReduceSize() {
            int sizeOld = list.size();

            list.remove(0);
            int sizeNew = list.size();

            assertEquals(sizeOld - 1, sizeNew);
        }

        @Test
        void removeShouldThrowIndexOutOfBoundsIndexLowerZero() {
            assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
        }

        @Test
        void removeShouldThrowIndexOutOfBoundsIndexHigherSize() {
            assertThrows(IndexOutOfBoundsException.class, () -> list.remove(5));
        }

        @Test
        void removeShouldThrowIndexOutOfBoundsIndexEqualsSize() {
            assertThrows(IndexOutOfBoundsException.class, () -> list.remove(list.size()));
        }

        @Test
        void removeByElementShouldThrowNoSuchElement() {
            assertThrows(NoSuchElementException.class, () -> list.remove("four"));
        }

        @Test
        void removeByElementRemoveOnlyFirstDuplicate() {
            String actualResult = list.remove("three");

            assertEquals("three", actualResult);
            assertEquals(3, list.size());
            assertEquals("three", list.get(2));
        }

        @Test
        void removeByElementFromEmptyListShouldThrowNoSuchElement() {
            CustomArrayList<String> emptyList = new CustomArrayList<>();
            assertThrows(NoSuchElementException.class, () -> emptyList.remove("four"));
        }
    }

    @Nested
    class AddAllMethod {
        private CustomArrayList<String> list;
        private CustomArrayList<String> toAdd;

        @BeforeEach
        void init() {
            list = new CustomArrayList<>();
            list.add("one");
            list.add("two");
            list.add("three");
            toAdd = new CustomArrayList<>();
        }

        @Test
        void addAllShouldReturnFalseOnEmptyList() {
            assertFalse(list.addAll(toAdd));
        }

        @Test
        void addAllShouldReturnFalseOnNull() {
            assertFalse(list.addAll(null));
        }

        @Test
        void addAllShouldReturnTrueOnListWithElement() {
            toAdd.add("four");
            toAdd.add("five");

            assertTrue(list.addAll(toAdd));
        }

        @Test
        void addAllShouldIncrementSizeOnListWithElement() {
            toAdd.add("four");
            toAdd.add("five");

            assertEquals(3, list.size());
            list.addAll(toAdd);
            assertEquals(3 + toAdd.size(), list.size());
        }

        @Test
        void addAllSuccessAcceptSubtypeList() {
            CustomArrayList<Number> numList = new CustomArrayList<>();
            numList.add(1.5);
            numList.add(2);
            CustomArrayList<Integer> intList = new CustomArrayList<>();
            intList.add(10);
            intList.add(20);
            assertTrue(numList.addAll(intList));
            assertEquals(4, numList.size());
            assertEquals(1.5, numList.get(0));
            assertEquals(20, numList.get(3));
        }
    }

}