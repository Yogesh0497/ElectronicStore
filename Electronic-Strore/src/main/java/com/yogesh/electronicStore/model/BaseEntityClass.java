package com.yogesh.electronicStore.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class BaseEntityClass {

    @Column(name="Create_Date", updatable = false)
    @CreationTimestamp
    private LocalDate createDate;

    @Column(name="Update_Date", insertable = false)
    @UpdateTimestamp
    private LocalDate updateDate;

    @Column(name = "Status")
    private String isactive;
}
