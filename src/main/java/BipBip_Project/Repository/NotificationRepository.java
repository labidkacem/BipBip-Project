package BipBip_Project.Repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import BipBip_Project.Model.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
}