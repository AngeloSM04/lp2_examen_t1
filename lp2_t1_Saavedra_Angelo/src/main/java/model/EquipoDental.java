package model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
@Entity
@Table(name = "tbl_equipo_dental")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter @Setter

public class EquipoDental {
	@Id
    @Column(name = "nro_equipo")
    @EqualsAndHashCode.Include
    private int nroEquipo;

    @Column(name = "nombre", nullable = false, length = 255)
    private String nombre;

    @Column (name= "costo", nullable = false)
    private double costo;


    @Column(name = "fecha_adquisicion", nullable = false, insertable = false, updatable = false)
    private LocalDate fechaAdquisicion;
    
    @Column (name= "estado", nullable = false)
    private String estado;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_dentista")
    private Dentista dentista;
    
    
}
