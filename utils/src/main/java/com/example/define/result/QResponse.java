package com.example.define.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QResponse<T> {

    private Integer code;
    private String message;
    private T data;

}
