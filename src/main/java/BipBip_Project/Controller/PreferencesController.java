/* 
package BipBip_Project.Controller;


import java.util.List;
import java.util.prefs.Preferences;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.jpa.repository.JpaRepository;



import BipBip_Project.Model.Preferences;
import BipBip_Project.Model.User;
import BipBip_Project.Repository.PreferencesRepository;
import BipBip_Project.Repository.UserRepository;
import BipBip_Project.Service.PreferencesService;

@Controller
@RequestMapping("/preferences")
public class PreferencesController {
@Autowired
private UserRepository userRepository;


@Autowired
private PreferencesRepository preferencesRepository;
@PostMapping
public String submitForm(@RequestParam(name = "smoke", defaultValue = "false") boolean smoke,
                         @RequestParam(name = "animals", defaultValue = "false") boolean animals,
                         @RequestParam(name = "music", defaultValue = "false") boolean music,
                         @RequestParam(name = "discussion", defaultValue = "false") boolean discussion,
                         @RequestParam(name = "foodAndBeverages", defaultValue = "false") boolean foodAndBeverages) {

    // Récupérer l'ID de l'utilisateur actuellement connecté
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String currentUserName = authentication.getName();
    User currentUser = userRepository.findByEmail(currentUserName); 

    if (currentUser != null) {
        // Vérifiez si l'utilisateur a déjà des préférences
        Preferences existingPreferences = preferencesRepository.findByzUserId(currentUser);

        if (existingPreferences == null) {
            existingPreferences = new Preferences();
            existingPreferences.setUserId(currentUser);
        }

        existingPreferences.setSmoke(smoke);
        existingPreferences.setAnimals(animals);
        existingPreferences.setMusic(music);
        existingPreferences.setDiscussion(discussion);
        existingPreferences.setfoodandBeverages(foodAndBeverages);

        preferencesRepository.save(existingPreferences);
    }
    return "ajout";
}

}
*/
package BipBip_Project.Controller;


import org.springframework.web.bind.annotation.RequestMapping;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import BipBip_Project.Model.Preferences;
import BipBip_Project.Model.User;
import BipBip_Project.Repository.PreferencesRepository;
import BipBip_Project.Repository.UserRepository;

@Controller
@RequestMapping("/preferences")
public class PreferencesController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PreferencesRepository preferencesRepository;

    @PostMapping
    public String submitForm(@RequestParam(name = "smoke", defaultValue = "false") boolean smoke,
                             @RequestParam(name = "animals", defaultValue = "false") boolean animals,
                             @RequestParam(name = "music", defaultValue = "false") boolean music,
                             @RequestParam(name = "discussion", defaultValue = "false") boolean discussion,
                             @RequestParam(name = "foodAndBeverages", defaultValue = "false") boolean foodAndBeverages) {

        // Récupérer l'ID de l'utilisateur actuellement connecté
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        User currentUser = userRepository.findByEmail(currentUserName);

        if (currentUser != null) {
            // Vérifiez si l'utilisateur a déjà des préférences
            Preferences existingPreferences = preferencesRepository.findByzUserId(currentUser);
           // findByUserId(currentUser);

            // Si les préférences n'existent pas, créez une nouvelle instance
            if (existingPreferences == null) {
                existingPreferences = new Preferences();
                existingPreferences.setUserId(currentUser);
            }

            // Mise à jour des préférences
            existingPreferences.setSmoke(smoke);
            existingPreferences.setAnimals(animals);
            existingPreferences.setMusic(music);
            existingPreferences.setDiscussion(discussion);
            existingPreferences.setfoodandBeverages(foodAndBeverages);

            // Sauvegarde des préférences
            preferencesRepository.save(existingPreferences);
        }
        
        // Rediriger l'utilisateur ou afficher un message de succès
        return "ajout";  // Change cela pour le nom du template ou la page de succès
    }
}


