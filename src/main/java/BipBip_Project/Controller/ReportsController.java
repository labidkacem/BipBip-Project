package BipBip_Project.Controller;



import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import BipBip_Project.Model.ReportStatus;
import BipBip_Project.Model.Reports;
import BipBip_Project.Repository.ReportsRepo;

import lombok.Data;

@Data
@Controller

public class ReportsController {
    @Autowired
    ReportsRepo ReportsRepo;
    

    @GetMapping("/report")
    public String showReportForm(Model model) {
        // Ici, vous pouvez initialiser les données du modèle si nécessaire
        // par exemple, vous pouvez obtenir une liste d'utilisateurs ou de types de rapports
        model.addAttribute("report", new Reports());
        return "report-form"; // Le nom du modèle HTML pour le formulaire de rapport
    }

    @PostMapping("/report")
    public String submitReport(@ModelAttribute("report") Reports report) {
        // Traitez le rapport ici (enregistrez-le dans la base de données ou effectuez d'autres actions nécessaires)
        // Utilisez un service pour effectuer la logique de traitement
        report.setdateReported(new Date());
        
        report.setstatus(ReportStatus.PENDING);
        ReportsRepo.save(report);
        return "confirmation"; // Le nom du modèle HTML pour la confirmation
    
    }

}
