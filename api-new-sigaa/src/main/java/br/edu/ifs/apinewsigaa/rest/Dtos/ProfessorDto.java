package br.edu.ifs.apinewsigaa.rest.Dtos;

import br.edu.ifs.apinewsigaa.model.ProfessorModel;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data

public class ProfessorDto {
    private String nome;
    private String cpf;
    private String email;
    private Data dataNascimento;
    private String celular;
    private String apelido;
    private String matricula;


    public ProfessorModel toModel() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, ProfessorModel.class);
    }
}
