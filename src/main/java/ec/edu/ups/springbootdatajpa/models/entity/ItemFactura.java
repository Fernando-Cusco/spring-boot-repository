package ec.edu.ups.springbootdatajpa.models.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "item_factura")
public class ItemFactura implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer cantidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id")
    private Producto producto;

    public Double calcularSubtotal() {
        return cantidad.doubleValue() * producto.getPrecio();
    }

    public Long getId() {
        return id;
    }



    public void setId(Long id) {
        this.id = id;
    }



    public Integer getCantidad() {
        return cantidad;
    }



    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    


    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }




    private static final long serialVersionUID = 1L;
}
