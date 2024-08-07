package br.edu.ifs.apinewsigaa.service;

import br.edu.ifs.apinewsigaa.exception.DataIntegrityException;
import br.edu.ifs.apinewsigaa.exception.ObjectNotFoundException;
import br.edu.ifs.apinewsigaa.model.DisciplinaModel;
import br.edu.ifs.apinewsigaa.repository.DisciplinaRepository;
import br.edu.ifs.apinewsigaa.rest.Dtos.DisciplinaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import static br.edu.ifs.apinewsigaa.exception.DataIntegrityException.tratamentoErro;

@Service
public class DisciplinaService {

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @Transactional
    public DisciplinaDto registroDisciplina(DisciplinaModel disciplina) {
        try {
            return disciplinaRepository.save(disciplina).toDto();
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException(tratamentoErro(e));
        }
    }

    @Transactional(readOnly = true)
    public List<DisciplinaDto> getDisciplinas() {
        List<DisciplinaModel> disciplinas = disciplinaRepository.findAll();
        return disciplinas.stream().map(DisciplinaModel::toDto).toList();
    }

    @Transactional(readOnly = true)
    public DisciplinaDto getPorNome(String nome) {
        return disciplinaRepository.findByNome(nome)
                .orElseThrow(() -> new ObjectNotFoundException("Disciplina não encontrada: " + nome)).toDto();
    }

    public void updateDisciplina(DisciplinaModel disciplina) {

        try {
            Optional<DisciplinaModel> disciplinaOptional = disciplinaRepository.findById(disciplina.getId());
            if (disciplinaOptional.isPresent()) {
                disciplinaRepository.save(disciplina);
            } else {
                throw new ObjectNotFoundException("Disciplina não encontrada: " + disciplina.getNome());
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException(tratamentoErro(e));
        }
    }

    @Transactional
    public void deleteDisciplina(String nome) {
        DisciplinaModel disciplinaModel = disciplinaRepository.findByNome(nome)
                .orElseThrow(() -> new ObjectNotFoundException("Disciplina não encontrada: " + nome));
        disciplinaRepository.deleteByNome(disciplinaModel.getNome());
    }
}
