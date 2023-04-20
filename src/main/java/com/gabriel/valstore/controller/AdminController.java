package com.gabriel.valstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gabriel.valstore.entity.Admin;
import com.gabriel.valstore.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;
    
    @GetMapping(value = "/cadastroAdmin")
    public String cadastroAdmin() throws Exception{
        return "admin/adminCadastroForm";
    }

    @GetMapping(value = "/loginAdmin")
    public String loginAdmin() throws Exception{
        return "admin/adminLoginForm";
    }

    @PostMapping(value = "/salvarAdmin")
    public String create(@ModelAttribute Admin admin) throws Exception{
        adminService.create(admin);
        return "redirect:/admin/loginAdmin";
    }

    @PostMapping(value = "/validarAdmin")
    public ModelAndView validarAdmin(@ModelAttribute Admin admin) throws Exception{
        boolean isValid  = adminService.validaAdmin(admin);
        ModelAndView mv = new ModelAndView();
        if(isValid){
            mv.setViewName("redirect:/skin/skinListAdmin");
            return mv;
        }else{
            mv.setViewName("admin/adminLoginForm");
            mv.addObject("aviso", "Administrador Inválido!");
            return mv; 
        }
    }


}
