package client.services;

import client.entities.Contact;

import java.util.List;

/**
 * Created by PearlS on 13/11/2017.
 */
public interface ContactService {

    List<Contact> getAllContacts();
    void deleteContact(Contact contact);
    Contact addContact(Contact contact);
    Contact getContactById(int id);
}
