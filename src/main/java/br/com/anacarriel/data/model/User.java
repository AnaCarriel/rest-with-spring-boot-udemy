package br.com.anacarriel.data.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User implements UserDetails, Serializable {

    private static final long serialVersionUID = 1L;



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_name", unique = true)
    private String userName;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "password")
    private String password;

    @Column(name = "account_non_expired")
    private Boolean accountNonExpired;

    @Column(name = "credentials_non_locked")
    private Boolean credentialsNonLoked;

    @Column(name = "credentials_non_expired")
    private Boolean credentialsNonExpired;

    @Column(name = "enabled")
    private Boolean enabled;

    //Para vincular as tabelas user_permission, USER e a PERMISSION, mapeamento de muitos para muitos
    @ManyToMany(fetch = FetchType.EAGER) //sempre vai trazer
    @JoinTable(name = "user_permission", joinColumns = {@JoinColumn (name = "id_user")},
    inverseJoinColumns = { @JoinColumn (name = "id_permission")})
    private List<Permission> permissionList;

    //aqui vai buscas as regras de cada id permissão
    public List<String> getRoles(){
        List<String> roles = new ArrayList<>();
        for (Permission permission: this.permissionList) {
            roles.add(permission.getDescription());
            
        }
        return roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.permissionList;
    }

    @Override
    public String getPassword() {
       return this.password;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.credentialsNonLoked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(Boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public Boolean getCredentialsNonLoked() {
        return credentialsNonLoked;
    }

    public void setCredentialsNonLoked(Boolean credentialsNonLoked) {
        this.credentialsNonLoked = credentialsNonLoked;
    }

    public Boolean getCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public List<Permission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<Permission> permissionList) {
        this.permissionList = permissionList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(getUserName(), user.getUserName()) && Objects.equals(getFullName(), user.getFullName()) && Objects.equals(getPassword(), user.getPassword()) && Objects.equals(isAccountNonExpired(), user.isAccountNonExpired()) && Objects.equals(getCredentialsNonLoked(), user.getCredentialsNonLoked()) && Objects.equals(isCredentialsNonExpired(), user.isCredentialsNonExpired()) && Objects.equals(isEnabled(), user.isEnabled()) && Objects.equals(getPermissionList(), user.getPermissionList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, getUserName(), getFullName(), getPassword(), isAccountNonExpired(), getCredentialsNonLoked(), isCredentialsNonExpired(), isEnabled(), getPermissionList());
    }
}
