package ma.enset.radar.query.service;

import lombok.AllArgsConstructor;
import ma.enset.commonapi.events.RadarCreatedEvent;
import ma.enset.commonapi.queries.FindAllRadars;
import ma.enset.radar.query.entities.Radar;
import ma.enset.radar.query.repositories.RadarRepository;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@AllArgsConstructor
@Transactional
public class RadarServiceHandler {
    private RadarRepository radarRepository;

    @EventHandler
    public void on(RadarCreatedEvent event){
        Radar radar = new Radar();
        radar.setId(event.getId());
        radar.setLatitude(event.getLatitude());
        radar.setLongitude(event.getLongitude());
        radar.setMaxSpeed(event.getMaxSpeed());
        radarRepository.save(radar);
    }

    @QueryHandler
    public List<Radar> on(FindAllRadars query){
        return radarRepository.findAll();
    }
}
