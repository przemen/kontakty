package pl.manka.app.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.manka.app.domain.PhoneNumber;

public interface PhoneNumberRepository extends CrudRepository<PhoneNumber, Integer> {
}
