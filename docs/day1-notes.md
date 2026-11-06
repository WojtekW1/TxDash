# Dzień 1: HashMap i optymalizacja wyszukiwania

## Ćwiczenie na papierze

### Trasowanie put (16 bucketów, maska 0b1111, bez spreadingu)

Klucze o hashach: 5, 21, 37, 6, 22, 5 (ostatni equals z pierwszym, inna wartość)

### Resize 16 → 32

### Usunięcie środkowego węzła z łańcucha

## Pytania sprawdzające

1. Dlaczego rozmiar tablicy to potęga dwójki?
2. Po co spreading i co by się stało bez niego?
3. Czemu przy resize nie wystarczy skopiować węzłów na te same indeksy?
4. Co się stanie z kluczem mutowalnym po zmianie pola w `hashCode`?
5. Czemu `HashMap<Long, V>` kosztuje więcej pamięci niż `LongSparseArray<V>`?

## Część D: Big O

1. `ArrayList.contains` vs `HashSet.contains`
2. `distinct()` / `distinctBy {}`
3. `ArrayList.removeAt(0)` w pętli
4. `Map<Long, Transaction>` dla 100k elementów
5. `findDuplicates` naiwnie vs z mapą

## Benchmark

| N | find (lista) | findById (indeks) | duplikaty naiwnie | duplikaty z mapą |
|---|---|---|---|---|
| 1 000 | | | | |
| 10 000 | | | | |
| 100 000 | | | | |

## Wnioski
