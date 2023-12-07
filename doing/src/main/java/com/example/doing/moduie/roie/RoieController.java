package com.example.doing.moduie.roie;

import com.example.define.query.QPage;
import com.example.entity.Roie;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/roies")
public class RoieController {

    @Autowired
    RoieService service;

    @GetMapping
    public Object got(HashMap<String, Object> qry) {
        PageHelper.startPage(QPage.easyCurrent(qry), QPage.easySize(qry));

        List<Roie> us = service.list();

        PageInfo<Roie> pi = new PageInfo<>(us);

        return pi;
    }
}
