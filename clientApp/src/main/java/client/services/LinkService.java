package client.services;

import client.entities.Link;

import java.util.List;

public interface LinkService {

    List<Link> findAllLinks();

    Link findById(int id);

    Link findByUrl(String linkUrl);

    Link addLink(Link link);

    Link updateLink(Link link);

    Link deleteLink(Link link);

    String checkUrl(String linkUrl);

}
