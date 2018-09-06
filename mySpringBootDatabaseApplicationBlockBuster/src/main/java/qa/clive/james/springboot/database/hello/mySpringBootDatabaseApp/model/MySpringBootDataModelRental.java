package qa.clive.james.springboot.database.hello.mySpringBootDatabaseApp.model;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name ="rental")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"creationDate"},allowGetters = true)
public class MySpringBootDataModelRental {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "dvdId", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private MySpringBootDataModelDVD dvdId;
	
	@NotNull
	private int rentalDuration;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "personId", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private MySpringBootDataModelPerson personId;
		
	@Column(nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date rentedDate;

	public MySpringBootDataModelPerson getPersonId() {
		return personId;
	}

	public void setPersonId(MySpringBootDataModelPerson personId) {
		this.personId = personId;
	}
	
	public MySpringBootDataModelDVD getDvdId() {
		return dvdId;
	}

	public void setDvdId(MySpringBootDataModelDVD dvdId) {
		this.dvdId = dvdId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public Date getRentedDate() {
		return rentedDate;
	}

	public void setRentedDate(Date rentedDate) {
		this.rentedDate = rentedDate;
	}

	public int getRentalDuration() {
		return rentalDuration;
	}

	public void setRentalDuration(int rentalDuration) {
		this.rentalDuration = rentalDuration;
	}
	
}
