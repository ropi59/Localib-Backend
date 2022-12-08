package fr.olivier.formationcda.ecf4.localib.rentals;

import fr.olivier.formationcda.ecf4.localib.users.User;
import fr.olivier.formationcda.ecf4.localib.vehicles.Disponibility;
import fr.olivier.formationcda.ecf4.localib.vehicles.Vehicle;
import fr.olivier.formationcda.ecf4.localib.vehicles.VehicleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class RentalService {

    private Logger logger = LoggerFactory.getLogger(User.class);

    private RentalRepository repository;
    private VehicleService vehicleService;

    public RentalService(RentalRepository repository, VehicleService vehicleService) {
        this.repository = repository;
        this.vehicleService = vehicleService;
    }

    /**
     * Récupère toutes les locations
     * @return la liste de toutes les locations
     */
    public List<Rental> findAll() {
        return repository.findAll();
    }


    /**
     * Enregistre une nouvelle location si les données sont valides
     * @param entity les informations de la location à vérifier
     * @return la location enregistrée
     */
    public Rental save(Rental entity) {
        //vérifie si le véhicule est disponible
        if (!this.checkVehicleDisponibility(this.vehicleService.findById(entity.getVehicleId()))){
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Véhicule non disponible");
        }
        //récupère toutes les locations avec un véhicule particulier
        List<Rental> rentalList = checkRentalByVehicleId(entity.getVehicleId());
        //vérifie si le véhicule est disponible aux dates choisies
        if (!checkDatesDisponibility(entity, rentalList)){
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Le véhicule est déjà en réservé à ces dates.");
        }
        return repository.save(entity);
    }

    /**
     * Met à jour les informations d'une location
     * @param rental les informations de la location à modifier
     * @return la location modifiée
     */
    public Rental update(Rental rental) {
        if (!this.repository.existsById(rental.getId())){
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "location non trouvée.");
        }
        return this.repository.save(rental);
    }

    /**
     * Recherche une location par son id.
     * Si aucune location n'est trouvée, retourne un 404
     * @param id l'id de la location à trouver
     * @return la location trouvée
     */
    public Rental findById(Long id) {
        return repository.findById(id).orElseThrow(() -> {
            logger.warn("findByIdInvalid: " +id);
            return new ResponseStatusException(HttpStatus.NOT_FOUND);
        });
    }

    /**
     * Supprime une location par son id
     * @param id l'id de la location à supprimer
     */
    public void deleteById(Long id) {
        repository.deleteById(id);
    }


    /**
     * Vérifie si un véhicule est disponible à la location
     * @param vehicle le véhicule à tester
     * @return true si le véhicule est disponible, sinon false
     */
    private Boolean checkVehicleDisponibility(Vehicle vehicle){
        if(vehicle.getDisponibility().equals(Disponibility.DISPONIBLE)){
            return true;
        }else {
            return false;
        }
    }

    /**
     * Récupère toutes les locations utilisant un véhicule particulier
     * @param vehicleId l'id du véhicule à vérifier
     * @return une liste de toutes les locations avec un véhicule particulier
     */
    private List<Rental> checkRentalByVehicleId(Long vehicleId) {
        List<Rental> rentalsWithVehicleId = new ArrayList<>();
        for (Rental rental : this.findAll()) {
            if (rental.getVehicleId() == vehicleId) {
                rentalsWithVehicleId.add(rental);
            }
        }
        return rentalsWithVehicleId;
    }

    /**
     * Vérifie si les dates de la location sont disponibles pour un véhicule particulier
     * @param rentalToCheck la location à vérifier.
     * @param rentals la liste des locations existante pour ce véhicule particulier
     * @return true si les dates sont libres.
     */
    private Boolean checkDatesDisponibility(Rental rentalToCheck, List<Rental> rentals){
        Boolean isDatevalid = false;
        for (Rental rental : rentals){
            //si la date de début de location est durant une location existante return false
            if (rentalToCheck.getStartDate().isAfter(rental.getStartDate()) && rentalToCheck.getStartDate().isBefore(rental.getEndDate())){
                isDatevalid = false;
            //si la date de fin de location est durant une location existante return false
            } else if (rentalToCheck.getEndDate().isAfter(rental.getStartDate()) && rentalToCheck.getEndDate().isBefore(rental.getEndDate())){
                isDatevalid = false;
            //si la date de location contient une autre location alors return false
            }else if (rentalToCheck.getStartDate().isBefore(rental.getStartDate()) && rentalToCheck.getEndDate().isAfter(rental.getEndDate())){
                isDatevalid = false;
            }else {
                isDatevalid = true;
            }
        }
        return isDatevalid;
    }


}
