package br.edu.ifs.apinewsigaa.rest.Dtos;

import br.edu.ifs.apinewsigaa.model.MatriculaModel;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data


public class MatriculaDto {

    private int idTurma;
    private int idAluno;

    public MatriculaModel toModel() {
        var modelMapper = new ModelMapper();
        return modelMapper.map(this, MatriculaModel.class);
    }
}
