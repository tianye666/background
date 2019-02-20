package com.tianye.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "admin")
public class Admin implements Serializable {
    @Id
    private Integer id;
    private String username;
    private String password;
    private String salt;
    @Transient
    private List<Role> roles;

    public List<String> getRoleName(){
        ArrayList<String> strings = new ArrayList<>();
        for (Role role : roles) {
            strings.add(role.getRoleName());
        }
        return strings;
    }
}
