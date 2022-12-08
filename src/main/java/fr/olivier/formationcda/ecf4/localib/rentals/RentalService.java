package fr.olivier.formationcda.ecf4.localib.rentals;

import fr.olivier.formationcda.ecf4.localib.users.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class RentalService {

    private Logger logger = LoggerFactory.getLogger(User.class);

    private RentalRepository repository;

    public RentalService(RentalRepository repository) {
        this.repository = repository;
    }

    /**
     * Récupère toutes les locations
     * @return la liste de toutes les locations
     */
    public List<Rental> findAll() {
        return repository.findAll();
    }

    /**
     * Crée une nouvelle location
     * @param entity les infos de la location à créer
     * @return la location sauvegardée
     */
    public Rental save(Rental entity) {
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
}
