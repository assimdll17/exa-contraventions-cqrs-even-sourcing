package ma.enset.immatriculationservice.commands.controllers;

import lombok.AllArgsConstructor;
import ma.enset.commonapi.commands.CreateVehiculeCommand;
import ma.enset.commonapi.dtos.CreateVehiculeRequestDTO;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/command/vehicule")
@AllArgsConstructor
@Service
public class VehiculeCommandController {
    private CommandGateway commandGateway;

    @PostMapping(path = "/create")
    public CompletableFuture<String> createVehicule(@RequestBody CreateVehiculeRequestDTO createVehiculeRequestDTO) {
        CompletableFuture<String> response = commandGateway.send(new CreateVehiculeCommand(
                UUID.randomUUID().toString(),
                createVehiculeRequestDTO.getMatricule(),
                createVehiculeRequestDTO.getMarque(),
                createVehiculeRequestDTO.getModele(),
                createVehiculeRequestDTO.getPuissance(),
                createVehiculeRequestDTO.getProprietaire()
        ));
        return response;
    }
}
