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

    private fun indexFor(key: K): Int {
        return Math.floorMod(key.hashCode(), table.size)
    }

    operator fun get(key: K): V? {
        val index = indexFor(key)

        var current: Entry<K, V>? = table[index]

        while (current != null) {
            if (current.key == key) {
                return current.value
            }

            current = current.next
        }

        return null
    }

    fun put(key: K, value: V): V? {
        val index = indexFor(key)

        var current: Entry<K, V>? = table[index]

        while (current != null) {
            if (current.key == key) {
                val oldValue = current.value
                current.value = value
                return oldValue
            }

            current = current.next
        }

        val currentStart = table[index]
        val newEntry = Entry(key, value, currentStart)
        table[index] = newEntry
        size++

        return null
    }
}