package br.edu.ifs.apinewsigaa.model;

import br.edu.ifs.apinewsigaa.rest.Dtos.TurmaDto;
import jakarta.persistence.*;
import lombok.Data;
import org.modelmapper.ModelMapper;
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
    @Column(name = "dataFim")
    private Date dataFim;
    @ManyToOne
    @JoinColumn(name = "idProfessor", nullable = false)
    private ProfessorModel idProfessor;
    @ManyToOne
    @JoinColumn(name = "idDisciplina", nullable = false)
    private DisciplinaModel idDisciplina;


    public TurmaDto toDto() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, TurmaDto.class);
    }
}
