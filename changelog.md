# Changelog

## Commit 04 - Aleksander Pytel

- Wyswietlanie planszy przeniesione do klasy GridManager
- Poruszanie sie gracza rozwiązane za pomocą klikania na planszy
- Nowa klasa MoveHandler - obsługa poruszania się gracza oraz Dalekow
- Nowa Klasa InputHandler - obsługa kliknięć na planszy, 
     kliknięcia poza sąsiednimi polami są ignorowane
- Nowa klasa CollisionHandlerVisitor - obsługa kolizji przy urzyciu wzorca Visitor
- Po kolizji gracza z Dalekiem, gracz ginie
- Daleki poruszają się w kierunku gracza
- Gracz może poruszać się tylko po sąsiednich polach
- Sąsiednie pola są oznaczone na planszy
- Po kolizji Daleka z Dalekiem, oba giną i zostawiają po sobie Scrap
- Po kolizji Daleka z Scrapem, Dalek ginie
- Dodane nowe grafiki - Dalek, Scrap, Player

## Commit 03 - Mateusz Cyganek - Mapa, Rysowanie mapy, Aktualizacja mapy, Ruszanie gracza, Wczytywanie obrazków

-   Metoda do przełączania widoków
-   Inicjalizacja mapki
-   Rysowanie mapki
-   Poruszanie gracza
-   Uaktualnianie mapki po ruchu gracza
-   Wczytywanie obrazków

## Commit 02 - Mateusz Cyganek - GameController, Mechanizmy przełączania widoków, iController

-   Dodanie Kotrolera gry i końca gry (oraz ich pliki .fxml)
-   Dodanie interfejsu kontrollerów do inicializacji widoków
-   Uzupełnienie stałych w ustawieniach o ścieżki plików .fxml
-   Przeniesienie inicajlizacji widoku z maina

## Commit 01 - Mateusz Cyganek - Init projektu

-   Inicjalizacja projektu w gradle'u
-   Dodanie dark theme i okienka z Hello World
-   Dodanie ustawień aplikacji
-   Dodanie obiektów które znajdą się na planszy
