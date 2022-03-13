package com.example.appomborxona.payload;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class InputDto {
//    private Timestamp timestamp;
    private String factureNumber;
    private Integer warehouse_id;
    private Integer suppler_id;
    private Integer currency_id;
}
