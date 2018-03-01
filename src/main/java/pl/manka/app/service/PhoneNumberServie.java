package pl.manka.app.service;

import pl.manka.app.domain.PhoneNumber;

public interface PhoneNumberServie {
    Iterable<PhoneNumber> getAllNumbers();
    PhoneNumber getNumberById(Integer id);
    PhoneNumber saveNumber(PhoneNumber phoneNumber);
    void deleteNumber(Integer id);
}
