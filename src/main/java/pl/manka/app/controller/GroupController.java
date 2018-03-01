package pl.manka.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.manka.app.domain.Contact;
import pl.manka.app.domain.Group;
import pl.manka.app.service.ContactService;
import pl.manka.app.service.GroupService;

import javax.validation.Valid;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


@Controller
@RequestMapping("/groups")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @Autowired
    ContactService contactService;

    @RequestMapping(value = { "", "/" })
    public String index(Model model){
        model.addAttribute("activePage", "groups");
        model.addAttribute("groups", groupService.getAllGroups());
        return "groups/index";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addContactForm(Group group, Model model) {
        model.addAttribute("activePage", "contacts");
        return "groups/add";
    }


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addContact(@Valid Group group, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("activePage", "groups");
            return "groups/add";
        }

        this.groupService.saveGroup(group);
        return "redirect:/groups";
    }

    @RequestMapping(value = "/view/{id}")
    public String viewContact(@PathVariable Integer id, Model model) {
        Group group = this.groupService.getGroupById(id);

        model.addAttribute("group", group );
        model.addAttribute("contacts",group.getContacts());
        model.addAttribute("activePage", "group");
        return "groups/view";
    }

    @RequestMapping(value = "/edit/{id}")
    public String editContact(@PathVariable Integer id, Model model) {
        model.addAttribute("group", this.groupService.getGroupById(id));
        model.addAttribute("activePage", "groups");
        return "groups/edit";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateContact(Group group){
        this.groupService.saveGroup(group);
        return "redirect:/groups/view/" + group.getId();
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteView(@PathVariable Integer id, Model model) {
        model.addAttribute("group", this.groupService.getGroupById(id));
        return "groups/delete";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(@RequestParam("id") Integer id, Model model) {
        this.groupService.deleteGroup(id);
        model.addAttribute("activePage", "group");
        return "redirect:/groups";
    }

    @RequestMapping(value = "/addcontacts/{id}", method = RequestMethod.GET)
    public String addContactToGroup(@PathVariable(name = "id") Integer id, Model model){
        Iterable<Contact> allContacts = this.contactService.getAllContacts();
        Group group = this.groupService.getGroupById(id);

        Set<Object[]> objects = new HashSet<>();

        allContacts.forEach(contact -> objects.add(new Object[]{contact,
                group.getContacts().contains(contact) ? true : false}));
        model.addAttribute("contacts", objects);
        model.addAttribute("group", group);
        return "groups/addContats";
    }

    @RequestMapping(value = "/addcontacts/{id}", method = RequestMethod.POST)
    public String addContactToGroup(@PathVariable(name = "id") Integer id,
                                    @RequestParam(name = "contacts[]", required = false) Integer[] contatscIDs){
        Group group = groupService.getGroupById(id);
        Set<Contact> contacts= new HashSet<>();
       try {
            for (int i = 0; i < contatscIDs.length; i++) {
                Contact contact = contactService.getContactById(contatscIDs[i]);
                contacts.add(contact);
            }
            group.setContacts(contacts);
       }
         catch(NullPointerException ex){
           group.setContacts(new HashSet<>());

        }finally {
           groupService.saveGroup(group);
        }
        return "redirect:/groups/view/"+id;
    }


}
