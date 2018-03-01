package pl.manka.app.service;


import pl.manka.app.domain.Contact;

public interface ContactService {
    Iterable<Contact> getAllContacts();

    Contact getContactById(Integer id);
    Contact saveContact(Contact contact);
    void deleteContact(Integer id);
}