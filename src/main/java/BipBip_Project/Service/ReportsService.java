package BipBip_Project.Service;


import BipBip_Project.Model.Reports;
import BipBip_Project.Repository.ReportsRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportsService {
    private final ReportsRepo nReportsRepo;

    // @Autowired
    public ReportsService(ReportsRepo nReportsRepo) {
        this.nReportsRepo = nReportsRepo;
    }

    public Reports addReport(Reports report) {
        return nReportsRepo.save(report);
    }

    public Reports updateReport(Reports report) {
        return nReportsRepo.save(report);
    }

    public List<Reports> getAllReports() {
        return nReportsRepo.findAll();
    }

    // Autres méthodes pour gérer les rapports (par exemple, supprimer, obtenir par ID, etc.)
}
