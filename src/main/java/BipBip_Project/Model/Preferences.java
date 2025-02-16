package BipBip_Project.Model;



import jakarta.persistence.*;




@Entity
@Table(name = "Preferences")
public class Preferences{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long UserPreferencesId;

    @OneToOne
    @JoinColumn(name = "user_ID", nullable = false)
    private User UserId ; 

    public User getUserId() {
        return UserId;
    }
    public void setUserId(User UserId) {
        this.UserId = UserId;
    }
    private boolean Smoke ;
    private boolean Animals ;
    private boolean Music ;
    private boolean Discussion ;
    private boolean foodandBeverages;


    public Long getUserPreferencesId() {
        return UserPreferencesId;
    }
    public void setUserPreferencesId(Long UserPreferencesId) {
        this.UserPreferencesId = UserPreferencesId;
    }
   
    public boolean isSmoke() {
        return Smoke;
    }
    public void setSmoke(boolean Smoke) {
        this.Smoke = Smoke;
    }
    public boolean isAnimals() {
        return Animals;
    }
    public void setAnimals(boolean Animals) {
        this.Animals = Animals;
    }
    public boolean isMusic() {
        return Music;
    }
    public void setMusic(boolean Music) {
        this.Music = Music;
    }
    public boolean isDiscussion() {
        return Discussion;
    }
    public void setDiscussion(boolean Discussion) {
        this.Discussion =Discussion;
    }
    public boolean isfoodandBeverages() {
        return foodandBeverages;
    }
    public void setfoodandBeverages(boolean foodandBeverages) {
        this.foodandBeverages = foodandBeverages;
    }

}