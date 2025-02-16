package BipBip_Project.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import BipBip_Project.Model.Preferences;
import BipBip_Project.Repository.PreferencesRepository;


@Service
public class PreferencesService {
    
        @Autowired
    private PreferencesRepository preferencesRepository;

    public void save(Preferences preferences) {
        System.out.println("save");

        preferencesRepository.save(preferences);
        System.out.println("yes");
    }
    /*private final PreferencesRepository preferencesRepository;
    @Transactional

    public Preferences updateUserPreferences(Long userId, Preferences newPreferences) throws Exception {
        // Vérifier si l'utilisateur existe
        Optional<User> userOptional = UserRepository.findById(userId);
        if (!userOptional.isPresent()) {
            throw new Exception("Utilisateur non trouvé");
        }

        User user = userOptional.get();

        // Chercher les préférences existantes
        Optional<Preferences> existingPreferences = preferencesRepository.findById(user.getUserPreferencesId());

        Preferences preferencesToSave;
        if (existingPreferences.isPresent()) {
            // Si les préférences existent, nous mettons à jour les valeurs
            preferencesToSave = existingPreferences.get();
            // ...
        } else {
            // Si les préférences n'existent pas, nous allons créer une nouvelle entité
            preferencesToSave = new Preferences();
            preferencesToSave.setuser_ID(user);
        }

        // Mettre à jour les valeurs avec les nouvelles préférences
        preferencesToSave.setSmoke(newPreferences.isSmoke());
        preferencesToSave.setAnimals(newPreferences.isAnimals());
        preferencesToSave.setMusic(newPreferences.isMusic());
        preferencesToSave.setDiscussion(newPreferences.isDiscussion());
        preferencesToSave.setfoodandBeverages(newPreferences.isfoodandBeverages());

        // Sauvegarder les préférences mises à jour dans la base de données
        return preferencesRepository.save(preferencesToSave);
    }
    public Preferences createPreferences(Preferences preferences) {
        return null;
    } /*/
    
    
    
}
