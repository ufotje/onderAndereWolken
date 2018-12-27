package client.services;

import client.entities.Guestbook;

import java.util.List;

public interface GuestbookService {

    List<Guestbook> findAllGuestbookEntries();

    Guestbook findById(int id);

    Guestbook addGuestbook(Guestbook guestbook);

    Guestbook deleteGuestbook(Guestbook guestbook);

}
