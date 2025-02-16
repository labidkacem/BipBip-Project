package BipBip_Project.Model;


import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Enumerated;


import java.util.Date;

@Entity
@Table(name = "Reports")

public class Reports {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ReportId")
    private Long reportId;

    @Column(name = "ReportingUserName")
    private String reportingUserName;

    @Column(name = "ReportedUserName")
    private String  reportedUserName;

    @Column(name = "ReportType")
    private int   reportType; // 1: trip, 2: TripDaily, 3: Parcel

    @Column(name = "AfterServiceId")
    private Long   afterServiceId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DateReported")
    private Date   dateReported;

    

    @Column(name = "Status")
    @Enumerated(EnumType.STRING)
    private ReportStatus   status;

    // Constructeurs, getters et setters
    // ...

    public  Reports() {
        // Constructeur par défaut
    }

    public   Reports(String reportingUserName, String reportedUserName, int reportType, Long afterServiceId, Date   dateReported, ReportStatus   status) {
        this.reportingUserName = reportingUserName;
        this.reportedUserName = reportedUserName;
        this.reportType = reportType;
        this.afterServiceId = afterServiceId;
        this.dateReported = dateReported;
        this.status = status;
    }

    public Long getreportId() {
        return  reportId;
    }

    public void setreportId(Long  reportId) {
        this.reportId = reportId;
    }

    public String getreportingUserName() {
        return reportingUserName;
    }

    public void setreportingUserName(String   reportingUserName) {
        this.reportingUserName =reportingUserName;
    }

    public String getreportedUserName() {
        return reportedUserName;
    }

    public void setreportedUserName(String reportedUserName) {
        this.reportedUserName = reportedUserName;
    }

    public int getreportType() {
        return reportType;
    }

    public void setreportType(int reportType) {
        this.reportType = reportType;
    }

    public Long getafterServiceId() {
        return afterServiceId;
    }

    public void setafterServiceId(Long afterServiceId) {
        this.afterServiceId = afterServiceId;
    }

    public Date getdateReported() {
        return dateReported;
    }

    public void setdateReported(Date dateReported) {
        this.dateReported = dateReported;
    }
    // Getters et setters
    // ...
    public ReportStatus getstatus() {
        return status;
    }

    public void setstatus(ReportStatus status) {
        this.status = status;
    }
    

}
