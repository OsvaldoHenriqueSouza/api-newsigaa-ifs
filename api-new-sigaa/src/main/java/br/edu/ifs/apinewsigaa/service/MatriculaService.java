package br.edu.ifs.apinewsigaa.service;

import br.edu.ifs.apinewsigaa.exception.ObjectNotFoundException;
import br.edu.ifs.apinewsigaa.model.MatriculaModel;
import br.edu.ifs.apinewsigaa.repository.MatriculaRepository;
import br.edu.ifs.apinewsigaa.rest.Dtos.MatriculaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service

public class MatriculaService {
    @Autowired
    private MatriculaRepository matriculaRepository;

    @Transactional(readOnly = true)
    public List<MatriculaDto> getMatriculas() {
        List<MatriculaModel> matriculas = matriculaRepository.findAll();
        return matriculas.stream().map(MatriculaModel::toDto).toList();
    }

    @Transactional(readOnly = true)
    public MatriculaDto getMatriculaPorId(int id) {
        Optional<MatriculaModel> matriculaOptional = matriculaRepository.findById(id);
        MatriculaModel matriculaModel = matriculaOptional.orElseThrow(() -> new ObjectNotFoundException("Matrícula não encontrada: " + id));
        return matriculaModel.toDto();
    }

    @Transactional
    public MatriculaDto registroMatricula(MatriculaModel matricula) {
        try {
            return matriculaRepository.save(matricula).toDto();
        } catch (Exception e) {
            throw new ObjectNotFoundException("Erro ao registrar matrícula: " + e.getMessage());
        }
    }

    @Transactional
    public void updateMatricula(MatriculaModel matricula) {
        try {
            Optional<MatriculaModel> matriculaOptional = matriculaRepository.findById(matricula.getId());
            if (matriculaOptional.isPresent()) {
                matriculaRepository.save(matricula);
            } else {
                throw new ObjectNotFoundException("Matrícula não encontrada: " + matricula.getId());
            }
        } catch (Exception e) {
            throw new ObjectNotFoundException("Erro ao atualizar matrícula: " + e.getMessage());
        }
    }

    @Transactional
    public void deleteMatriculaPorId(int id) {
        if (!verificaMatricula(id)) {
            throw new ObjectNotFoundException("Matrícula não encontrada: " + id);
        }
        try {
            matriculaRepository.deleteById(id);
        } catch (Exception e) {
            throw new ObjectNotFoundException("Erro ao deletar matrícula: " + e.getMessage());
        }
    }

    public boolean verificaMatricula(int id) {
        return matriculaRepository.existsById(id);
    }

}
