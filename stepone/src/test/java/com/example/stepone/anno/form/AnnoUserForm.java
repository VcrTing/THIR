package com.example.stepone.anno.form;

import com.example.stepone.anno.define.AnnoPhoneValid;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnnoUserForm {
    private String name;
    private Boolean is18;

    @AnnoPhoneValid
    private String phone;
}
