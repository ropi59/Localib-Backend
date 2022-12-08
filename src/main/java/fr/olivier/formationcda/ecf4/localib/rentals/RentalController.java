package fr.olivier.formationcda.ecf4.localib.rentals;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/rentals")
@CrossOrigin
public class RentalController {

    private final RentalService service;

    public RentalController(RentalService service) {
        this.service = service;
    }

    /**
     * Récupère toutes les locations
     *
     * @return la liste de toutes les locations
     */
    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<Rental> findAll() {
        return service.findAll();
    }

    /**
     * Crée une nouvelle location
     *
     * @param entity les infos de la location à créer
     * @return la location sauvegardée
     */
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Rental save(@RequestBody Rental entity) {
        return service.save(entity);
    }

    /**
     * Met à jour les informations d'une location
     *
     * @param rental les informations de la location à modifier
     * @return la location modifiée
     */
    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public Rental update(@RequestBody Rental rental, @PathVariable Long id) {
        if ( !id.equals(rental.getId())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id non trouvé.");
        }
        return this.service.update(rental);
    }

    /**
     * Recherche une location par son id.
     * Si aucune location n'est trouvée, retourne un 404
     *
     * @param id l'id de la location à trouver
     * @return la location trouvée
     */
    @GetMapping("{id}")
    @ResponseStatus(code = HttpStatus.FOUND)
    public Rental findById(@PathVariable Long id) {
        return service.findById(id);
    }

    /**
     * Supprime une location par son id
     *
     * @param id l'id de la location à supprimer
     */
    @DeleteMapping("{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }
}
