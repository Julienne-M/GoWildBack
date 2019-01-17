package fr.wcs.gowild.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.wcs.gowild.model.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long>{

}
