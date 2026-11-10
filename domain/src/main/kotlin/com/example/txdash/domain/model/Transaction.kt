package com.example.txdash.domain.model

data class Transaction(
    val id: Long,
    val terminalId: String,
    val rrn: String,
    val amountMinor: Long,
    val currency: String,
    val timestampMillis: Long,
    val category: String,
    val merchant: String,
    val status: TransactionStatus,
    val type: TransactionType,
) {
    init {
        require(amountMinor > 0) { "amountMinor must be greater than 0" }
        require(terminalId.isNotBlank()) { "terminalId must not be blank" }
        require(rrn.length == 12) { "rrn length must be equal to 12" }
    }
}
