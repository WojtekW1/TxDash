package com.example.txdash.domain.generator

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

class TransactionGeneratorTest {
    @Test(expected = IllegalArgumentException::class)
    fun givenNegativeCount_whenGeneratingTransaction_thenReturnsIllegalArgumentException() {
        //Given
        val count = -1

        //When
        val generator = TransactionGenerator(1234L)
        generator.generate(count)
    }

    @Test(expected = IllegalArgumentException::class)
    fun givenNegativeDuplicateRatio_whenGeneratingTransaction_thenReturnsIllegalArgumentException() {
        //Given
        val count = 2
        val duplicateRatio = -0.1

        //When
        val generator = TransactionGenerator(1234L)
        generator.generate(count, duplicateRatio)
    }

    @Test(expected = IllegalArgumentException::class)
    fun givenDuplicateRatioOutOfBounds_whenGeneratingTransaction_thenReturnsIllegalArgumentException() {
        //Given
        val count = 2
        val duplicateRatio = 0.6

        //When
        val generator = TransactionGenerator(1234L)
        generator.generate(count, duplicateRatio)
    }

    @Test
    fun given1000Count_whenGeneratingTransactions_thenTransactionsListSizeIs1000() {
        //Given
        val count = 1000

        //When
        val list = TransactionGenerator(1234).generate(count)

        //Then
        assertEquals(1000, list.size)
    }

    @Test
    fun givenSameSeedAndArguments_whenGeneratingTransactions_thenTransactionsListsAreEqual() {
        //Given
        val count = 1000
        val seed = 123L

        //When
        val list1 = TransactionGenerator(seed).generate(count)
        val list2 = TransactionGenerator(seed).generate(count)

        //Then
        assertEquals(list1, list2)
    }

    @Test
    fun givenDifferentSeedAndArguments_whenGeneratingTransactions_thenTransactionsListsAreDifferent() {
        //Given
        val count = 1000
        val seed1 = 123L
        val seed2 = 124L

        //When
        val list1 = TransactionGenerator(seed1).generate(count)
        val list2 = TransactionGenerator(seed2).generate(count)

        //Then
        assertNotEquals(list1, list2)
    }

    @Test
    fun givenSeedAndArguments_whenGeneratingTransactions_thenTransactionsListsIdsAreUnique() {
        //Given
        val count = 1000
        val seed = 123L

        //When
        val list = TransactionGenerator(seed).generate(count)
        val areUnique = list.map { it.id }.toSet().size == count

        //Then
        assertEquals(true, areUnique)
    }
}