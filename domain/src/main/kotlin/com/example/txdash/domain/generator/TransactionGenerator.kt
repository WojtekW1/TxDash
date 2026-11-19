package com.example.txdash.domain.generator

import com.example.txdash.domain.model.Transaction
import com.example.txdash.domain.model.TransactionStatus
import com.example.txdash.domain.model.TransactionType
import kotlin.random.Random

class TransactionGenerator(private val seed: Long) {

    private val terminals = listOf("T-001", "T-002", "T-003", "T-004", "T-005", "T-006", "T-007", "T-009", "T-010", "T-011", "T-012", "T-013", "T-014", "T-015", "T-016", "T-017", "T-019", "T-020")
    private val categories = listOf("Grocery", "Health care", "Car maintenance", "Activities", "Insurance", "Trips")
    private val merchants = listOf("Lidl", "LuxMed", "Brembo", "Cinema City", "PZU", "Rainbow Tours", "Amazon", "Rossmann", "Apple", "Logitech")

    private val BASE = 1_700_000_000_000L
    private val DAYS_30_IN_MS = 30L * 24 * 60 * 60 * 1000

    fun generate(count: Int, duplicateRatio: Double = 0.0): List<Transaction> {
        require(count >= 0) {"Count must be greater than 0 or equal to 0"}
        require(duplicateRatio in 0.0..0.5) {"Duplicate ratio not in scope"}

        val random = Random(seed)
        val duplicateCount = (count * duplicateRatio).toInt()
        val originalCount = count - duplicateCount

        val originals = List(originalCount) { index ->
            Transaction(
                id = (index + 1).toLong(),
                terminalId = terminals.random(random),
                rrn = (index + 1).toString().padStart(12, '0'),
                amountMinor = random.nextLong(100, 500_000),
                currency = "PLN",
                timestampMillis = BASE + random.nextLong(0, DAYS_30_IN_MS),
                category = categories.random(random),
                merchant = merchants.random(random),
                status = TransactionStatus.entries.random(random),
                type = TransactionType.entries.random(random)
            )
        }

        val duplicates = List(duplicateCount) {
            val original = originals.random(random)
            original.copy(
                timestampMillis = original.timestampMillis + random.nextLong(1_000, 6_000)
            )
        }

        val mixed = (originals + duplicates).shuffled(random).mapIndexed { index, transaction -> transaction.copy(id = (index + 1).toLong()) }

        return mixed
    }
}