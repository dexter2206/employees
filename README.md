# Employees - projekt na zajęcia z JDBC
## Wprowadzenie
Repozytorium zawiera przykłady użycia JDBC do omówienia podczas kursu prowadzonego w SDA. Przykłady zakładają, że 
uczniowie posiadają zaimportowaną bazę Employees z przykładowych baz MySQL: https://dev.mysql.com/doc/employee/en/employees-preface.html
## Zawartość repozytorium
- com.sda.employees.model - przykłady DTO
- com.sda.employees.examples - przykłady użycia JDBC, zarówno proste, jak i bardziej zaawansowane wykorzystujące repozytorium i DAO
- com.sda.employees.dao - implementacja Data Acces Object dla klasy Employee
- com.sda.employees.repository - implementacja repozytorium dla klasy Employee
## Proponowane zadania
### Proste programy
1. Napisać program, który wczyta nazwę działu a następnie wyświetli wszystkich jego (historycznych) managerów wraz z okresem
w jakim pełnili tę funkcję.
2. Napisać program, który utworzy nowy dział - IT - oraz doda do niego dwóch pracowników, w tym jednego pracowinkaw charakterze
jego managera.
### DTO i DAO
1. Napisać DTO dla encji Departamentu, Zatrudnienia, Pensji
2. Napisać DAO operujące na w.w. DTO
3. Przetestować w.w. klasy.
### Repozytoria
1. Uzupełnić repozytorium dla klasy Employee o metody umożliwiające:
  - otrzymanie listy pracowników pracujących w danym departamencie w zadanym dniu
  - znalezienie listy pracowników pełniących (w dowolnym czasie) funkcję managera
2. Dodać repozytorium dla encji Departamentu, posiadające nastęujące funkcjonalności:
  - pobranie listy wszystkich departamentów
  - dodanie Departamentu
  - ustawienie menadżera departamentu w danym okresie.
3. Dodać repozytorium dla encji Zatrudnienie
  -

