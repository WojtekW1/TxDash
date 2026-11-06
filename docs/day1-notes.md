# Dzień 1: HashMap i optymalizacja wyszukiwania

## Ćwiczenie na papierze

Tablica ma 16 miejsc (0-15). Miejsce wpisu = reszta z dzielenia jego liczby (hash) przez 16.

### 1. Wstawianie

Dodajesz 6 wpisów o liczbach: 5, 21, 37, 6, 22, 5 (ostatnia to ten sam klucz co pierwsza, więc nadpisanie, nie nowy wpis).
Stan tablicy po każdym kroku i końcowa liczba wpisów:

miejsce 5: 3 wpisy (5, 21, 37)
miejsce 6: 2 wpisy (6, 22)

### 2. Powiększenie do 32 miejsc

Tablicę powiększasz z 16 do 32 miejsc. Policz miejsce każdego wpisu od nowa (reszta z dzielenia przez 32). Które wpisy zmieniły miejsce?

miejsce 5: 2 wpisy (5, 37)
miejsce 6: 1 wpis (6)
miejsce 21: 1 wpis (21)
miejsce 22: 1 wpis (22)

Zmieniły miejsce wpisy 21 i 22

### 3. Usunięcie wpisu ze środka łańcuszka

Na jednym miejscu tablicy wpisy tworzą łańcuszek: każdy wskazuje na następny. Usuwasz wpis ze środka. Co trzeba zmienić, żeby reszta łańcuszka dalej była połączona?

Należy zmienić wtedy wskazanie wpisu poprzedzającego usunięty na następujący po usuniętym

## Pytania sprawdzające

1. Po co zamieniamy klucz na liczbę (hash)?

Klucz zamieniamy na liczbę (hash) po to żeby identyfikacja była szybsza i łatwiejsza, z liczby wyliczamy miejsce w tablicy i idziemy tam od razu zamiast przeglądać wszystkie wpisy po kolei

2. Co to kolizja i co wtedy robimy?

Kolizja to moment, w którym w jedno miejsce w tablicy trafia kilka wpisów o różnych kluczach, wtedy należy utworzyć łańcuszek dopisując elementy jeden za drugim

3. Dlaczego po powiększeniu tablicy trzeba przenieść wszystkie wpisy na nowe miejsca?

Ponieważ zmienia się reszta z dzielenia, przy szukaniu liczymy już nową resztę więc wpis leżący w starym miejscu nie zostałby znaleziony

4. Co się stanie, gdy zmienisz klucz po wstawieniu go do mapy?

Zmiana klucza zmienia jego hash więc wyliczone miejsce jest inne niż to, gdzie wpis naprawdę leży

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
