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
@Table(name = "tbl_dentista")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter @Setter
public class Dentista {
    @Id
    @Column(name = "id_dentista")
    @EqualsAndHashCode.Include
    private int idDentista;

    @Column(name = "nombre_completo", nullable = false, length = 255)
    private String nombreCompleto;

    @Column (name= "cop", nullable = false)
    private String cop;


    @Column(name = "fecha_inicio_contrato", nullable = false)
    private LocalDate fechaInicioContrato;

    @Column (name= "turno", nullable = false)
    private char turno;
    
    @Column(name = "correo", nullable = false, length = 255)
    private String correo;
    

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_especialidad")
    private Especialidad especialidad;
    
    @Override
    public String toString() {
        return nombreCompleto;
    }
}
