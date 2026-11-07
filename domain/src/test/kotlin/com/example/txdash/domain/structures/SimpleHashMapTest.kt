package com.example.txdash.domain.structures

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class SimpleHashMapTest {

    @Test(expected = IllegalArgumentException::class)
    fun givenZeroCapacity_whenCreated_thenThrowsIllegalArgumentException() {
        SimpleHashMap<String, Int>(0)
    }

    @Test(expected = IllegalArgumentException::class)
    fun givenMinusCapacity_whenCreated_thenThrowsIllegalArgumentException() {
        SimpleHashMap<String, Int>(-5)
    }

    @Test
    fun givenSizeNewMap_whenCreated_thenSizeIsZero() {
        //Given
        val map = SimpleHashMap<String, Int>()

        //When
        val size = map.size

        //Then
        assertEquals(0, size)
    }

    @Test
    fun givenEmptyMap_whenPutNewKey_thenGetReturnsValue() {
        //Given
        val map = SimpleHashMap<String, Int>()

        //When
        map.put("a", 1)

        //Then
        assertEquals(1, map.size)
        assertEquals(1, map.get("a"))
    }

    @Test
    fun givenEmptyMap_whenKeyNotExists_thenGetReturnsNull() {
        //Given
        val map = SimpleHashMap<String, Int>()

        //When
        val value = map.get("a")

        //Then
        assertNull(value)
    }

    @Test
    fun givenKeyToEmptyMap_whenKeyAlreadyExists_thenOldValueReturned() {
        //Given
        val map = SimpleHashMap<String, Int>()
        map.put("a", 1)

        //When
        val oldValue = map.put("a", 2)

        //Then
        val currentValue = map.get("a")
        assertEquals(2, currentValue)
        assertEquals(1, map.size)
        assertEquals(1, oldValue)
    }

    @Test
    fun givenKeyToEmptyMap_whenAddedKeyInTheSamePlace_thenCollisionHandledProperly() {
        //Given
        val map = SimpleHashMap<String, Int>(1)
        map.put("a", 1)

        //When
        map.put("b", 2)

        //Then
        val value1 = map.get("a")
        val value2 = map.get("b")

        assertEquals(1, value1)
        assertEquals(2, value2)
        assertEquals(2, map.size)
    }

    @Test
    fun givenEmptyMap_whenPutFirstKey_thenPutReturnsNull() {
        //Given
        val map = SimpleHashMap<String, Int>()

        //When
        val oldValue = map.put("a", 1)

        //Then
        assertNull(oldValue)
    }

    @Test
    fun givenEmptyMap_whenPutKey_thenKeyExists() {
        //Given
        val map = SimpleHashMap<String, Int>()

        //When
        map.put("a", 1)

        //Then
        assertEquals(true, map.containsKey("a"))
    }

    @Test
    fun givenEmptyMap_whenSearchingKey_thenContainsKeyReturnsFalse() {
        //Given
        val map = SimpleHashMap<String, Int>()

        //When
        val searched = map.containsKey("x")

        //Then
        assertEquals(false, searched)
    }

    @Test
    fun givenEmptyMap_whenPutNullValue_thenContainsKeyReturnsTrue() {
        //Given
        val map = SimpleHashMap<String, Int?>()

        //When
        map.put("a", null)

        //Then
        assertEquals(true, map.containsKey("a"))
    }

    @Test
    fun givenMapWithExistingKey_whenDeletingExistingKey_thenContainsKeyReturnsFalse() {
        //Given
        val map = SimpleHashMap<String, Int>()
        map.put("a", 1)

        //When
        map.remove("a")

        //Then
        assertEquals(false, map.containsKey("a"))
        assertEquals(0, map.size)
    }

    @Test
    fun givenEmptyMap_whenDeletingNonExistingKey_thenRemoveReturnsNull() {
        //Given
        val map = SimpleHashMap<String, Int>()

        //When
        val result = map.remove("x")

        //Then
        assertNull(result)
    }

    @Test
    fun givenChainOfThree_whenRemovingFirstEntry_thenOthersRemain() {
        //Given
        val map = SimpleHashMap<String, Int>(1)
        map.put("a", 1)
        map.put("b", 2)
        map.put("c", 3)

        //When
        val removed = map.remove("c")

        //Then
        assertEquals(3, removed)
        assertNull(map.get("c"))
        assertEquals(1, map.get("a"))
        assertEquals(2, map.get("b"))
        assertEquals(2, map.size)
    }

    @Test
    fun givenChainOfThree_whenRemovingMiddleEntry_thenOthersRemain() {
        //Given
        val map = SimpleHashMap<String, Int>(1)
        map.put("a", 1)
        map.put("b", 2)
        map.put("c", 3)

        //When
        val removed = map.remove("b")

        //Then
        assertEquals(2, removed)
        assertNull(map.get("b"))
        assertEquals(1, map.get("a"))
        assertEquals(3, map.get("c"))
        assertEquals(2, map.size)
    }

    @Test
    fun givenChainOfThree_whenRemovingLastEntry_thenOthersRemain() {
        //Given
        val map = SimpleHashMap<String, Int>(1)
        map.put("a", 1)
        map.put("b", 2)
        map.put("c", 3)

        //When
        val removed = map.remove("a")

        //Then
        assertEquals(1, removed)
        assertNull(map.get("a"))
        assertEquals(2, map.get("b"))
        assertEquals(3, map.get("c"))
        assertEquals(2, map.size)
    }

    @Test
    fun givenEmptyMap_whenPutThousandEntries_thenSizeAndValueIsReturned() {
        //Given
        val map = SimpleHashMap<String, Int>()

        //When
        for (i in 1..1000) {
            map.put("key$i", i)
        }

        //Then
        assertEquals(1000, map.size)
        for (i in 1..1000) {
            assertEquals(i, map.get("key$i"))
        }
    }

    @Test
    fun givenEmptyMap_whenPut13Elements_thenResizeCorrect() {
        //Given
        val map = SimpleHashMap<String, Int>()

        //When
        for (i in 1..13) {
            map.put("key$i", i)
        }

        //Then
        assertEquals(13, map.size)

        for (i in 1..13) {
            assertEquals(i, map.get("key$i"))
        }
    }

    @Test
    fun givenMapWith100Elements_whenRemovedEvenElements_thenOddReturnsNull() {
        //Given
        val map = SimpleHashMap<String, Int>()
        for (i in 1..100) {
            map.put("key$i", i)
        }

        //When
        for (i in 1..100) {
            if (i % 2 == 0) {
                map.remove("key$i")
            }
        }

        //Then
        assertEquals(50, map.size)

        for (i in 1..100) {
            if (i % 2 == 0) {
                assertNull(map.get("key$i"))
            } else {
                assertEquals(i, map.get("key$i"))
            }
        }
    }

    @Test
    fun givenEmptyMap_whenPutNegativeHashCode_thenValueIsReturned() {
        //Given
        val map = SimpleHashMap<Int, String>()

        //When
        map.put(-5, "x")

        //Then
        assertEquals("x", map.get(-5))
    }
}