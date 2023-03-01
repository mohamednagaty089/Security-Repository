package com.global.security.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="sec_users")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    @NotNull
    private String username;
    @NotNull
    private String password;

    @NotNull
    private String firstname;
    @NotNull
    private String lastname;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name="sec_user_role",joinColumns =@JoinColumn(name="user_id"),inverseJoinColumns = @JoinColumn(name="role_id"))
    @OrderColumn(name="id")
    private Set<Role> roles=new HashSet<>();
}
