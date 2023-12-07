package com.example.view;

import cn.hutool.json.JSONUtil;
import com.example.define.EnumSex;
import com.example.entity.Roie;
import com.example.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserView {
    private Long id;
    private String username;
    private String nickname;
    private String email;
    private String creatAt;
    private String avatar;

    private String sex;
    private Roie roie;

    // 工厂模式
    public static UserView init(User u, List<Roie> rs) {
        Integer __sex = u.getSex();

        Integer __roie = u.getRoieId();
        u.setSex(null);
        u.setRoieId(null);

        UserView res = JSONUtil.toBean(JSONUtil.toJsonStr(u), UserView.class);
        res.setSex(EnumSex.codeToString(__sex));
        res.setRoie(
                rs.stream().filter(r -> r.getId() == __roie).findFirst().orElse(null)
        );
        return res;
    }

    public static List<UserView> initList(List<User> us, List<Roie> rs) {
        return us.stream().map(u -> UserView.init(u, rs)).collect(Collectors.toList());
    }
}
