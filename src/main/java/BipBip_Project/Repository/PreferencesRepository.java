package BipBip_Project.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import BipBip_Project.Model.Preferences;
import BipBip_Project.Model.User;

@Repository
public interface PreferencesRepository extends JpaRepository<Preferences, Long> {
    Preferences findByzUserId(User user);
    void deleteByzUserId(User user);

}
