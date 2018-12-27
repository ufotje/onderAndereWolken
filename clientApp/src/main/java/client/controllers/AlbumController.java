package client.controllers;

import client.services.RestClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin/kiekjes_album")
public class AlbumController {

    @Autowired
    private RestClientService restClientService;

    @Value("${resource.server.socket}")
    private String resourceServerSocket;

    @GetMapping
    public String handleGetAlbums(){
        return "albums";
    }

    @GetMapping("/nieuw")
    public String handleGetNew(ModelMap model){
        model.addAttribute("auth", restClientService.createAuthenticationValue());
        model.addAttribute("destinationSocket", resourceServerSocket);
        return "admin/pictureAlbumForm";
    }
}
