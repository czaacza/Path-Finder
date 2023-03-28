# Path-Finder
JavaFX GUI application using Dijkstra and BFS algorithms to find the shortest path between two given vertices, and tell whether the given graph is connected or not.

## Sample screenshot
<img src="https://github.com/czaacza/Path-Finder/blob/master/img/mainImg.PNG" width="600" />

## Purpose

### ENG
The program can be used to:
- Generate a graph of given measurements and of edges' weights generated randomly in given range
- Load and save the graph to the text file of applicable format
- Split the graph into smaller ones by selecting border vertices
- Find the shortest path between two given vertices (Dijkstra's algorithm)
- Check if the loaded graph is connected or not (BFS algorithm)

### PL
Program może być wykorzystany by:
- Wygenerować graf o podanych wymiarach i o losowo wygenerowanych z danego zakresu, wagach krawędzi
- Załadować i zapisać graf do pliku tekstowego o odpowiednim formacie
- Podzielić graf na mniejsze części poprzez wybranie wierzchołków znajdujących się na krawędziach
- Znaleźć najkrótszą możliwą drogę pomiędzy dwoma wybranymi wierzchołkami (Algorytm Dijkstry)
- Sprawdzić, czy załadowany graf jest spójny (Algorytm BFS)

## Usage
### ENG
Be sure to install and set **javaFX**, v.18 library before opening the application.

Before building the application in your IDE:
- set **src/main/resources** as the **Resources Root**
- set **src/main/java** as the **Sources Root**
- remove all other sources and resources roots

As a result, the program can now be succesfully built. What is more, the name import and export graph path can now include only the name of the file, not the absolute opath.

Recommended IDE for building and opening the project is **Intellij Idea**, where you can easily create a new JavaFX project and replace the src folder with the one given in repo.

### PL
Aby otworzyć aplikację należy zainstalować i wdrożyć do środowiska bibliotekę **javaFX**, wersja 18.

Przed zbudowaniem aplikacji w środowisku obsługującym pliki .java należy:
- ustawić ścieżkę **Resources Root** na folder **src/main/resources**
- ustawić ścieżkę **Sources Root** na folder **src/main/java**
- usunąć wszystkie inne ścieżki Sources i Resources Root

Dzięki temu program zostanie zbudowany poprawnie, a importując lub zapisując graf do pliku wystarczy wpisać nazwę pliku znajdującego się w folderze  src/main/resources/data, a nie całą ścieżkę do pliku.

Zalecane środowisko uruchomieniowe to **Intellij Idea** - Dla łatwego uruchomienia programu zaleca się stworzenie nowego projektu JavaFX i zamiana oryginalnego folderu src na folder src z repozytorium.<br/><br/>


## Class diagram

<img src="https://github.com/czaacza/Path-Finder/blob/master/img/ClassDiagram.drawio.png" width="676" height="600"/>

### ENG
The diagram picture the modules used to code the application. More info in the implementation specification in PL language.

### PL
Diagram przedstawia moduły wykorzystane do napisania aplikacji. Więcej informacji w specyfikacji implementacyjnej.

## Contributors
- Mateusz Czarnecki ([czaacza](https://github.com/czaacza))
- Paweł Jędrzejczyk ([Javelel](https://github.com/Javelel))
