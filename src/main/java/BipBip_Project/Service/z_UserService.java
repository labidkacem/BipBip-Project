package BipBip_Project.Service;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import  BipBip_Project.Model.User;
import  BipBip_Project.Repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class z_UserService {

    @Autowired
    private UserRepository userRepository; 

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id){

        return userRepository.findById(id);
    }

    public boolean authenticate(String email, String password) {
        // Logique d'authentification personnalisée, par exemple :
        User user = userRepository.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return true; // Authentification réussie
        }
        return false; // Authentification échouée
    }

    /*  public User updateUser(Long userId, User updatedUserDetails) throws ResourceNotFoundException {
        // Trouver l'utilisateur existant
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id : " + userId));

        // Mettre à jour les propriétés de l'utilisateur
        existingUser.setFirstName(updatedUserDetails.getFirstName());
        existingUser.setLastName(updatedUserDetails.getLastName());
        existingUser.setEmail(updatedUserDetails.getEmail());
        existingUser.setPhoneNumber(updatedUserDetails.getPhoneNumber());


        return userRepository.save(existingUser);
    }*/

    public User saveOrUpdateUser(User user) {
        return userRepository.save(user);
    }


    @Transactional
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(user);
    }

    

    
}
