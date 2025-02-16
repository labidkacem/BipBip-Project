/* 
package BipBip_Project.Service;




import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import BipBip_Project.Model.User;

@Service
public class AuthenticationService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
public User register(String firstName, String lastName, String email, String rawPassword, String phoneNumber) {
    // Vérifier d'abord si l'e-mail est déjà enregistré.
    User existingUser = entityManager.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
                                       .setParameter("email", email)
                                       .getResultStream()
                                       .findFirst()
                                       .orElse(null);

    if (existingUser != null) {
        // Si un utilisateur avec cet e-mail existe déjà, nous lançons une exception.
        throw new IllegalArgumentException("Il y a déjà un compte avec l'adresse e-mail fournie.");
    }

   String hashedPassword = BCrypt.hashpw(rawPassword, BCrypt.gensalt());

    User newUser = new User();
    newUser.setFirstName(firstName);
    newUser.setLastName(lastName);
    newUser.setEmail(email);
    newUser.setPassword(hashedPassword);


    newUser.setPhoneNumber(phoneNumber);
    newUser.setStatus(User.UserStatus.ACTIF);

    // Essayez de sauvegarder le nouvel utilisateur.
    try {
        entityManager.persist(newUser);
        entityManager.flush(); // Cela peut aider à attraper les exceptions de contrainte de la base de données.
        return newUser;
    } catch (PersistenceException e) {
        // Cela pourrait être dû à une violation de contrainte comme un e-mail en double.
        throw new IllegalArgumentException("Erreur lors de l'enregistrement de l'utilisateur. Veuillez vérifier les détails fournis.", e);
    }
}
/*
public User login(String email, String password) {
    // Validation basique de l'email
    if (email == null || email.trim().isEmpty()) {
        throw new IllegalArgumentException("L'adresse e-mail ne peut pas être vide.");
    }

    // Recherche de l'utilisateur par e-mail
    User user = entityManager.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
                               .setParameter("email", email)
                               .getResultStream()
                               .findFirst()
                               .orElse(null); 

    if (user == null) {
        // L'utilisateur avec cet e-mail n'existe pas, donc l'authentification échoue.
        throw new SecurityException("Les informations d'identification sont incorrectes.");
    }

    // Vérification du mot de passe
    try {
        if (user.getPassword().equals(password)) {
            // Authentification réussie
            return user;
        } else {
            // Le mot de passe ne correspond pas
            throw new SecurityException("Les informations d'identification sont incorrectes.");
        }
    } catch (IllegalArgumentException e) {
        // Problème avec le salt ou le format du hash
        throw new IllegalStateException("Erreur lors de la vérification du mot de passe.", e);
    }
}

}


*/