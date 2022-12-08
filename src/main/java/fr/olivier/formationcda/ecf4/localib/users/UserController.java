package fr.olivier.formationcda.ecf4.localib.users;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    /**
     * Récupère tous les clients
     *
     * @return la liste de tous les clients
     */
    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<User> findAll() {
        return service.findAll();
    }

    /**
     * Crée un nouveau client
     *
     * @param entity les infos du client à créer
     * @return le client sauvegardé
     */
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public User save(@RequestBody User entity) {
        return service.save(entity);
    }

    /**
     * Met à jour les informations d'un client
     *
     * @param user les informations du client à modifier
     * @return le client modifié
     */
    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public User update(@RequestBody User user, @PathVariable Long id) {
        if ( !id.equals(user.getId())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id non trouvé.");
        }
        return this.service.update(user);
    }

    /**
     * Recherche un client par son id.
     * Si aucun client n'est trouvé, retourne un 404
     *
     * @param id l'id du client à trouver
     * @return le client trouvé
     */
    @GetMapping("{id}")
    @ResponseStatus(code = HttpStatus.FOUND)
    public User findById(@PathVariable Long id) {
        return service.findById(id);
    }

    /**
     * Supprime un client par son id
     *
     * @param id l'id du véhicule à supprimer
     */
    @DeleteMapping("{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }
}
