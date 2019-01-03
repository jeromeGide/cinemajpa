package fr.laerce.cinema.controller;

import fr.laerce.cinema.dao.FilmDao;
import fr.laerce.cinema.dao.PersonDao;
import fr.laerce.cinema.dao.PlayDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/role")
public class PlayController {
    @Autowired
    PlayDao playDao;

    @Autowired
    PersonDao personDao;

    @Autowired
    FilmDao filmDao;

    @GetMapping("/list")
    public String list(Model model){
        model.addAttribute("play", playDao.findAllByOrderByIdAsc());
        return "role/list";
    }
    @GetMapping("/role_list/{id}")
    public String detail(Model model, @PathVariable("id") Long id){
        model.addAttribute("play", playDao.findById(id));
        return "role/list";
    }

}
