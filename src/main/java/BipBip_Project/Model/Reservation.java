package BipBip_Project.Model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;

import java.util.Date;



	@Entity
	@Table(name = "Reservation")

	public class Reservation {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "Reservation_ID")
	    private Long reservation_ID;

	    @Temporal(TemporalType.TIMESTAMP)
        @Column(name = "ReservationDate")
	    private Date  reservationDate;

		public void reservationDate(Date reservationDate) {
			this.reservationDate = reservationDate;
		}
		@Column(name = "Paymentsituation")
	    private boolean paymentsituation;

		@Column(name = "NumberOfReservation")
		private int numberOfReservation;

		@Column(name= "Status")
		@Enumerated(EnumType.STRING)
		private ReservationStatus status;

		@Enumerated(EnumType.STRING)
    	@Column(name = "ServiceType")
    	private ServiceType ServiceType;

		public enum ServiceType{
			PARCEL,
			DAILY,
			NORMAL
		}

		@ManyToOne
		@JoinColumn(name = "trip_ID")
		private Trip tripId;

		//reservee: the persone who make the reservation
		@ManyToOne
		@JoinColumn(name = "reservee")		
		private User reservee;

		@Column(name = "rating", nullable = true)
		private int rating;
		// Constructeurs

		public Reservation() 
        {
	    }

	    public Reservation( Date  reservationDate, int numberOfReservation, boolean paymentsituation , ReservationStatus status ) {
	        
	        this.reservationDate = reservationDate;
			this.paymentsituation=paymentsituation;
	        
	        this.numberOfReservation = numberOfReservation;
			this.status = status;
	    }

	    // Getters et setters
	    public Long getReservation_ID () {
	        return reservation_ID;
	    }

	    public void setreservation_ID (Long reservation_ID) {
	        this.reservation_ID = reservation_ID;
	    }

        public Date  getReservationDate () {
	        return reservationDate;
	    }

	    public void setreservationDate (Date  reservationDate) {
	        this.reservationDate = reservationDate;
	    }
		
		public boolean ispaymentsituation() {
			return paymentsituation;
		}
		public void setpaymentsituation(boolean paymentsituation) {
			this.paymentsituation = paymentsituation;
		}
        
      
        public int getNumberOfReservation () {
	        return numberOfReservation;
	    }

	    public void setNumberOfReservation (int numberOfReservation) {
	        this.numberOfReservation = numberOfReservation;
	    }

		public void setServiceType(ServiceType ServiceType) {
			this.ServiceType = ServiceType;
		}

		public ServiceType getServiceType() {
			return ServiceType;
		}

		public Trip getTripId() {
			return tripId;
		}

		public void setTripId(Trip tripId) {
			this.tripId = tripId;
		}

		// public ReservationStatus getl_status(){
		// 	return l_status;
		// }

		public ReservationStatus getstatus(){
			return status;
		}
		public void setstatus(ReservationStatus status){
			this.status = status;
		}

		public Long getreservation_ID() {
			return reservation_ID;
		}

		public Date getreservationDate() {
			return reservationDate;
		}

		public int getnumberOfReservation() {
			return numberOfReservation;
		}

		public User getReservee() {
			return reservee;
		}
		
		public void setnumberOfReservation(int numberOfReservation) {
			this.numberOfReservation = numberOfReservation;
		}

		public void setReservee(User reservee) {
			this.reservee = reservee;
		}

		public void setRating(int rating) {
			this.rating = rating;
		}

		public int getRating() {
			return rating;
		}
}