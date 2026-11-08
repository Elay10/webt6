# Event Registration API

A small in-memory Spring Boot REST API for the Event Registration scenario. It does not connect to a database or to the Lab 4 frontend.

## Requirements and running

- JDK 17 or later
- Gradle Wrapper (included)

From the project directory, start the application on port 8080:

```powershell
.\gradlew.bat bootRun
```

Run the automated tests with:

```powershell
.\gradlew.bat test
```

## Endpoints

| Method and path | Response |
|---|---|
| `GET /api/hello` | JSON welcome message |
| `GET /api/events` | Three in-memory event objects |
| `GET /api/info` | Application name, course, and semester from `application.properties` |
| `GET /api/events/{id}` | Event by ID, or a JSON `404` message if it does not exist |

Example: `http://localhost:8080/api/events`

## Code structure

- `EventRegistrationApplication` starts Spring Boot and component scanning.
- `RegistrationController` handles HTTP requests and delegates event operations to `EventService`. Spring injects the service through the controller constructor.
- `EventService` provides the welcome text and the in-memory list of events.
- `EventInfo` is the record serialized as JSON by Spring MVC.
- `application.properties` holds the application name, course, and semester; the controller reads them with `@Value`.

The project intentionally has no database, entities, repositories, authentication, frontend integration, or full CRUD operations; those are outside this laboratory's scope.
