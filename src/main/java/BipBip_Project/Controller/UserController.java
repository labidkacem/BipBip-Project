/*
package BipBip_Project.Controller;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

//import BipBip_Project.Model.User;
import BipBip_Project.Repository.PreferencesRepository;
import BipBip_Project.Repository.UserRepository;

import BipBip_Project.Service.UserService;
import com.github.javafaker.Faker;

//import BipBip_Project.service.AuthenticationService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;



@Controller
@RequestMapping("/")
public class UserController {
    
    @Inject
    private UserService userService;
    @Inject 
    private UserRepository userRepository;
    @Inject 
    private PreferencesRepository preferenceRepository ;







    @GetMapping("/")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

  


  
    @PutMapping("/{id}")
   public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long userId,
                                             @Valid @RequestBody User userDetails) {
        try {
            User updatedUser = userService.updateUser(userId, userDetails);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }



    @GetMapping("/")
    public String showForm(User user) { 
        return "form"; 
    }

    @PostMapping("/")
    public String submitForm(User user) {
        userService.setComputingDerivedPassword(user, user.getPassword());
        userRepository.save(user);
        return "ajout";}




    @GetMapping("/login")
    public String showLogin(@ModelAttribute("User") User User) {
        //un utilisateur pour accéder pendant le développement
        /*User Admin = new User();
        Admin.setEmail("a");
        Admin.setPassword("a");
        userService.setComputingDerivedPassword(Admin, Admin.getPassword());
        userRepository.save(Admin);(ici _>)
        
        return "login";
    }



    
    
    @GetMapping("/home")
    public String redirectIdHomePage(HttpSession session) {
        Long userID ;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String username = userDetails.getUsername();
            User user = userRepository.findByEmail(username);
            if(user != null ){
                userID = user.getuser_ID(); 
                session.setAttribute("userID", userID);
                return "redirect:/home/" + userID ;
            }
        }
        return "redirect:/login";
    }
    @GetMapping("/home/{userId}")
    public String showHomePage(@PathVariable Long userId) {
        if(userRepository.findById(userId).isPresent()){
            Long userID ;
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof UserDetails) {
                UserDetails userDetails = (UserDetails) authentication.getPrincipal();
                String username = userDetails.getUsername();
                User user = userRepository.findByEmail(username);
                if(user != null ){
                    userID = user.getuser_ID();
                    if(userID == userId){
                        return "home";
                    }
                    else{
                        return "redirect:/home/" + userID ;
                    }
                }
                
            }
        }
        return "redirect:/login";
    }

    @GetMapping("/preferences")
    public String showpreferencesPage() {
        return "preferences"; 
    }
    @GetMapping("/profile")
    public String showProfilPage() {
        return "profile";  
    }
   
    @Transactional

    @PostMapping("/deleteAccount")
public String deleteAccount() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof UserDetails) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        User user = userRepository.findByEmail(username);
    if (user != null) {
            preferenceRepository.deleteByzUserId(user);
            userRepository.delete(user);
        }

    }
    return "redirect:/";  
}

@GetMapping("/editProfile")
public String getEditProfile(Model model) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof UserDetails) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userRepository.findByEmail(userDetails.getUsername());
        if (user != null) {
            model.addAttribute("user", user);
            return "editProfile";
        }
    }
    return "redirect:/login";
}
@PostMapping("/editProfile")
public String handleEditProfile(@ModelAttribute("user") User updatedUser) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof UserDetails) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User currentUser = userRepository.findByEmail(userDetails.getUsername());
        if (currentUser != null) {
            currentUser.setFirstName(updatedUser.getFirstName());
            currentUser.setLastName(updatedUser.getlastName());
            currentUser.setEmail(updatedUser.getEmail());
            currentUser.setPhoneNumber(updatedUser.getPhoneNumber());

            userRepository.save(currentUser);
            return "redirect:/home";
        }
    }
    return "redirect:/login";
}




// Add Users randomly to test the application **************************************
    private static final List<String> FIRST_NAMES = Arrays.asList("John", "Jane", "Alice", "Bob", "Charlie", "David", "Eva", "Frank");
    private static final List<String> LAST_NAMES = Arrays.asList("Doe", "Smith", "Johnson", "Brown", "Wilson", "Lee", "Taylor", "Davis");

    @GetMapping("/addUsers")
    public String addUsers() {
        int numberOfUsers = 1000; // Spécifie le nombre d'utilisateurs que tu veux générer

        for (int i = 0; i < numberOfUsers; i++) {
            User user = new User();
            user.setFirstName(getRandomElement(FIRST_NAMES));
            user.setLastName(getRandomElement(LAST_NAMES));
            user.setEmail(generateRandomEmail(user.getFirstName(), user.getLastName()));
            user.setdateInscription(generateRandomDateInPast());
            user.setPassword(generateRandomPassword(8));
            user.setPhoneNumber(generateRandomPhoneNumber());
            user.setStatus(User.UserStatus.ACTIF);
            userService.setComputingDerivedPassword(user, user.getPassword());
            userRepository.save(user);
        }
        return "ajout";
    }

    // Fonction pour obtenir un élément aléatoire à partir d'une liste
    private String getRandomElement(List<String> list) {
        Random random = new Random();
        return list.get(random.nextInt(list.size()));
    }

    // Fonction pour générer une adresse e-mail aléatoire
    private String generateRandomEmail(String firstName, String lastName) {
        String random = generateRandomPassword(5);
        return firstName.toLowerCase() + "." + lastName.toLowerCase() + "." + random + "@example.com";
    }

    // Fonction pour générer une date d'inscription aléatoire dans le passé
    private Date generateRandomDateInPast() {
        Random random = new Random();
        long millisInPast = System.currentTimeMillis() - random.nextInt(365) * 24 * 60 * 60 * 1000L;
        return new Date(millisInPast);
    }

    // Fonction pour générer un mot de passe aléatoire avec des chiffres, majuscules et minuscules
    private String generateRandomPassword(int n) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < n; i++) {
            int index = random.nextInt(characters.length());
            password.append(characters.charAt(index));
        }

        return password.toString();
    }

    // Fonction pour générer un numéro de téléphone aléatoire
    private String generateRandomPhoneNumber() {
        Random random = new Random();
        StringBuilder phoneNumber = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            int digit = random.nextInt(10);
            phoneNumber.append(digit);
        }

        return phoneNumber.toString();
    }
}

*/