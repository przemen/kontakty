package pl.manka.app.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.manka.app.domain.Group;

public interface GroupRepository extends CrudRepository<Group, Integer>{
}
