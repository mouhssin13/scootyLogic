package com.pfa.smartV.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;



@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "User",
        uniqueConstraints = {
                @UniqueConstraint(name = "uniq_email" ,columnNames = "email"),
                @UniqueConstraint(name = "uniq_cin", columnNames = "CIN")
        }
)
public class User implements UserDetails {


 @Id
 @SequenceGenerator(
         name = "user_sequence",
         sequenceName = "user_sequence",
         allocationSize = 1
 )
 @GeneratedValue(
         strategy = GenerationType.SEQUENCE,
         generator = "user_sequence"
 )
 @Column(
         name = "id",
         updatable = false
 )
 private Long id;

 @Column(
         name = "fullName",
         nullable = false
 )
 private String fullName;
 @Column(
         name = "email",
         nullable = false
 )
 private String email;
 @Column(
         name = "tel",
         nullable = false
 )
 private String tel;
 @Column(
         name = "CIN",
         nullable = false
 )
 private String CIN;

 @Enumerated(EnumType.STRING)
 private Role role;

 @Column(
         name = "password",
         nullable = false
 )
 private String password;

 @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
 private List<Ride> rides;


 @Override
 public Collection<? extends GrantedAuthority> getAuthorities() {
  return List.of(new SimpleGrantedAuthority(role.name()));
 }

 @Override
 public String getUsername() {
  return email;
 }

 @Override
 public boolean isAccountNonExpired() {
  return true;
 }

 @Override
 public boolean isAccountNonLocked() {
  return true;
 }

 @Override
 public boolean isCredentialsNonExpired() {
  return true;
 }

 @Override
 public boolean isEnabled() {
  return true;
 }

 @Override
 public String toString() {
  return "User{" +
          "id=" + id +
          ", fullName='" + fullName + '\'' +
          ", email='" + email + '\'' +
          ", tel='" + tel + '\'' +
          ", CIN='" + CIN + '\'' +
          ", password='" + password + '\'' +
          '}';
 }
}
