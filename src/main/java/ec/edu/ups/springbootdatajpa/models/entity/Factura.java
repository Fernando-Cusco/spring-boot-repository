package ec.edu.ups.springbootdatajpa.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "facturas")
public class Factura implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String descripcion;

    private String observacion;

    @Temporal(TemporalType.DATE)
    @Column(name = "create_at")
    private Date createAt;

    @ManyToOne(fetch = FetchType.LAZY)
    private Cliente cliente;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "factura_id")
    private List<ItemFactura> items;

    public Factura() {
        items = new ArrayList<ItemFactura>();
    }

    @PrePersist
    public void prePersist() {
        createAt = new Date();
    }

    public void agregarItemFactura(ItemFactura itemFactura) {
        items.add(itemFactura);
    }
    
    public Double calcularTotal() {
        Double total = 0.0;
        for(ItemFactura item: items) {
            total += item.calcularSubtotal();
        }
        return total;
    }

    public List<ItemFactura> getItems() {
        return items;
    }



    public void setItems(List<ItemFactura> items) {
        this.items = items;
    }



    public Long getId() {
        return id;
    }



    public void setId(Long id) {
        this.id = id;
    }



    public String getDescripcion() {
        return descripcion;
    }



    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }



    public String getObservacion() {
        return observacion;
    }



    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }



    public Date getCreateAt() {
        return createAt;
    }



    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }



    public Cliente getCliente() {
        return cliente;
    }



    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }



    private static final long serialVersionUID = 1L;
    
}