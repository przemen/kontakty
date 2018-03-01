package pl.manka.app.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.manka.app.domain.Contact;
import pl.manka.app.service.ContactService;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/api/contact")
public class ContactRestController {
    private ContactService contactService;

    @Autowired
    public void setContactService(ContactService contactService) {
        this.contactService = contactService;
    }

 

    @RequestMapping(value = {"/list" })
    public ResponseEntity <Iterable<Contact>> list () {

        Iterable<Contact> contactSet =  this.contactService.getAllContacts();
       return new ResponseEntity<>(contactSet, new HttpHeaders(), HttpStatus.OK);
    }
/*
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addContactForm(Contact contact, Model model) {
        model.addAttribute("activePage", "contacts");
        return "contacts/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addContact(@Valid Contact contact, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("activePage", "contacts");
            return "contacts/add";
        }

        this.contactService.saveContact(contact);
        return "redirect:/contacts";
    }

    @RequestMapping(value = "/view/{id}")
    public String viewContact(@PathVariable Integer id, Model model) {
        model.addAttribute("contact", this.contactService.getContactById(id));
        model.addAttribute("activePage", "contacts");
        model.addAttribute("phoneNumbers",this.contactService.getContactById(id).getNumbers());
        return "contacts/view";
    }



    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public String updateContact(Contact contact) {
        System.out.println("Contact ID: " + contact.getId());
        this.contactService.saveContact(contact);
        return "redirect:/contacts/view/" + contact.getId();
    }

    @RequestMapping(value = "/delete/", method = RequestMethod.DELETE)
    public ResponseEntity<> deleteContact(@RequestParam(name = "id") Integer id) {
        this.contactService.deleteContact(id);

    }

     */


}