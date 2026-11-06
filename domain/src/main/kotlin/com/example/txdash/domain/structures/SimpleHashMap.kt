package com.example.txdash.domain.structures

class Entry<K : Any, V>(
    val key: K,
    var value: V,
    var next: Entry<K, V>? = null
)

class SimpleHashMap<K : Any, V>(
    initialCapacity: Int = 16
) {
    init {
        require(initialCapacity > 0) { "initialCapacity must be greater than zero." }
    }
    private var table = arrayOfNulls<Entry<K, V>>(initialCapacity)
    var size: Int = 0
        private set
}