package br.edu.ifs.apinewsigaa.service;

import br.edu.ifs.apinewsigaa.exception.ObjectNotFoundException;
import br.edu.ifs.apinewsigaa.model.TurmaModel;
import br.edu.ifs.apinewsigaa.repository.TurmaRepository;
import br.edu.ifs.apinewsigaa.rest.Dtos.TurmaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service

public class TurmaService {

    @Autowired
    private TurmaRepository turmaRepository;

    @Transactional(readOnly = true)
    public List<TurmaDto> getTurmas() {
        List<TurmaModel> turmas = turmaRepository.findAll();
        return turmas.stream().map(TurmaModel::toDto).toList();
    }

    @Transactional(readOnly = true)
    public TurmaDto getPorId(Integer id) {
        Optional<TurmaModel> turmaOptional = turmaRepository.findById(id);
        TurmaModel turmaModel = turmaOptional.orElseThrow(() -> new ObjectNotFoundException("Turma não encontrada: " + id));
        return turmaModel.toDto();
    }

    @Transactional
    public TurmaDto registroTurma(TurmaModel turma) {
        try {
            return turmaRepository.save(turma).toDto();
        } catch (Exception e) {
            throw new ObjectNotFoundException("Erro ao salvar turma: " + e.getMessage());
        }
    }

    @Transactional
    public void updateTurma(TurmaModel turma) {
        try {
            Optional<TurmaModel> turmaOptional = turmaRepository.findById(turma.getId());
            if (turmaOptional.isPresent()) {
                turmaRepository.save(turma);
            } else {
                throw new ObjectNotFoundException("Turma não encontrada: " + turma.getId());
            }
        } catch (Exception e) {
            throw new ObjectNotFoundException("Erro ao atualizar turma: " + e.getMessage());
        }
    }

    @Transactional
    public void deleteTurmaPorId(Integer id) {
        if (!verificaExistenciaTurma(id)) {
            throw new ObjectNotFoundException("Turma não encontrada: " + id);
        }
        try {
            turmaRepository.deleteById(id);
        } catch (Exception e) {
            throw new ObjectNotFoundException("Erro ao deletar turma: " + e.getMessage());
        }
    }


    public boolean verificaExistenciaTurma(Integer id) {
        return turmaRepository.existsById(id);
    }

}
