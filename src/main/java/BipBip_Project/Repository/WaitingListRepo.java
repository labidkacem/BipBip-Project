package BipBip_Project.Repository;


import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import BipBip_Project.Model.WaitingList;

@Repository
public interface WaitingListRepo extends JpaRepository<WaitingList, Long> {

    WaitingList findByDateAdded(Date Date);
}