package be.intec.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

@Controller
public class AlbumController {

//    @PostMapping
//    @CrossOrigin
//    public String handlePost(HttpServletRequest request){
//        try {
//            Map map = request.getParameterMap();
//            System.out.println(map.size());
//            String title = request.getParameter("title");
//
//            System.out.println(title);
//
//
//            for (Part part : request.getParts()){
//                if (part.getSubmittedFileName() != null){
//                    System.out.println(part.getSubmittedFileName());
//                    Path path = Paths.get("c://"+title+"/", part.getSubmittedFileName());
//                    Files.createDirectories(path.getParent());
//                    Files.createFile(path);
//                }
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ServletException e) {
//            e.printStackTrace();
//        }
//
//        return "redirect:http://localhost:8080/admin/kiekjes_album";
//    }
}
