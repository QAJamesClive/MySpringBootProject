package qa.clive.james.springboot.database.hello.mySpringBootDatabaseApp.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import qa.clive.james.springboot.database.hello.mySpringBootDatabaseApp.model.MySpringBootDataModelPerson;

@Repository
public interface MySpringBootRepositoryPerson extends JpaRepository<MySpringBootDataModelPerson,Long> {

}
