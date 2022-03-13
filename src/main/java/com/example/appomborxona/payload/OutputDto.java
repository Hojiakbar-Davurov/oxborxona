package com.example.appomborxona.payload;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class OutputDto {
    private String factureNumber;
    private Integer warehouse_id;
    private Integer client_id;
    private Integer currency_id;
}
