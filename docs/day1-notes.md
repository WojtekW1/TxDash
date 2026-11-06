# Dzień 1: HashMap i optymalizacja wyszukiwania

## Ćwiczenie na papierze

16 wieszaków, numer wieszaka = reszta z dzielenia hasha przez 16.

### 1. Wstawianie

Hashe: 5, 21, 37, 6, 22, 5 (ostatni to to samo nazwisko co pierwszy, więc podmiana, nie dodanie).
Stan wieszaków po każdym kroku i końcowy rozmiar:

### 2. Powiększenie do 32 wieszaków

Dodawaj dalej, aż kurtek będzie więcej niż 12. Po przewieszeniu (reszta z dzielenia przez 32), które kurtki zmieniły wieszak?

### 3. Zdjęcie kurtki ze środka łańcuszka

Co trzeba zrobić, żeby kolejne kurtki dalej wisiały w łańcuszku?

## Pytania sprawdzające

1. Po co zamieniamy nazwisko na liczbę?

2. Co to kolizja i co wtedy robimy?

3. Dlaczego po powiększeniu szatni trzeba przewiesić wszystkie kurtki?

4. Co się stanie, gdy zmienisz klucz po wstawieniu go do mapy?

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
