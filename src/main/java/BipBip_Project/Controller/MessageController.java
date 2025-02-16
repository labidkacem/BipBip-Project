package BipBip_Project.Controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import BipBip_Project.Model.Message;
import BipBip_Project.Model.Trip;
import BipBip_Project.Model.User;
import BipBip_Project.Repository.MessageRepo;
import BipBip_Project.Repository.TripRepo;
import BipBip_Project.Repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@Controller
public class MessageController {
    @Autowired
    private MessageRepo repo;
    @Autowired
    private TripRepo tripRepo;
    @Autowired
    private UserRepository userRepository;
    
   @RequestMapping("/envoyer/{id}") 
    public String formMessage(@PathVariable Long id, Message msg, Model model, HttpSession session){
        System.out.println("vous allez envoyer le message avec l'utilisateur " + id);
        session.setAttribute("id", id);
        model.addAttribute("msg", msg);
        return "envoiMessage";
    }


    @RequestMapping("/repondre/{id}/{receiverId}")
    public String formReponse(@PathVariable Long id, @PathVariable Long receiverId, Message msg, Model model, HttpSession session){   
        session.setAttribute("id", id);
        session.setAttribute("receiverId", receiverId);
        model.addAttribute("msg", msg);
        return "answerMessage";
    }

    @RequestMapping("/messagerie/{id}")
    public String envoyerMessage(@PathVariable Long id, Message msg, BindingResult result, @RequestParam("content") String contenu, Model model, HttpServletRequest request, HttpSession session){ 
    
        User user;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String username = userDetails.getUsername();
            user = userRepository.findByEmail(username);
            if(user != null ){
                Long userID = user.getuser_ID();
                msg.setSender(user);
                System.out.println("celui qui envoie le message est " + userID);
            }
        }
        Trip trip = tripRepo.findById(id).orElse(new Trip());
        model.addAttribute("trip", trip);   
        Long tripCreatorId = trip.getuser();
        User tripCreator = userRepository.findById(tripCreatorId).orElse(new User());
        msg.setReceiver(tripCreator);
        msg.setContent(contenu);
        msg.setDate(new Date());
        repo.save(msg);
            return "ajout";
    }

    @RequestMapping("/answer/{id}")
    public String repondreMessage(@PathVariable Long id, Message msg, BindingResult result, @RequestParam("content") String contenu, Model model, HttpServletRequest request, HttpSession session){ 
        Long receiverId = (Long) session.getAttribute("receiverId");

        //on recupere la personne a qui on va repondre
        System.out.println(receiverId + "va répondre à " + id);
        User receiver = userRepository.findById(id).orElse(new User());
        //la personne qui va recevoir le message
        User sender = userRepository.findById(receiverId).orElse(new User());
        System.out.println("vous etes dans la methode de reponse");
        System.out.println("et la personne " + sender + " va repondre a la personne " + receiver);
        msg.setSender(sender);
        msg.setReceiver(receiver);
        msg.setDate(new Date());
        repo.save(msg);

        
        return "ajout";
    }


    @RequestMapping("/boiteMessages")
    public String afficherMessages(Model model, HttpSession session){
        System.out.println("affichage de messages");
        List<Message> messages = repo.findAll();
        List<Message> messagess = new ArrayList<>();
        User user ;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String username = userDetails.getUsername();
            user = userRepository.findByEmail(username);
            if(user != null ){
                Long userID = user.getuser_ID();
                for(Message message : messages){
                    if(message.getReceiver().getuser_ID().equals(userID)){
                        System.out.println("c'est votre message");
                        messagess.add(message);
                    } 
                }
            }
            messagess.sort(Comparator.comparing(m -> m.getSender().getuser_ID()));

        }
        
        System.out.println(messages.size());
        model.addAttribute("messages", messagess); 

        return "boiteMessage";
    } 

}
