package BipBip_Project.Repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import BipBip_Project.Model.Message;

@Repository
public interface MessageRepo extends JpaRepository<Message, Long> {
    public Message findById(long id);
}
