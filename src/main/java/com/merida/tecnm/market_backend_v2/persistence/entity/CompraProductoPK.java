package com.merida.tecnm.market_backend_v2.persistence.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CompraProductoPK implements Serializable {

    @Column (name = "id_compra")
    private Integer idCompra;

    @Column (name = "id_producto")
    private Integer idProducto;

    public Integer getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(Integer idCompra) {
        this.idCompra = idCompra;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    @Override public boolean equals(Object other){
        if(this==other)return true;
        if(!(other instanceof CompraProductoPK that))return false;
        return Objects.equals(idCompra,that.idCompra)&&Objects.equals(idProducto,that.idProducto);
    }
    @Override public int hashCode(){return Objects.hash(idCompra,idProducto);}
}
