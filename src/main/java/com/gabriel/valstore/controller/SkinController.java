package com.gabriel.valstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping(value = "/cadastroSkin")
    public String cadastroSkin() {
        return "catalogo/skinForm";
    }

    @PostMapping(value = "/salvarSkin")
    public String salvarSkin(@ModelAttribute Skin skin) throws Exception {
        skinService.create(skin);
        return "redirect:/skin/skinList";
    }

}
