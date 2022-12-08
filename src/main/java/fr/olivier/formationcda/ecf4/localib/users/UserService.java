package fr.olivier.formationcda.ecf4.localib.users;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserService {

    private Logger logger = LoggerFactory.getLogger(User.class);

    private UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    /**
     * Récupère tous les clients
     * @return la liste de tous les clients
     */
    public List<User> findAll() {
        return repository.findAll();
    }

    /**
     * Crée un nouveau client
     * @param entity les infos du client à créer
     * @return le client sauvegardé
     */
    public User save(User entity) {
        return repository.save(entity);
    }

    /**
     * Met à jour les informations d'un client
     * @param user les informations du client à modifier
     * @return le client modifié
     */
    public User update(User user) {
        if (!this.repository.existsById(user.getId())){
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "client non trouvé.");
        }
        return this.repository.save(user);
    }

    /**
     * Recherche un client par son id.
     * Si aucun client n'est trouvé, retourne un 404
     * @param id l'id du client à trouver
     * @return le client trouvé
     */
    public User findById(Long id) {
        return repository.findById(id).orElseThrow(() -> {
            logger.warn("findByIdInvalid: " +id);
            return new ResponseStatusException(HttpStatus.NOT_FOUND);
        });
    }

    /**
     * Supprime un client par son id
     * @param id l'id du client à supprimer
     */
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
