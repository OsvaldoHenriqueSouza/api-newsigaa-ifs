package br.edu.ifs.apinewsigaa.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "disciplina")
public class DisciplinaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "nome", length = 255, nullable = false)
    private String nome;
    @Column(name = "numeroCreditos", nullable = false, unique = true)
    private int matricula; // pode ser trocado por byte
}
