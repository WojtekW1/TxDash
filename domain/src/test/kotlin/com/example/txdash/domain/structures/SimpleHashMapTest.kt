package com.example.txdash.domain.structures

import org.junit.Assert.assertEquals
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
    fun sizeNewMap_whenCreated_thenSizeIsZero() {
        //Given
        val map = SimpleHashMap<String, Int>()

        //When
        val size = map.size

        //Then
        assertEquals(0, size)
    }
}