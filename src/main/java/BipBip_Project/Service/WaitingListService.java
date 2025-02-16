package BipBip_Project.Service;

import java.util.Date;
import java.util.List;

import BipBip_Project.Model.WaitingList;
import BipBip_Project.Repository.WaitingListRepo;
public class WaitingListService {
    private final WaitingListRepo WaitingListRepository;

    public WaitingListService(WaitingListRepo WaitingListRepository) {
        this.WaitingListRepository = WaitingListRepository;
    }

    public WaitingList addWaitingList(WaitingList WaitingList){
        return WaitingListRepository.save(WaitingList);
    }

    public List<WaitingList> getAllWaitingList(){
        return WaitingListRepository.findAll();
    }

    public WaitingList getLastAddedWaitingList(){
        List<WaitingList> WaitingList = getAllWaitingList();
        Date lastDate = WaitingList.get(0).getDateAdded();
        for(int i = 1; i < WaitingList.size(); i++){
            if(WaitingList.get(i).getDateAdded().after(lastDate)){
                lastDate = WaitingList.get(i).getDateAdded();
            }
        }
        return WaitingListRepository.findByDateAdded(lastDate);
    }

    public WaitingList removeLastWaitingList(WaitingList WaitingList){
        WaitingListRepository.delete(WaitingList);
        return WaitingList;
    }


}
