package fr.wcs.gowild.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.wcs.gowild.exception.RessourceNotFoundException;
import fr.wcs.gowild.model.Event;
import fr.wcs.gowild.repository.EventRepository;

@RestController
@RequestMapping("/api")
public class EventController {

	@Autowired
	EventRepository eventRepository;

	// CRUD = Read
	@GetMapping("/events")
	public List<Event> getAllEvents() {
		return eventRepository.findAll();
	}

	// CRUD = Read
	@GetMapping("/events/{id}")
	public Event getEventById(@PathVariable(value = "id") Long eventId) {
		return eventRepository.findById(eventId).orElseThrow(() -> new RessourceNotFoundException("Event", "id", eventId));
	}

	// CRUD = Create
	@PostMapping("/events")
	public Event createEvent(@Valid @RequestBody Event event) {
		return eventRepository.save(event);
	}

	// CRUD = Update
	@PutMapping("/events/{id}")
	public Event updateEvent(@PathVariable(value = "id") Long eventId, @RequestBody Event eventDetails) {
		Event event = eventRepository.findById(eventId).orElseThrow(() -> new RessourceNotFoundException("Event", "id", eventId));
		event.setTitle(eventDetails.getTitle());
		event.setDescription(eventDetails.getDescription());
		event.setDate_start(eventDetails.getDate_start());
		event.setDate_end(eventDetails.getDate_end());
		event.setTimetable(eventDetails.getTimetable());
		event.setPricing_info(eventDetails.getPricing_info());
		event.setLink(eventDetails.getLink());
		event.setPlacename(eventDetails.getPlacename());
		event.setAddress(eventDetails.getAddress());

		Event updateEvent = eventRepository.save(event);
		return updateEvent;
	}

	// CRUD = Delete
	@DeleteMapping("/events/{id}")
	public ResponseEntity<?> deleteEvent(@PathVariable(value = "id") Long eventId) {
		Event event = eventRepository.findById(eventId).orElseThrow(() -> new RessourceNotFoundException("Event", "id", eventId));
		eventRepository.delete(event);
		return ResponseEntity.ok().build();
	}

}
