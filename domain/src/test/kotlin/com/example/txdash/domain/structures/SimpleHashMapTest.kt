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
}