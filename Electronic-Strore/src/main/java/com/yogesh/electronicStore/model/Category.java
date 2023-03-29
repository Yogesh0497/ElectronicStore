package com.yogesh.electronicStore.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name="Category_Data")
@NoArgsConstructor
@AllArgsConstructor
public class Category extends BaseEntityClass {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long categoryId;

     private String title;

     private String description;

     private String coverImage;
}
