package br.edu.ifs.apinewsigaa.service;

import br.edu.ifs.apinewsigaa.model.AlunoModel;
import br.edu.ifs.apinewsigaa.repository.Aluno.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

//    public AlunoModel salvarAluno(AlunoDto aluno) {
//        AlunoModel alunoModel = new AlunoModel();
//        alunoModel.setNome(aluno.getNome());
//        alunoModel.setCpf(aluno.getCpf());
//        alunoModel.setEmail(aluno.getEmail());
//        alunoModel.setDataNascimento(aluno.getDataNascimento());
//        alunoModel.setCelular(aluno.getCelular());
//        alunoModel.setMatricula(aluno.getMatricula());
//        alunoModel.setApelido(aluno.getApelido());
//
//        return alunoRepository.save(alunoModel);
//    }
}
