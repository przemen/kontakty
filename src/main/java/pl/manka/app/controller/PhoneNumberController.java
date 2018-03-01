package pl.manka.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.manka.app.domain.Contact;
import pl.manka.app.domain.PhoneNumber;
import pl.manka.app.domain.Type;
import pl.manka.app.repositories.ContactRepository;
import pl.manka.app.repositories.PhoneNumberRepository;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/numbers")
public class PhoneNumberController {

    @Autowired
    private PhoneNumberRepository phoneNumberRepository;

    @Autowired
    private ContactRepository contactRepository;

    @RequestMapping(value = "/add/{id}", method = RequestMethod.GET)
    public String addNumber(@PathVariable(name = "id") Integer id, Model model){

        Set<String> types = new HashSet<>();
        types.add(Type.GŁÓWNY.toString());
        types.add(Type.KOMÓRKA.toString());
        types.add(Type.DOMOWY.toString());
        types.add(Type.INNY.toString());

        model.addAttribute("types",types);
        model.addAttribute("id", id);
        return "numbers/add";
    }


    @RequestMapping(value = "/add/{id}", method = RequestMethod.POST)
    public String addNumber(@PathVariable(name = "id") Integer id,
                                    @RequestParam(name = "number") String number,
                                    @RequestParam(name = "type") Type type,
                                    Model model) {

        PhoneNumber phoneNumber = new PhoneNumber();
        phoneNumber.setNumber(number);
        phoneNumber.setType(type);
        phoneNumber.setContact(this.contactRepository.findOne(id));
        this.phoneNumberRepository.save(phoneNumber);
        return "redirect:/contacts/view/" + id;
    }


    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editNumber(@PathVariable(name = "id") Integer id, Model model){

        Set<String> types = new HashSet<>();
        types.add(Type.GŁÓWNY.toString());
        types.add(Type.KOMÓRKA.toString());
        types.add(Type.DOMOWY.toString());
        types.add(Type.INNY.toString());

        PhoneNumber phoneNumber = phoneNumberRepository.findOne(id);
        model.addAttribute("types",types);
        model.addAttribute("number", phoneNumber);
        return "numbers/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editNumber(@RequestParam(name = "number") String number,
                            @RequestParam(name = "type") Type type,
                             @RequestParam(name = "id") Integer id,
                            Model model) {

        PhoneNumber phoneNumber = phoneNumberRepository.findOne(id);
        phoneNumber.setNumber(number);
        phoneNumber.setType(type);

        this.phoneNumberRepository.save(phoneNumber);
        return "redirect:/contacts/view/" + phoneNumber.getContact().getId();
    }



    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteNumber(@PathVariable(name = "id") Integer id, Model model){
        PhoneNumber phoneNumber = phoneNumberRepository.findOne(id);
        model.addAttribute("number", phoneNumber);
        return "numbers/delete";
    }


    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deleteNumber(@RequestParam(name = "id") Integer id){
        PhoneNumber phoneNumber = phoneNumberRepository.findOne(id);
        phoneNumberRepository.delete(phoneNumber);
        return "redirect:/contacts/view/"+phoneNumber.getContact().getId();
    }






}
