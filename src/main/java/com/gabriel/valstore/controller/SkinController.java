package com.gabriel.valstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gabriel.valstore.entity.Skin;
import com.gabriel.valstore.service.SkinService;


@Controller
@RequestMapping("/skin")
public class SkinController {

    @Autowired
    private SkinService skinService;

    @GetMapping(value = "/skinList")
    public String skins(Model model, @RequestParam(name = "sort", required = false) String sort) throws Exception {
        model.addAttribute("skins", skinService.findAllSkin(sort));
        return "catalogo/skinList";
    }

    @GetMapping(value = "/skinListAdmin")
    public String skinsAdmin(Model model, @RequestParam(name = "sort", required = false) String sort) throws Exception {
        model.addAttribute("skins", skinService.findAllSkin(sort));
        return "catalogo/skinListAdmin";
    }

    @GetMapping(value = "/cadastroSkin")
    public ModelAndView cadastroSkin() {
        ModelAndView mv = new ModelAndView("catalogo/skinForm");
        mv.addObject("skin", new Skin());
        return mv;
    }

    @PostMapping(value = "/salvarSkin")
    public String salvarSkin(@ModelAttribute Skin skin) throws Exception {
        skinService.create(skin);
        return "redirect:/skin/skinListAdmin";
    }

    @GetMapping(value = "/editar/{id}")
    public ModelAndView editar(@PathVariable(name = "id") Long id) throws Exception {
        ModelAndView mv = new ModelAndView("catalogo/skinForm");
        mv.addObject("skin", skinService.findById(id));
        return mv;
    }
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Object> deletar(@PathVariable(name = "id") Long id) throws Exception {
        skinService.delete(id);
        return ResponseEntity.ok().build();
    }

}
