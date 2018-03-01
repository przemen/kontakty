package pl.manka.app.repositories;


import org.springframework.data.repository.CrudRepository;
import pl.manka.app.domain.Contact;

public interface ContactRepository extends CrudRepository<Contact, Integer>{
 Contact findContactByAddressContaining(String address);
}