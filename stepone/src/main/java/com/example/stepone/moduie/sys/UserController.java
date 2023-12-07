package com.example.stepone.moduie.sys;

import com.example.define.query.QPage;
import com.example.entity.Roie;
import com.example.entity.User;
import com.example.stepone.moduie.roie.RoieService;
import com.example.view.UserView;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService service;

    @Autowired
    RoieService roieService;

    @GetMapping
    public PageInfo<UserView> got(HashMap<String, Object> qry) {
        PageHelper.startPage(QPage.easyCurrent(qry), QPage.easySize(qry));

        List<User> us = service.list();

        List<Roie> rs = roieService.list();

        return new PageInfo<>(UserView.initList(us, rs));
    }
}
