package com.example.txdash.domain.structures

class Entry<K : Any, V>(
    val key: K,
    var value: V,
    var next: Entry<K, V>? = null
)