package br.edu.ifs.apinewsigaa.service;

import br.edu.ifs.apinewsigaa.exception.ObjectNotFoundException;
import br.edu.ifs.apinewsigaa.model.AlunoModel;
import br.edu.ifs.apinewsigaa.model.DisciplinaModel;
import br.edu.ifs.apinewsigaa.model.ProfessorModel;
import br.edu.ifs.apinewsigaa.repository.ProfessorRepository;
import br.edu.ifs.apinewsigaa.rest.Dtos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class ProfessorService {
    @Autowired
    private ProfessorRepository professorRepository;

    @Transactional(readOnly = true)
    public List<ProfessorDto> getProfessores() {
        List<ProfessorModel> professores = professorRepository.findAll();
        return professores.stream().map(ProfessorModel::toDto).toList();
    }

    @Transactional(readOnly = true)
    public ProfessorDto getProfessorPorId(int id) {
        Optional<ProfessorModel> professorOptional = professorRepository.findById(id);
        ProfessorModel professorModel = professorOptional.orElseThrow(() -> new ObjectNotFoundException("Professor não encontrado: " + id));
        return professorModel.toDto();
    }

    @Transactional
    public ProfessorDto registroProfessor(ProfessorModel professor) {
        try {
            return professorRepository.save(professor).toDto();
        } catch (Exception e) {
            throw new ObjectNotFoundException("Erro ao salvar professor: " + e.getMessage());
        }
    }

    @Transactional
    public void updateProfessor(ProfessorModel professor) {
        try {
            Optional<ProfessorModel> professorOptional = professorRepository.findById(professor.getId());
            if (professorOptional.isPresent()) {
                professorRepository.save(professor);
            } else {
                throw new ObjectNotFoundException("Professor não encontrado: " + professor.getNome());
            }
        } catch (Exception e) {
            throw new ObjectNotFoundException("Erro ao atualizar professor: " + e.getMessage());
        }
    }

    @Transactional
    public void deleteProfessorPorId(int id) {
        if (!verificaExistenciaProfessor(id)) {
            throw new ObjectNotFoundException("Professor não encontrado: " + id);
        }

        try {
            professorRepository.deleteById(id);
        } catch (Exception e) {
            throw new ObjectNotFoundException("Erro ao deletar professor: " + e.getMessage());
        }
    }

    @Transactional(readOnly = true)
    public ProfessorModel getProfessorPorMatricula(String matricula) {
        return professorRepository.findByMatricula(matricula)
                .orElseThrow(() -> new ObjectNotFoundException("Professor com a matrícula: " + matricula +  " não encontrado"));
    }

    @Transactional(readOnly = true)
    public ProfessorDisciplinasDto getProfessorDisciplinas(String matricula) {
        ProfessorModel professor = getProfessorPorMatricula(matricula);

        List<DisciplinaDto> disciplinasProfessor = professor.getDisciplinas()
                .stream()
                .map(DisciplinaModel::toDto)
                .collect(Collectors.toList());

        ProfessorDisciplinasDto professorDisciplinasDto = new ProfessorDisciplinasDto();
        professorDisciplinasDto.setProfessor(professor.toDto());
        professorDisciplinasDto.setDisciplinas(disciplinasProfessor);

        return professorDisciplinasDto;
    }

    @Transactional(readOnly = true)
    public ProfessorDisciplinasAlunosDto getProfessorDisciplinasAlunos(String matricula) {
        ProfessorModel professor = getProfessorPorMatricula(matricula);

        ProfessorDisciplinasAlunosDto professorDisciplinasAlunosDto = new ProfessorDisciplinasAlunosDto();
        professorDisciplinasAlunosDto.setProfessor(professor.toDto());

        List<DisciplinaComAlunosDto> disciplinasComAlunos = professor.getDisciplinas()
                .stream()
                .map(disciplina -> {
                    DisciplinaComAlunosDto disciplinaComAlunosDto = new DisciplinaComAlunosDto();
                    disciplinaComAlunosDto.setDisciplina(disciplina.toDto());

                    List<AlunoDto> alunos = disciplina.getAlunos()
                            .stream()
                            .map(AlunoModel::toDto)
                            .collect(Collectors.toList());

                    disciplinaComAlunosDto.setAlunos(alunos);
                    return disciplinaComAlunosDto;
                }).collect(Collectors.toList());

        professorDisciplinasAlunosDto.setDisciplinas(disciplinasComAlunos);
        return professorDisciplinasAlunosDto;

    }


    public boolean verificaExistenciaProfessor(int id) {
        return professorRepository.existsById(id);
    }

}
