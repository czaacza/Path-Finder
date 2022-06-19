# Path-Finder
JavaFX GUI application using Dijkstra and BFS algorithms to find the shortest path between two given vertices, and tell whether the given graph is connected or not.

## Sample screen
<img src="https://github.com/czaacza/Path-Finder/blob/master/img/mainImg.PNG" width="815" height="600"/>

## Usage
### PL
Aby otworzyć aplikację należy zainstalować i wdrożyć do środowiska bibliotekę **javaFX**, wersja 18.

Przed zbudowaniem aplikacji w środowisku obsługującym pliki .java należy:
- ustawić ścieżkę **Resources Root** na folder **src/main/resources**
- ustawić ścieżkę **Sources Root** na folder **src/main/java**
- usunąć wszystkie inne ścieżki Sources i Resources Root

Dzięki temu program zostanie zbudowany poprawnie, a importując lub zapisując graf do pliku wystarczy wpisać nazwę pliku znajdującego się w folderze  src/main/resources/data, a nie całą ścieżkę do pliku.

Zalecane środowisko uruchomieniowe to **Intellij Idea** - Dla łatwego uruchomienia programu zaleca się stworzenie nowego projektu JavaFX i zamiana oryginalnego folderu src na folder src z repozytorium.<br/><br/>

### ENG
Be sure to install and set **javaFX**, v.18 library before opening the application.

Before building the application in your IDE:
- set **src/main/resources** as the **Resources Root**
- set **src/main/java** as the **Sources Root**
- remove all other sources and resources roots

As a result, the program can now be succesfully built. What is more, the name import and export graph path can now include only the name of the file, not the absolute opath.

Recommended IDE for building and opening the project is **Intellij Idea**, where you can easily create a new JavaFX project and replace the src folder with the one given in repo.

## Contributors
- Mateusz Czarnecki ([czaacza](https://github.com/czaacza))
- Paweł Jędrzejczyk ([Javelel](https://github.com/Javelel))
