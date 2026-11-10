package com.example.txdash.domain.model

import com.example.txdash.domain.structures.SimpleHashMap
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class TransactionTest {

    private fun validTransaction (
        id: Long = 1123L,
        terminalId: String = "Terminal1",
        rrn: String = "382922918103",
        amountMinor: Long = 1000L,
        currency: String = "PLN",
        timestampMillis: Long = 1243L,
        category: String = "GROCERY",
        merchant: String = "Pepco",
        status: TransactionStatus = TransactionStatus.APPROVED,
        type: TransactionType = TransactionType.SALE
    ): Transaction {
        return Transaction(
            id = id,
            terminalId = terminalId,
            rrn = rrn,
            amountMinor = amountMinor,
            currency = currency,
            timestampMillis = timestampMillis,
            category = category,
            merchant = merchant,
            status = status,
            type = type
        )
    }

    @Test
    fun givenCorrectData_whenCreatingObject_thenObjectCreated() {
        //Given
        val id = 1123L
        val terminalId = "Terminal1"
        val rrn = "382922918103"
        val amountMinor = 1000L
        val currency = "PLN"
        val timestampMillis = 1243L
        val category = "GROCERY"
        val merchant = "Pepco"
        val status = TransactionStatus.APPROVED
        val type = TransactionType.SALE

        //When
        val transaction = validTransaction()

        //Then
        assertEquals(id, transaction.id)
        assertEquals(terminalId, transaction.terminalId)
        assertEquals(rrn, transaction.rrn)
        assertEquals(amountMinor, transaction.amountMinor)
        assertEquals(currency, transaction.currency)
        assertEquals(timestampMillis, transaction.timestampMillis)
        assertEquals(category, transaction.category)
        assertEquals(merchant, transaction.merchant)
        assertEquals(status, transaction.status)
        assertEquals(type, transaction.type)
    }

    @Test(expected = IllegalArgumentException::class)
    fun givenAmountMinorValueZero_whenCreatingTransaction_thenIllegalArgumentException() {
        //Given
        val amountMinor = 0L


        //When
        validTransaction(amountMinor = amountMinor)
    }

    @Test(expected = IllegalArgumentException::class)
    fun givenAmountMinorValueNegative_whenCreatingTransaction_thenIllegalArgumentException() {
        //Given
        val amountMinor = -5L


        //When
        validTransaction(amountMinor = amountMinor)
    }

    @Test(expected = IllegalArgumentException::class)
    fun givenEmptyTerminalId_whenCreatingTransaction_thenIllegalArgumentException() {
        //Given
        val terminalId = ""

        //When
        validTransaction(terminalId = terminalId)
    }

    @Test(expected = IllegalArgumentException::class)
    fun givenRRNShorterThanTwelve_whenCreatingTransaction_thenIllegalArgumentException() {
        //Given
        val rrn = "3829229181"

        //When
        validTransaction(rrn = rrn)
    }

    @Test(expected = IllegalArgumentException::class)
    fun givenRRNLongerThanTwelve_whenCreatingTransaction_thenIllegalArgumentException() {
        //Given
        val rrn = "3829229181826"

        //When
        validTransaction(rrn = rrn)
    }

    @Test
    fun givenTransactionValues_whenCreatingTwoIdenticalTransactions_thenTransactionsAndHashCodesAreEqual() {
        //Given
        val transaction1 = validTransaction()
        val transaction2 = validTransaction()

        //When
        val equalTransactions = transaction1 == transaction2
        val equalTransactionsHashCodes = transaction1.hashCode() == transaction2.hashCode()

        //Then
        assertTrue(equalTransactions)
        assertTrue(equalTransactionsHashCodes)
    }

    @Test
    fun givenValidTransaction_whenChangedTransactionStatus_thenOriginalIsWithoutChanges() {
        //Given
        val transaction = validTransaction()

        //When
        val reversed = transaction.copy(status = TransactionStatus.REVERSED)

        //Then
        assertEquals(transaction.id, reversed.id)
        assertEquals(transaction.terminalId, reversed.terminalId)
        assertEquals(transaction.rrn, reversed.rrn)
        assertEquals(transaction.amountMinor, reversed.amountMinor)
        assertEquals(transaction.currency, reversed.currency)
        assertEquals(transaction.timestampMillis, reversed.timestampMillis)
        assertEquals(transaction.category, reversed.category)
        assertEquals(transaction.merchant, reversed.merchant)
        assertEquals(TransactionStatus.REVERSED, reversed.status)
        assertEquals(transaction.type, reversed.type)
        assertEquals(TransactionStatus.APPROVED, transaction.status)
    }

    @Test
    fun givenSimpleHashMap_whenCreatingAndPuttingTransactions_thenGetAndPutWorksFine() {
        //Given
        val map = SimpleHashMap<Transaction, String>()

        //When
        map.put(validTransaction(), "x")

        //Then
        assertEquals("x", map.get(validTransaction()))
    }

}