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
@Table(name = "tbl_especialidad")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter @Setter
public class Especialidad {
	@Id
    @Column(name = "id_especialidad")
    @EqualsAndHashCode.Include
    private int idEspecialidad;

    @Column(name = "titulo", nullable = false, length = 255)
    private String titulo;

    @Override
    public String toString() {
        return titulo;
    }
    

}
