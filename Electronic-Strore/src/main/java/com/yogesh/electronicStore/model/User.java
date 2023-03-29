package com.yogesh.electronicStore.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="User_Data")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends BaseEntityClass{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    @Column(name="User_Name")
    private String name;

    @Column(name="User_Email")
    private String email;

    @Column(name="User_Password")
    private String password;

    private String about;

    private String gender;

    private String imageName;
}
