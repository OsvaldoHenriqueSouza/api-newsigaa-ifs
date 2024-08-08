package br.edu.ifs.apinewsigaa.service;

import br.edu.ifs.apinewsigaa.exception.DataIntegrityException;
import br.edu.ifs.apinewsigaa.exception.ObjectNotFoundException;
import br.edu.ifs.apinewsigaa.model.AlunoModel;
import br.edu.ifs.apinewsigaa.repository.AlunoRepository;
import br.edu.ifs.apinewsigaa.rest.Dtos.AlunoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import static br.edu.ifs.apinewsigaa.exception.DataIntegrityException.tratamentoErro;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    @Transactional
    public AlunoDto registroAluno(AlunoModel aluno) {
        try {
            return alunoRepository.save(aluno).toDto();
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException(tratamentoErro(e));
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

    public void updateAluno(AlunoModel aluno) {

        try {
            Optional<AlunoModel> alunoOptional = alunoRepository.findById(aluno.getId());
            if (alunoOptional.isPresent()) {
                alunoRepository.save(aluno);
            } else {
                throw new ObjectNotFoundException("Aluno não encontrado: " + aluno.getNome());
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException(tratamentoErro(e));
        }
    }

    @Transactional
    public void deleteAlunoPorMatricula(String matricula) {
        Optional<AlunoModel> alunoOptional = alunoRepository.findByMatricula(matricula);
        alunoOptional.orElseThrow(() -> new ObjectNotFoundException("Matrícula : " + matricula + " não encontrada"));
        alunoRepository.deleteByMatricula(matricula);
    }

    @Transactional
    public void deleteAlunoPorId(int id) {

        if (!verificarExistenciaAluno(id)) {
            throw new ObjectNotFoundException("Aluno não encontrado com o id : " + id);
        };

        try {
            alunoRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException(tratamentoErro(e));
        }
    }

    public boolean verificarExistenciaAluno(int id) {
        return alunoRepository.existsById(id);
    }
}
