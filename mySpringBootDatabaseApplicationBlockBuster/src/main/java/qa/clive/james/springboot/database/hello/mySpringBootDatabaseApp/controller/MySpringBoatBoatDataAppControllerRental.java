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
import qa.clive.james.springboot.database.hello.mySpringBootDatabaseApp.model.MySpringBootDataModelPerson;
import qa.clive.james.springboot.database.hello.mySpringBootDatabaseApp.model.MySpringBootDataModelRental;
import qa.clive.james.springboot.database.hello.mySpringBootDatabaseApp.repository.MySpringBootRepositoryDVD;
import qa.clive.james.springboot.database.hello.mySpringBootDatabaseApp.repository.MySpringBootRepositoryPerson;
import qa.clive.james.springboot.database.hello.mySpringBootDatabaseApp.repository.MySpringBootRepositoryRental;
import qa.james.gareth.springboot.database.hello.mySpringBootDatabaseApp.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/rental")
public class MySpringBoatBoatDataAppControllerRental {
	
	@Autowired
	private MySpringBootRepositoryRental repositoryRental;
	
	@Autowired
	private MySpringBootRepositoryPerson repositoryPerson;
	
	@Autowired
	private MySpringBootRepositoryDVD repositoryDvd;
	 
	
	@PostMapping("/person/{personId}/dvd/{dvdId}")	
	public MySpringBootDataModelRental creatComment(@PathVariable (value = "personId") Long personId,
													@PathVariable (value = "dvdId") Long dvdId,
													@Valid @RequestBody MySpringBootDataModelRental rental) {
		MySpringBootDataModelPerson person = repositoryPerson.findById(personId).orElseThrow(()-> new ResourceNotFoundException("Person","id",personId));
		MySpringBootDataModelDVD dvd = repositoryDvd.findById(dvdId).orElseThrow(()-> new ResourceNotFoundException("DVD","id",dvdId));
		rental.setDvdId(dvd);
		rental.setPersonId(person);
		rental.setrentalReturned(false);
		return repositoryRental.save(rental);

	}
	

	@PutMapping("/rental/{rentalId}/dvd/{dvdId}")
	public MySpringBootDataModelRental updateOrder(@PathVariable (value = "rentalId") Long rentalId,
												   @PathVariable (value = "dvdId") Long dvdId,
												   @Valid @RequestBody MySpringBootDataModelRental rental) {
		MySpringBootDataModelDVD dvd = repositoryDvd.findById(dvdId).orElseThrow(()-> new ResourceNotFoundException("DVD","id",dvdId));
		rental.setDvdId(dvd);
		return repositoryRental.save(rental);
	}
	
	@PutMapping("/returned/{rentalId}/dvd/{dvdId}")
	public MySpringBootDataModelRental returnOrder(@PathVariable (value = "rentalId") Long rentalId,
												   @PathVariable (value = "dvdId") Long dvdId,
												   @Valid @RequestBody MySpringBootDataModelRental rental) {
		if(!rental.getRrentalReturned()) {
			rental.setrentalReturned(true);
		}
		return repositoryRental.save(rental);
	}
	
}
