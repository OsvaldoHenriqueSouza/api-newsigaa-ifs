package br.edu.ifs.apinewsigaa.rest.Dtos;

import br.edu.ifs.apinewsigaa.model.AlunoModel;
import lombok.Data;
import org.modelmapper.ModelMapper;
import java.util.Date;

@Data
public class AlunoDto {

    private String nome;
    private String cpf;
    private String email;
    private Date dataNascimento;
    private String celular;
    private String matricula;
    private String apelido;

    public AlunoModel toModel(){
        var modelMapper = new ModelMapper();
        return modelMapper.map(this, AlunoModel.class);
    }
}
