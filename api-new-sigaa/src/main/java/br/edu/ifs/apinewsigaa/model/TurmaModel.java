package br.edu.ifs.apinewsigaa.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
@Entity
@Table(name = "turma")
public class TurmaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "dataInicio", nullable = false)
    private Date dataInicio;
    @Column(name = "dataFim", nullable = false)
    private Date dataFim;
    @ManyToOne
    @JoinColumn(name = "idProfessor", nullable = false)
    private ProfessorModel idProfessor;
    @ManyToOne
    @JoinColumn(name = "idDisciplina", nullable = false)
    private DisciplinaModel idDisciplina;
}
