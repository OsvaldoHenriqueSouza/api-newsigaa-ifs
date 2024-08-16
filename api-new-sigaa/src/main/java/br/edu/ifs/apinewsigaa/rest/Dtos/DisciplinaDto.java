package br.edu.ifs.apinewsigaa.rest.Dtos;

import br.edu.ifs.apinewsigaa.model.DisciplinaModel;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class DisciplinaDto {
    private int id;
    private String nome;
    private byte numeroCredito;

    public DisciplinaModel toModel() {
        var modelMapper = new ModelMapper();
        return modelMapper.map(this, DisciplinaModel.class);
    }
}
