package pl.manka.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.manka.app.domain.PhoneNumber;
import pl.manka.app.repositories.PhoneNumberRepository;


@Service
public class PhoneNumberServieImpl implements PhoneNumberServie {

    @Autowired
    private PhoneNumberRepository phoneNumberRepository;

    @Override
    public Iterable<PhoneNumber> getAllNumbers() {
        return phoneNumberRepository.findAll();
    }

    @Override
    public PhoneNumber getNumberById(Integer id) {
        return phoneNumberRepository.findOne(id);
    }

    @Override
    public PhoneNumber saveNumber(PhoneNumber phoneNumber) {
        return phoneNumberRepository.save(phoneNumber);
    }

    @Override
    public void deleteNumber(Integer id) {
        phoneNumberRepository.delete(id);
    }
}
