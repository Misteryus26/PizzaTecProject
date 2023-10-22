package com.example.pizzatec.menu.inicio;

public class PromocionData {
    private String promocion;
    private String descripcion;
    private String precio;
    private Integer promoImg;

    public PromocionData(String promocion, String descripcion, String precio, Integer promoImg) {
        this.promocion = promocion;
        this.descripcion = descripcion;
        this.precio = precio;
        this.promoImg = promoImg;
    }

    public String getPromocion() {
        return promocion;
    }

    public void setPromocion(String promocion) {
        this.promocion = promocion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public Integer getPromoImg() {
        return promoImg;
    }

    public void setPromoImg(Integer promoImg) {
        this.promoImg = promoImg;
    }
}
