package com.example.miapp.model;

import java.util.List;

public class BoletaRequest {
    private Double total;
    private String fecha; 
    private List<Long> productos; 
    public Double getTotal() { return total; }
    public void setTotal(Double total) { this.total = total; }

    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }

    public List<Long> getProductos() { return productos; }
    public void setProductos(List<Long> productos) { this.productos = productos; }
}
