package fr.olivier.formationcda.ecf4.localib.vehicles;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
@CrossOrigin
public class VehicleController {

    private final VehicleService service;

    public VehicleController(VehicleService service) {
        this.service = service;
    }

    /**
     * Récupère tous les véhicules
     *
     * @return la liste de tous les véhicules
     */
    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<Vehicle> findAll() {
        return service.findAll();
    }

    /**
     * Crée un nouveau véhicule
     *
     * @param entity les infos du véhicule à créer
     * @return le véhicule sauvegardé
     */
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Vehicle save(@RequestBody Vehicle entity) {
        return service.save(entity);
    }

    /**
     * Mets à jour les informations d'un véhicule
     *
     * @param vehicle les informations du véhicule à mettre à jour
     * @param id l'id du véhicule à mettre à jour
     * @return le véhicule mis à jour
     */
    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public Vehicle update(@RequestBody Vehicle vehicle, @PathVariable Long id) {
        if ( !id.equals(vehicle.getId())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id non trouvé.");
        }
        return this.service.update(vehicle);
    }

    /**
     * Recherche un véhicule par son id.
     * Si aucun véhicule n'est trouvé, retourne un 404
     *
     * @param id l'id du véhicule à trouver
     * @return le véhicule trouvé
     */
    @GetMapping("{id}")
    @ResponseStatus(code = HttpStatus.FOUND)
    public Vehicle findById(@PathVariable Long id) {
        return service.findById(id);
    }

    /**
     * Supprime un véhicule par son id
     *
     * @param id l'id du véhicule à supprimer
     */
    @DeleteMapping("{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }
}
