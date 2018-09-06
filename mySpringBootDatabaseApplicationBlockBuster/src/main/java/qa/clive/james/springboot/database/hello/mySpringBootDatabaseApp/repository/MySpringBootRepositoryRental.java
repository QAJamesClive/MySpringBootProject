package qa.clive.james.springboot.database.hello.mySpringBootDatabaseApp.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import qa.clive.james.springboot.database.hello.mySpringBootDatabaseApp.model.MySpringBootDataModelRental;



@Repository
public interface MySpringBootRepositoryRental extends JpaRepository<MySpringBootDataModelRental,Long> {
	Page<MySpringBootDataModelRental> findByPersonId(Long personID, Pageable pageable);
	Page<MySpringBootDataModelRental> findByDvdId(Long dvdID, Pageable pageable);

}
