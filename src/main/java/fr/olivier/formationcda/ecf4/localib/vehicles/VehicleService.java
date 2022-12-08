package fr.olivier.formationcda.ecf4.localib.vehicles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class VehicleService {

    private Logger logger = LoggerFactory.getLogger(Vehicle.class);

    private VehicleRepository repository;

    public VehicleService(VehicleRepository repository) {
        this.repository = repository;
    }

    /**
     * Récupère tous les véhicules
     * @return la liste de tous les véhicules
     */
    public List<Vehicle> findAll() {
        return repository.findAll();
    }

    /**
     * Crée un nouveau véhicule
     * @param entity les infos du véhicule à créer
     * @return le véhicule sauvegardé
     */
    public Vehicle save(Vehicle entity) {
        return repository.save(entity);
    }

    /**
     * Met à jour les informations d'un véhicule
     * @param vehicle les informations du véhicule à modifier
     * @return le véhicule modifié
     */
    public Vehicle update(Vehicle vehicle) {
        if (!this.repository.existsById(vehicle.getId())){
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Adresse non trouvée.");
        }
        return this.repository.save(vehicle);
    }

    /**
     * Recherche un véhicule par son id.
     * Si aucun véhicule n'est trouvé, retourne un 404
     * @param id l'id du véhicule à trouver
     * @return le véhicule trouvé
     */
    public Vehicle findById(Long id) {
        return repository.findById(id).orElseThrow(() -> {
            logger.warn("findByIdInvalid: " +id);
            return new ResponseStatusException(HttpStatus.NOT_FOUND);
        });
    }

    /**
     * Supprime un véhicule par son id
     * @param id l'id du véhicule à supprimer
     */
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
