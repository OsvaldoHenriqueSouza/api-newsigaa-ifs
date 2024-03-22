package br.edu.ifs.apinewsigaa.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "matricula")
public class MatriculaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "idTurma")
    private TurmaModel idTurma;
    @ManyToOne
    @JoinColumn(name = "idAluno")
    private AlunoModel idAluno;
}
