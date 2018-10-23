package com.team.entity;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
@Table(name = "user", schema = "project", catalog = "")
public class UserEntity {
    private String name;
    @Size(min = 6, message = "密码长度不能低于六位")
    private String password;
    private Long id;
    private Byte enabled;
    private String authorities;

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Id
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "enabled")
    public Byte getEnabled() {
        return enabled;
    }

    public void setEnabled(Byte enabled) {
        this.enabled = enabled;
    }

    @Basic
    @Column(name = "authorities")
    public String getAuthorities() {
        return authorities;
    }

    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (enabled != null ? !enabled.equals(that.enabled) : that.enabled != null) return false;
        if (authorities != null ? !authorities.equals(that.authorities) : that.authorities != null) return false;

        return true;
    }

//    @Override
//    public int hashCode() {
//        int result = name != null ? name.hashCode() : 0;
//        result = 31 * result + (password != null ? password.hashCode() : 0);
//        result = 31 * result + id;
//        result = 31 * result + (enabled != null ? enabled.hashCode() : 0);
//        result = 31 * result + (authorities != null ? authorities.hashCode() : 0);
//        return result;
//    }
}
