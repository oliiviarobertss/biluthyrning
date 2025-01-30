Biluthyrning - Spring Boot-applikation

Projektöversikt

Detta är en Spring Boot-baserad biluthyrningsapplikation där användare kan hantera kunder och bilar via en webbapplikation med Thymeleaf. Applikationen hanterar CRUD-operationer (Create, Read, Update, Delete) för bilar och kunder, samt har en sökfunktion.

Funktionalitet

Bilhantering: Lägg till, visa och sök efter bilar.

Kundhantering: Lägg till, visa och sök efter kunder.

Webbgränssnitt med Thymeleaf: Användarvänliga sidor för att hantera bilar och kunder.

REST API: Backend-exponering för datahantering.

BDD-testning: Implementerad med Cucumber och JUnit.

Docker: En Dockerfile för att köra applikationen i en container.

Teknologier

Spring Boot (MVC, JPA, Thymeleaf)

H2-databas (för testning)

Docker

JUnit & Mockito (enhetstester)

Cucumber (BDD-testning)

Installation & Körning

1️⃣ Klona projektet

git clone <GitHub-repo-URL>
cd biluthyrning

2️⃣ Bygg och starta med Maven

mvn clean install
mvn spring-boot:run

3️⃣ Starta med Docker

docker build -t biluthyrning .
docker run -p 8080:8080 biluthyrning

UML-diagram

UML-diagrammet över projektets användningsfall finns bifogat i dokumentationen.

Testning

För att köra tester:

mvn test

BDD-tester med Cucumber ligger i src/test/resources/features/

GitHub-repo

Projektets kod ligger i GitHub Classroom-repot: GitHub Classroom.

Författare: Olivia Roberts

