/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.senai.alertar.model;

/**
 *
 * @author Mari
 */
public class Sensor extends DataBaseObject {

    private Integer Id;
    private String Codigo;
    private Double GPSLatitude;
    private Double GPSLongitude;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String Codigo) {
        this.Codigo = Codigo;
    }

    public Double getGPSLatitude() {
        return GPSLatitude;
    }

    public void setGPSLatitude(Double GPSLatitude) {
        this.GPSLatitude = GPSLatitude;
    }

    public Double getGPSLongitude() {
        return GPSLongitude;
    }

    public void setGPSLongitude(Double GPSLongitude) {
        this.GPSLongitude = GPSLongitude;
    }

}
