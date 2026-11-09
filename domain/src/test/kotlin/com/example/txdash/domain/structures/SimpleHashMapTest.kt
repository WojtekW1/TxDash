package com.example.txdash.domain.structures

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
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
        // klucze 1 i 17 mają to samo miejsce (reszta 1 z dzielenia przez 16), a resize się nie włączy
        val map = SimpleHashMap<Int, String>(16)
        map.put(1, "a")

        //When
        map.put(17, "b")

        //Then
        assertEquals(2, map.maxBucketLength())
        assertEquals("a", map.get(1))
        assertEquals("b", map.get(17))
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
        // klucze 1, 17, 33 trafiają na to samo miejsce; łańcuszek: 33 -> 17 -> 1
        val map = SimpleHashMap<Int, String>(16)
        map.put(1, "a")
        map.put(17, "b")
        map.put(33, "c")
        assertEquals(3, map.maxBucketLength())

        //When
        val removed = map.remove(33)

        //Then
        assertEquals("c", removed)
        assertNull(map.get(33))
        assertEquals("a", map.get(1))
        assertEquals("b", map.get(17))
        assertEquals(2, map.size)
    }

    @Test
    fun givenChainOfThree_whenRemovingMiddleEntry_thenOthersRemain() {
        //Given
        // łańcuszek: 33 -> 17 -> 1
        val map = SimpleHashMap<Int, String>(16)
        map.put(1, "a")
        map.put(17, "b")
        map.put(33, "c")
        assertEquals(3, map.maxBucketLength())

        //When
        val removed = map.remove(17)

        //Then
        assertEquals("b", removed)
        assertNull(map.get(17))
        assertEquals("a", map.get(1))
        assertEquals("c", map.get(33))
        assertEquals(2, map.size)
    }

    @Test
    fun givenChainOfThree_whenRemovingLastEntry_thenOthersRemain() {
        //Given
        // łańcuszek: 33 -> 17 -> 1
        val map = SimpleHashMap<Int, String>(16)
        map.put(1, "a")
        map.put(17, "b")
        map.put(33, "c")
        assertEquals(3, map.maxBucketLength())

        //When
        val removed = map.remove(1)

        //Then
        assertEquals("a", removed)
        assertNull(map.get(1))
        assertEquals("b", map.get(17))
        assertEquals("c", map.get(33))
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

    @Test
    fun givenEmptyMap_whenMaxBucketLength_thenReturnsZero() {
        //Given
        val map = SimpleHashMap<String, Int>()

        //When
        val length = map.maxBucketLength()

        //Then
        assertEquals(0, length)
    }

    @Test
    fun givenMapWithCapacity16_whenPut3KeysInTheSamePlace_thenMaxBucketLengthIs3() {
        //Given
        val map = SimpleHashMap<Int, String>(16)

        //When
        map.put(1, "a")
        map.put(17, "b")
        map.put(33, "c")

        //Then
        assertEquals(3, map.maxBucketLength())
    }

    @Test
    fun givenEmptyMap_whenPut1000Elements_thenMaxBucketLengthLessThan10() {
        //Given
        val map = SimpleHashMap<String, Int>()

        //When
        for (i in 1..1000) {
            map.put("key$i", i)
        }

        //Then
        assertTrue(map.maxBucketLength() < 10)
    }
}