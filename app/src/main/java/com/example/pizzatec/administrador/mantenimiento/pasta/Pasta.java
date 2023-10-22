package com.example.pizzatec.administrador.mantenimiento.pasta;

import com.google.firebase.database.Exclude;

public class Pasta {
        private String key;
        private String nombre;
        private String precio;
        private String descripcion;
        private int url;

        public Pasta() {
        }

        public Pasta(String nombre, String precio, String descripcion, int url) {
            this.nombre = nombre;
            this.precio = precio;
            this.descripcion = descripcion;
            this.url = url;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getPrecio() {
            return precio;
        }

        public void setPrecio(String precio) {
            this.precio = precio;
        }

        public String getDescripcion() {
            return descripcion;
        }

        public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
        }

        public int getUrl() {
            return url;
        }

        public void setUrl(int url) {
            this.url = url;
        }

        @Exclude
        public String getKey() {
            return key;
        }

        @Exclude
        public void setKey(String key) {
            this.key = key;
        }

}
