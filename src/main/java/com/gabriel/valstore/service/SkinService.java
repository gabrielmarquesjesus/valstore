package com.gabriel.valstore.service;

import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import com.gabriel.valstore.entity.Skin;
import com.gabriel.valstore.repository.SkinDao;

@Service
public class SkinService {

    @Autowired
    private SkinDao skinDao;
    
    public void create(Skin skin) throws Exception{
        String base64 = Base64.getEncoder().encodeToString(skin.getImagem().getBytes());
        skin.setUrlImagem(base64);
        skinDao.save(skin);
    }

    public List<Skin> findAllSkin(String ordenacao) throws Exception{
        Sort sort = Sort.unsorted();

        if(!StringUtils.isEmpty(ordenacao)){
            Direction direction = ordenacao.split(",")[1].equals("ASC") ? Direction.ASC : Direction.DESC;
            String prop = ordenacao.split(",")[0];
            sort = Sort.by(direction,prop);
        }
        return skinDao.findAll(sort);
    }

}
