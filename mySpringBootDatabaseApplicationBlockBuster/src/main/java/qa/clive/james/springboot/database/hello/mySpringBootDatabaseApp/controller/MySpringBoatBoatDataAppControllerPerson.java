package qa.clive.james.springboot.database.hello.mySpringBootDatabaseApp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import qa.clive.james.springboot.database.hello.mySpringBootDatabaseApp.model.MySpringBootDataModelPerson;
import qa.clive.james.springboot.database.hello.mySpringBootDatabaseApp.repository.MySpringBootRepositoryPerson;
import qa.james.gareth.springboot.database.hello.mySpringBootDatabaseApp.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/person")
public class MySpringBoatBoatDataAppControllerPerson {
	
	@Autowired
	MySpringBootRepositoryPerson myRepository;
	
	@PostMapping("/person")
	public MySpringBootDataModelPerson createPerson(@Valid @RequestBody MySpringBootDataModelPerson mSDM) {
		return myRepository.save(mSDM);
	}
	
	@GetMapping("/person/{id}")
	public MySpringBootDataModelPerson getPeronbyID(@PathVariable(value = "id")Long personID) {
		return myRepository.findById(personID).orElseThrow(()-> new ResourceNotFoundException("MySpringBootDataModel","id",personID));
	}
	
	@GetMapping("/person")
	public List<MySpringBootDataModelPerson> getAllPeople(){
		return myRepository.findAll();
	}
	
	@PutMapping("/person/{id}")
	public MySpringBootDataModelPerson updatePerson(@PathVariable(value = "id") Long personID, @Valid @RequestBody MySpringBootDataModelPerson personDetails) {
		
		MySpringBootDataModelPerson mSDM = myRepository.findById(personID).orElseThrow(()-> new ResourceNotFoundException("Person","id",personID));
		mSDM.setName(personDetails.getName());
		mSDM.setAddress(personDetails.getAddress());
		mSDM.setAge(personDetails.getAge());
		
		MySpringBootDataModelPerson updateData = myRepository.save(mSDM);
		return updateData;
	}
	
	@DeleteMapping("/person/{id}")
	public ResponseEntity<?> deletePerson(@PathVariable(value = "id")Long personID){
		MySpringBootDataModelPerson mSDM = myRepository.findById(personID).orElseThrow(()-> new ResourceNotFoundException("Person","id",personID));
		
		myRepository.delete(mSDM);
		return ResponseEntity.ok().build();
	}
	
	
	
}
