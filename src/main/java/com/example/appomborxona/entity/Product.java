package com.example.appomborxona.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product extends Template {
    @ManyToOne
    private Category category;

    @OneToOne
    private Attachment photo;

    @ManyToOne
    private Measurement measurement;

    private String code;
}
