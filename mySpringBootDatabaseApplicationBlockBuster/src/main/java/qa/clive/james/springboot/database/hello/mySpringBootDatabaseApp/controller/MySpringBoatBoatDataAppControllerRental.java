package qa.clive.james.springboot.database.hello.mySpringBootDatabaseApp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import qa.clive.james.springboot.database.hello.mySpringBootDatabaseApp.model.MySpringBootDataModelDVD;
import qa.clive.james.springboot.database.hello.mySpringBootDatabaseApp.model.MySpringBootDataModelRental;
import qa.clive.james.springboot.database.hello.mySpringBootDatabaseApp.repository.MySpringBootRepositoryPerson;
import qa.clive.james.springboot.database.hello.mySpringBootDatabaseApp.repository.MySpringBootRepositoryRental;
import qa.james.gareth.springboot.database.hello.mySpringBootDatabaseApp.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/api")
public class MySpringBoatBoatDataAppControllerRental {
	
	@Autowired
	private MySpringBootRepositoryRental repositoryRental;
	
	@Autowired
	private MySpringBootRepositoryPerson repositoryPerson;
	 
	
	@PostMapping("/person/{personId}/rental")	
	public MySpringBootDataModelRental creatComment(@PathVariable (value = "personId") Long personId,
													@Valid @RequestBody MySpringBootDataModelRental rental) {
		
		return repositoryPerson.findById(personId).map(mySpringBootDataModelPerson -> {
			rental.setPersonId(mySpringBootDataModelPerson);
			return repositoryRental.save(rental);
		}).orElseThrow(() -> new ResourceNotFoundException("Person","id",rental));
	}
	

	@PutMapping("/person/{personId}}/rental/{rentalId}")
	public MySpringBootDataModelRental updateOrder(@PathVariable (value = "personId") Long personId,
												   @PathVariable (value = "rentalId") Long rentalId, 
												   @Valid @RequestBody MySpringBootDataModelRental rentalDetails) {
		if(!repositoryPerson.existsById(personId)) {
			throw new ResourceNotFoundException("Person","id",rentalDetails);
		}
		return repositoryRental.findById(rentalId).map(rental -> {
			rental.setRentalDuration(rentalDetails.getRentalDuration());
			return repositoryRental.save(rental);
		}).orElseThrow(() -> new ResourceNotFoundException("Rental Id", "id", rentalDetails));
	}
	
	@DeleteMapping("/person/{personID}/rental/{rentalId}")
	public ResponseEntity<?> deleteComment(@PathVariable (value = "personId") Long personId, @PathVariable (value = "rentalId") Long rentalId){
		if(!repositoryPerson.existsById(personId)) {
			throw new ResourceNotFoundException("Person","id",personId);
		}
		return repositoryRental.findById(rentalId).map(rental -> {
			repositoryRental.delete(rental);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException("Rental Id", rentalId.toString(), null));
	}
	
	
	
	
}
