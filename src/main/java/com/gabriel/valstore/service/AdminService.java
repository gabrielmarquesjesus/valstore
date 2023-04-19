package com.gabriel.valstore.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.gabriel.valstore.entity.Admin;
import com.gabriel.valstore.repository.AdminDao;

@Service
public class AdminService {

    @Autowired
    private AdminDao adminDao;
    
    public void create(Admin admin) throws Exception{
        adminDao.save(admin);
    }

    public boolean validaAdmin(Admin admin) throws Exception{
        Example example = Example.of(admin);
        Optional adminValido = adminDao.findOne(example);
        return adminValido.isEmpty() ? false : true;
    }

}
