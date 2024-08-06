package br.edu.ifs.apinewsigaa.service;

import br.edu.ifs.apinewsigaa.exception.DataIntegrityException;
import br.edu.ifs.apinewsigaa.exception.ObjectNotFoundException;
import br.edu.ifs.apinewsigaa.model.AlunoModel;
import br.edu.ifs.apinewsigaa.repository.AlunoRepository;
import br.edu.ifs.apinewsigaa.rest.Dtos.AlunoDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static br.edu.ifs.apinewsigaa.exception.DataIntegrityException.extrairErro;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public AlunoDto registroAluno(AlunoModel aluno) {
        try {
            return alunoRepository.save(aluno).toDto();
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException(extrairErro(e));
        }
    }

    @Transactional(readOnly = true)
    public List<AlunoDto> getAlunos() {
        List<AlunoModel> alunos = alunoRepository.findAll();
        return alunos.stream().map(AlunoModel::toDto).toList();
    }

    @Transactional(readOnly = true)
    public AlunoDto getPorMatricula(String matricula) {
        Optional<AlunoModel> alunoOptional = alunoRepository.findByMatricula(matricula);
        AlunoModel alunoModel = alunoOptional.orElseThrow(() -> new ObjectNotFoundException("Matrícula não encontrada: " + matricula));
        return alunoModel.toDto();
    }

    public void updateAluno(String matricula, AlunoModel aluno) {
        Optional<AlunoModel> alunoOptional = alunoRepository.findByMatricula(aluno.getMatricula());
        AlunoModel alunoModel = alunoOptional.orElseThrow(() -> new ObjectNotFoundException("Matrícula não encontrada: " + matricula));
        alunoModel.setId(alunoModel.getId());

        try {
            alunoRepository.save(alunoModel).toDto();
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException(extrairErro(e));
        }
    }

    @Transactional
    public void deleteAluno(String matricula) {
        Optional<AlunoModel> alunoOptional = alunoRepository.findByMatricula(matricula);
        AlunoModel alunoModel = alunoOptional.orElseThrow(() -> new ObjectNotFoundException("Matrícula não encontrada: " + matricula));
        alunoRepository.deleteByMatricula(alunoModel.getMatricula());
    }
}
