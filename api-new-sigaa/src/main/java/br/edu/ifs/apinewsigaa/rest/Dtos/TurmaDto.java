package br.edu.ifs.apinewsigaa.rest.Dtos;

import br.edu.ifs.apinewsigaa.model.TurmaModel;
import lombok.Data;
import org.modelmapper.ModelMapper;
import java.util.Date;

@Data

public class TurmaDto {
    private Date dataInicio;
    private Date dataFim;
    private int idProfessor;
    private int idDisciplina;



    public TurmaModel toModel() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, TurmaModel.class);
    }
}
