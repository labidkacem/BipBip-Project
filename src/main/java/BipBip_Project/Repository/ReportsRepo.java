package BipBip_Project.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import BipBip_Project.Model.Reports;

@Repository
public interface ReportsRepo extends JpaRepository<Reports, Long> {
    // Vous pouvez ajouter des méthodes de requête personnalisées ici si nécessaire

}
