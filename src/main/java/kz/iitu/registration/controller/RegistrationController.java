package kz.iitu.registration.controller;

import kz.iitu.registration.model.EventInfo;
import kz.iitu.registration.service.EventService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class RegistrationController {
    private final EventService eventService;
    private final String applicationName;
    private final String course;
    private final int semester;

    public RegistrationController(
            EventService eventService,
            @Value("${spring.application.name}") String applicationName,
            @Value("${app.course}") String course,
            @Value("${app.semester}") int semester
    ) {
        this.eventService = eventService;
        this.applicationName = applicationName;
        this.course = course;
        this.semester = semester;
    }

    @GetMapping("/hello")
    public Map<String, String> hello() {
        return Map.of("message", eventService.getWelcomeMessage());
    }

    @GetMapping("/events")
    public List<EventInfo> getEvents() {
        return eventService.getEvents();
    }

    @GetMapping("/events/{id}")
    public ResponseEntity<?> getEventById(@PathVariable int id) {
        var event = eventService.getEventById(id);
        if (event.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "Event with id " + id + " was not found"));
        }
        return ResponseEntity.ok(event.get());
    }

    @GetMapping("/info")
    public Map<String, Object> getInfo() {
        return Map.of(
                "applicationName", applicationName,
                "course", course,
                "semester", semester
        );
    }
}
