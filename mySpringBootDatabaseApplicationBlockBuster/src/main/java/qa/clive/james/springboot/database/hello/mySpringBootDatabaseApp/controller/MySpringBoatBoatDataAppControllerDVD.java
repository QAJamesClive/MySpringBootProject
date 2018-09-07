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

import qa.clive.james.springboot.database.hello.mySpringBootDatabaseApp.model.MySpringBootDataModelDVD;
import qa.clive.james.springboot.database.hello.mySpringBootDatabaseApp.repository.MySpringBootRepositoryDVD;
import qa.james.gareth.springboot.database.hello.mySpringBootDatabaseApp.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/dvd")
public class MySpringBoatBoatDataAppControllerDVD {
	
	@Autowired
	MySpringBootRepositoryDVD myRepository;
	
	@PostMapping("/dvd")
	public MySpringBootDataModelDVD createDvd(@Valid @RequestBody MySpringBootDataModelDVD mSDM) {
		return myRepository.save(mSDM);
	}
	
	@GetMapping("/dvd/{id}")
	public MySpringBootDataModelDVD getDvdbyID(@PathVariable(value = "id")Long dvdID) {
		return myRepository.findById(dvdID).orElseThrow(()-> new ResourceNotFoundException("MySpringBootDataModelDVD","id",dvdID));
	}
	
	@GetMapping("/dvd")
	public List<MySpringBootDataModelDVD> getAllDvd(){
		return myRepository.findAll();
	}
	
	@PutMapping("/dvd/{id}")
	public MySpringBootDataModelDVD updateDvd(@PathVariable(value = "id") Long dvdID, @Valid @RequestBody MySpringBootDataModelDVD dvdDetails) {
		
		MySpringBootDataModelDVD mSDM = myRepository.findById(dvdID).orElseThrow(()-> new ResourceNotFoundException("Dvd","id",dvdID));
		mSDM.setName(dvdDetails.getName());
		mSDM.setDescription(dvdDetails.getDescription());
		mSDM.setPrice(dvdDetails.getPrice());
		
		MySpringBootDataModelDVD updateData = myRepository.save(mSDM);
		return updateData;
	}
	
	@DeleteMapping("/dvd/{id}")
	public ResponseEntity<?> deleteDvd(@PathVariable(value = "id")Long dvdID){
		MySpringBootDataModelDVD mSDM = myRepository.findById(dvdID).orElseThrow(()-> new ResourceNotFoundException("Dvd","id",dvdID));
		
		myRepository.delete(mSDM);
		return ResponseEntity.ok().build();
	}
	
	
	
}
