
package BipBip_Project.Controller;



import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties.Http;
//import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import BipBip_Project.Model.CarImages;
import BipBip_Project.Model.Parcel;
import BipBip_Project.Model.Trip;
import BipBip_Project.Repository.ParcelRepository;
import BipBip_Project.Service.ParcelService;

import BipBip_Project.Model.Parcel.Statut;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/parcels")
public class ParcelController {

    private final ParcelService ParcelService;
    List<String> imagePaths = new ArrayList<>();
    private static String uploadDirectory = "src/main/resources/static/parcel/";

    @Autowired
    public ParcelController(ParcelService ParcelService) {
        this.ParcelService = ParcelService;
    }

    @GetMapping("/addParcel")
    public String searchParcels(Parcel Parcel, Model model) {
        return "searchParcel";
    }
/* 
    @PostMapping("/addParcel")
    public String addParcel(Parcel Parcel, Model model, HttpSession session, @RequestParam("departureCity") String departureCity, @RequestParam("destinationCity") String destinationCity, @RequestParam("travelDate") String travelDate) throws ParseException {
        MultipartFile[] photos = Parcel.getPhotos();
        List<Trip> matchingTrips = new ArrayList<>();
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(travelDate);
        for (MultipartFile file : photos) {
            if (file != null && !file.isEmpty()) {
                try {
                    // Générez un nom de fichier unique
                    String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
                    // Enregistrez le fichier dans le répertoire de ressources
                    Files.write(Paths.get(uploadDirectory, fileName), file.getBytes());
                    // Ajoutez le chemin du fichier à la liste
                    String imagePath = "/static/parcel/" + fileName; 
                    imagePaths.add(imagePath);
                } catch (IOException e) {
                  //e.printStacTrace();
                }
            }
        }
        Parcel.setUserID((Long) session.getAttribute("userID"));
        Parcel.setStatut(Statut.SEARCHING);
        Parcel.setImagePaths(imagePaths);
        ParcelService.saveParcel(Parcel);
        matchingTrips = ParcelService.getSearchResults(departureCity, destinationCity, date, Parcel);
        model.addAttribute("matchingTrips", matchingTrips);
        return "searchParcel";
    }

    
}

*/

@PostMapping("/addParcel")
public String addParcel(Parcel parcel, Model model, HttpSession session, @RequestParam("departureCity") String departureCity, @RequestParam("destinationCity") String destinationCity, @RequestParam("travelDate") String travelDate) throws ParseException {
    MultipartFile[] photos = parcel.getPhotos();
    List<Trip> matchingTrips = new ArrayList<>();
    Date date = new SimpleDateFormat("yyyy-MM-dd").parse(travelDate);
    
    for (MultipartFile file : photos) {
        if (file != null && !file.isEmpty()) {
            try {
                // Générer un nom de fichier unique
                String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
                // Enregistrer le fichier
                Files.write(Paths.get(uploadDirectory, fileName), file.getBytes());
                // Ajouter le chemin du fichier à la liste
                String imagePath = "/static/parcel/" + fileName;
                imagePaths.add(imagePath);
            } catch (IOException e) {
                // Logguer l'erreur et ajouter un message d'erreur
                e.printStackTrace();
                model.addAttribute("errorMessage", "Erreur lors du téléchargement de l'image : " + file.getOriginalFilename());
                return "searchParcel";
            }
        }
    }

    parcel.setUserID((Long) session.getAttribute("userID"));
    parcel.setStatut(Statut.SEARCHING);
    parcel.setImagePaths(imagePaths);

    // Sauvegarder le colis
    ParcelService.saveParcel(parcel);

    // Recherche des trajets
    matchingTrips = ParcelService.getSearchResults(departureCity, destinationCity, date, parcel);
    model.addAttribute("matchingTrips", matchingTrips);
    return "searchParcel";
}
}