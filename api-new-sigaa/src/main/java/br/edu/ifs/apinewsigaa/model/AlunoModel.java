package br.edu.ifs.apinewsigaa.model;

import br.edu.ifs.apinewsigaa.rest.Dtos.AlunoDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;
import org.modelmapper.ModelMapper;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotNull
    @Length(min = 3, max = 255)
    @Column(name = "nome", length = 255, nullable = false)
    private String nome;
    @NotNull
//    @CPF
    @Column(name = "cpf", length = 14, nullable = false, unique = true)
    private String cpf;
    @NotNull
//    @Email
    @Column(name = "email", length = 255, nullable = false, unique = true)
    private String email;
    @NotNull
    @Column(name = "dataNascimento", nullable = false)
    private Date dataNascimento;
//    @Pattern(regexp = "\\d{2}-\\d{5}-\\d{4}")
//    @Pattern(regexp = "\\d{2}-\\d{4,5}-\\\\d{4}")
    @NotNull
    @Column(name = "celular", length = 15, nullable = false, unique = true)
    private String celular;
    @Column(name = "apelido", length = 255, nullable = true)
    private String apelido;
    @NotNull
    @Column(name = "matricula", nullable = false, unique = true)
    private String matricula;

    public AlunoDto toDto() {
        var modelMapper = new ModelMapper();
        return modelMapper.map(this, AlunoDto.class);
    }
}
