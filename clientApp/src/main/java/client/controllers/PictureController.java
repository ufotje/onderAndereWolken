package client.controllers;

import client.entities.Picture;
import client.services.RestClientService;
import client.services.implementation.PictureServiceImpl;
import client.services.implementation.RestClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PictureController {

    @Autowired
    private RestClientServiceImpl restClientService;

    @Autowired
    private PictureServiceImpl pictureService;

    @Value("${resource.server.socket}")
    private String baseUrl;

    @Value("${client.server.socket}")
    private String redirectSocket;

    @GetMapping("admin/pictures")
    public String handleGetAll(ModelMap model){
        model.addAttribute("pictures", pictureService.findAll());
        return "admin/pictures";
    }

    @GetMapping("admin/pictures/add")
    public String handleGet(Model model){
        Picture picture = null;
        try{
            picture = pictureService.getLatestPicture();
            model.addAttribute("pictureUrl", picture.getLocation());
        }catch(NullPointerException e){
            model.addAttribute("pictureUrl", baseUrl+"images/defaultStoryBackgr.jpg");
        }

        model.addAttribute("auth", restClientService.createAuthenticationValue());
        model.addAttribute("destinationSocket", baseUrl);
        model.addAttribute("redirectSocket", redirectSocket);

        return "admin/pictureForm";
    }
}
