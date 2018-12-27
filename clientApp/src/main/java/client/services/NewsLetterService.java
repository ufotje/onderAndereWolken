package client.services;

import client.entities.NewsLetter;

public interface NewsLetterService {

    NewsLetter subscribe(NewsLetter newsLetter);

    NewsLetter findByMail(String mail);

    void deleteNewsletter(NewsLetter newsLetter);
}