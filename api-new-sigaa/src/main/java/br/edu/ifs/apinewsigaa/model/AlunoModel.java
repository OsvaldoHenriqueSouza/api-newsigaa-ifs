package br.edu.ifs.apinewsigaa.model;

import br.edu.ifs.apinewsigaa.rest.Dtos.Aluno.AlunoDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "aluno")
public class AlunoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "nome", length = 255, nullable = false)
    private String nome;
    @Column(name = "cpf", length = 14, nullable = false, unique = true)
    private String cpf;
    @Column(name = "email", length = 255, nullable = false, unique = true)
    private String email;
    @Column(name = "dataNascimento", nullable = false)
    private Date dataNascimento;
    @Column(name = "celular", length = 14, nullable = false, unique = true)
    private String celular;
    @Column(name = "apelido", length = 255, nullable = true)
    private String apelido;
    @Column(name = "matricula", nullable = false, unique = true)
    private int matricula;

    public AlunoModel(AlunoDto aluno) {
        this.nome = aluno.nome();
        this.cpf = aluno.cpf();
        this.email = aluno.email();
        this.dataNascimento = aluno.dataNascimento();
        this.celular = aluno.celular();
        this.matricula = aluno.matricula();
        this.apelido = aluno.apelido();
    }
}
