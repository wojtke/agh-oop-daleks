# Changelog

## Commit 16 - Aleksander Pytel - Dzialajace PowerUpy

-   Dodanie teleportera
-   Dodanie attractora

## Commit 15 - Aleksander Pytel - Wprowadzanie powerupów

-   Poprawa wyświetlania obiektów - kożystamy z BaseObject i getImage
-   GameMap przechowuje listę ObjectBase zamiast listy poszczegolnych klas bazowych
-   Zmiana sposobu rozwiązywania kolizji - teraz są metody do kolizji z konkretnym obiektem

## Commit 14 - Mateusz Cyganek - Kampania gracza

-   Nowy pusty widok wyboru poziomu kampanii
-   Widok kampanii
-   Obsługa postępu w trybie kampanii

## Commit 13 - Aleksander Pytel - Wyszukiwanie poziomow

-   Możliwość filtrowania poziomów
-   Poprawa widoku poziomów

## Commit 12 - Wojciech Jasiński - Lista poziomów z serwera

-   Lista poziomów do wybrania
-   Dodanie nowego poziomu
-   Usunięcie poziomu

## Commit 11 - Mateusz Cyganek - Serializacja JSON

-   Serializacja pobranych json'ów do obiektów projketu
-   Nowy pusty widok wyboru poziomu

## Commit 10 - Mateusz Cyganek - API, ServiceManager

-   Implementacja i uruchomienie API na prytwanym serwerze
-   Dodane enpointy API: Levels, Upload, Delete
-   ServiceManager z metodami implementującymi API

## Commit 09 - Wojciech Jasiński

-   Zmiany w metodzie MoveDaleks
-   Hermetyzacja pól w GameControler
-   directionTo rzuca wyjątek, gdy chcemy kierunek do samego siebie

## Commit 08 - Mateusz Cyganek - Poprawa kolizji, Poprawa Inicjalizacji gry, Poprawki

-   Poprawy nazw
-   Losowy wektor
-   Poprawa mechanizmu kolizji
-   Walidacja dodawania nowego daleka przy starcie gry

## Commit 07 - Mateusz Cyganek - Obsługiwanie wygranej/przegranej, Teleportacja, Poprawki

-   Poprawa widoku przegranej
-   Obsługa wygrania gry
-   Widok wygranej
-   Usunięcie zbędnych obrazków
-   Sprzątenie kodu
-   Rozwiązanie warningów

## Commit 06 - Wojciech Jasiński

-   Dodano nowe tekstury
-   Walidacja danych wprowadzanych na ekranie startowym
-   Fix buga, w którym gracz mógł ruszać się poza swój zasięg

## Commit 05 - Wojciech Jasiński

-   Nowa klasa RandomGameMapFactory tworząca mapkę
-   Parametry takie jak rozmiar mapy i ilość daleków wybierane w menu startowym
-   Dependency Injection z wykorzystaniem Guice - ustawienia mapki
-   Przełączanie widoków start->gra->gameOver
-   Fix poruszania się daleków w kierunku gracza
-   Metoda toVector przeniesiona do z Position do EDirection
-   Inicjalizacja gridPane przeniesiona do gridManager

## Commit 04 - Aleksander Pytel

-   Wyswietlanie planszy przeniesione do klasy GridManager
-   Poruszanie sie gracza rozwiązane za pomocą klikania na planszy
-   Nowa klasa MoveHandler - obsługa poruszania się gracza oraz Dalekow
-   Nowa Klasa InputHandler - obsługa kliknięć na planszy, kliknięcia poza sąsiednimi polami są ignorowane
-   Nowa klasa CollisionHandlerVisitor - obsługa kolizji przy użyciu wzorca Visitor
-   Po kolizji gracza z Dalekiem, gracz ginie
-   Daleki poruszają się w kierunku gracza
-   Gracz może poruszać się tylko po sąsiednich polach
-   Sąsiednie pola są oznaczone na planszy
-   Po kolizji Daleka z Dalekiem, oba giną i zostawiają po sobie Scrap
-   Po kolizji Daleka z Scrapem, Dalek ginie
-   Dodane nowe grafiki - Dalek, Scrap, Player

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
