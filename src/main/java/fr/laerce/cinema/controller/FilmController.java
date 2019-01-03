package fr.laerce.cinema.controller;


import fr.laerce.cinema.dao.FilmDao;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/** On indique à springboot qu'il s'agit d'une classe controller**/
@Controller
public class FilmController {

    @Autowired
    FilmDao filmDao;

   
    @GetMapping("/film_list")
    public String film_list(Model model){
        model.addAttribute("films", filmDao.findAllByOrderByIdAsc());
        return "film/film_list";
    }
    @GetMapping("/film_detail/")

    public String film_detail(Model model){
        model.addAttribute ("film",filmDao.findAll());
        return "film/film_detail";
    }

    @GetMapping("/film_detail/{id}")
  
     public String film_detail_id(Model model, @PathVariable("id") Long id){
        model.addAttribute ("film",filmDao.findById (id).get ());


        return "film/film_detail";
    }

    @GetMapping("/film_detail{image_path}")
    public void image_path(HttpServletRequest request, HttpServletResponse response, String image_path) throws IOException{
        String filename = "C:\\Users\\CDI\\IdeaProjects\\cinemajpa\\src\\main\\resources\\static\\images\\affiches" +image_path;

       /**UTILITAIRE POUR IMPORTER DES IMAGES A PARTIR D'UN FOLDER EXTERNE A L'APPLICATION**/
        String mime = request.getServletContext().getMimeType(filename);
        if (mime == null) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }
        response.setContentType(mime);
        File file = new File(filename);
        response.setContentLength((int) file.length());
        FileInputStream in = new FileInputStream(file);
        OutputStream out = response.getOutputStream();
        byte[] buf = new byte[1024];
        int count = 0;
        while ((count = in.read(buf)) >= 0) {
            out.write(buf, 0, count);
        }
        out.close();
        in.close();
    }

/*// @Value( "${url}" )
     String url;
    @GetMapping("/affiche/{image_path}")
    public ResponseEntity<byte[]> getImageAsResponseEntity (HttpServletRequest request, HttpServletResponse response, @PathVariable("image_path") String image_path) {
        try {
            HttpHeaders headers = new HttpHeaders ();
            String filename=url+image_path;
            File i = new File (filename);
            FileInputStream in = new FileInputStream(i);
            byte[] media = IOUtils.toByteArray (in);
            headers.setCacheControl (CacheControl.noCache ().getHeaderValue ());

            ResponseEntity<byte[]> responseEntity = new ResponseEntity<> (media, headers, HttpStatus.OK);
            return responseEntity;
        } catch (IOException e) {
            e.printStackTrace ();
        }
       return null;
}*/
    @GetMapping("/film_form")
    public String film_form(Model model){
        model.addAttribute("films", filmDao.findAll());
        return "film/film_form";
    }

 //on copie/colle la methode pour le portrait des acteur
//    @Value( "${url2}" )
//    private String url2;
//    //que l'on mappe sur image/id id etant le nom brut de l'image
//    @GetMapping("/image/{id}")
//    public ResponseEntity<byte[]> getImageAsResponseEntity2 (HttpServletRequest request, HttpServletResponse response, @PathVariable("id") String id) {
//        try {
//            HttpHeaders headers = new HttpHeaders ();
//            String filename=url2+id;
//            File i = new File (filename);
//            FileInputStream in = new FileInputStream(i);
//            byte[] media = IOUtils.toByteArray (in);
//            headers.setCacheControl (CacheControl.noCache().getHeaderValue());
//
//            ResponseEntity<byte[]> responseEntity = new ResponseEntity<> (media, headers, HttpStatus.OK);
//            return responseEntity;
//        } catch (IOException e) {
//            e.printStackTrace ();
//        }
//        return null;
////    }
//    @GetMapping("/acteur/{id}")
//    //on recupere id grace à pathvariable
//    public String acteur(Model m, @PathVariable("id") String id){
//        //on envoie a acteur la personne concernée grace a la methode getbyaf et id qui est le nom de l'image
//        m.addAttribute ("actor", personneDao.findByPhotoPath (id));
//return"acteur";}
}
