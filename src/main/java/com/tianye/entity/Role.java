package com.tianye.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role implements Serializable {
    private Integer id;
    private String roleName;
    private List<Authority> authorities;

    public List<String> getAuthoritieName(){
        ArrayList<String> strings = new ArrayList<>();
        for (Authority authority : authorities) {
            strings.add(authority.getAuthorityName());
        }
        return strings;
    }
}
