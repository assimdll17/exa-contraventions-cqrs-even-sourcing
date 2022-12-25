package ma.enset.immatriculationservice.query.controllers;

import lombok.AllArgsConstructor;
import ma.enset.commonapi.queries.GetVehicule;
import ma.enset.commonapi.queries.GetVehicules;
import ma.enset.immatriculationservice.query.entities.Owner;
import ma.enset.immatriculationservice.query.entities.Vehicule;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/query/vehicule")
@AllArgsConstructor
@Service
public class VehiculeQueryController {
    private QueryGateway queryGateway;


    @GetMapping(path = "/")
    public List<Vehicule> getVehicules() {
        return queryGateway.query(new GetVehicules(), ResponseTypes.multipleInstancesOf(Vehicule.class)).join();
    }

    @GetMapping(path = "/{id}")
    public Owner getVehicule(@PathVariable String id) {
        return queryGateway.query(new GetVehicule(id), Owner.class).join();
    }


}
