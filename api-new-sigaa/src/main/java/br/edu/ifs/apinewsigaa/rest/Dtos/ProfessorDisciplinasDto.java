package br.edu.ifs.apinewsigaa.rest.Dtos;

import br.edu.ifs.apinewsigaa.model.ProfessorModel;
import lombok.Data;
import org.modelmapper.ModelMapper;
import java.util.List;

@Data
public class ProfessorDisciplinasDto {
    private ProfessorDto professor;
    private List<DisciplinaDto> disciplinas;

    public ProfessorModel toModel() {
        var modelMapper = new ModelMapper();
        return modelMapper.map(this, ProfessorModel.class);
    }

}
