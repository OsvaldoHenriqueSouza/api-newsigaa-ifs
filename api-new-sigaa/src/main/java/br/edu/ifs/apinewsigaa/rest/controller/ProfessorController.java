package br.edu.ifs.apinewsigaa.rest.controller;


import br.edu.ifs.apinewsigaa.model.ProfessorModel;
import br.edu.ifs.apinewsigaa.rest.Dtos.ProfessorDisciplinasAlunosDto;
import br.edu.ifs.apinewsigaa.rest.Dtos.ProfessorDisciplinasDto;
import br.edu.ifs.apinewsigaa.rest.Dtos.ProfessorDto;
import br.edu.ifs.apinewsigaa.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("/professores")
public class ProfessorController {
    @Autowired
    private ProfessorService professorService;

    @GetMapping
    public ResponseEntity<List<ProfessorDto>> registroProfessor() {
        List<ProfessorDto> listProfessores = professorService.getProfessores();
        return ResponseEntity.ok(listProfessores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessorDto> getProfessor(@PathVariable int id) {
        ProfessorDto professor = professorService.getProfessorPorId(id);
        return ResponseEntity.ok(professor);
    }

    @PostMapping
    public ResponseEntity<ProfessorDto> registerProfessor(@RequestBody ProfessorModel professor) {
        ProfessorDto professorDto = professorService.registroProfessor(professor);
        return ResponseEntity.ok(professorDto);
    }

    @PutMapping
    public ResponseEntity<ProfessorDto> updateProfessor(@RequestBody ProfessorModel professor) {
        professorService.updateProfessor(professor);
        return ResponseEntity.ok(professor.toDto());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProfessorPorId(@PathVariable int id) {
        professorService.deleteProfessorPorId(id);
        return ResponseEntity.ok("Professor deletado com sucesso!");
    }

    @GetMapping("/professorDisciplinas/{matricula}")
    public ResponseEntity<ProfessorDisciplinasDto> getProfessorDisciplinas(@PathVariable String matricula) {
        ProfessorDisciplinasDto professorDisciplinasDto = professorService.getProfessorDisciplinas(matricula);
        return ResponseEntity.ok(professorDisciplinasDto);
    }

    @GetMapping("/professorDisciplinaAlunos/{matricula}")
    public ResponseEntity<ProfessorDisciplinasAlunosDto> getProfessorDisciplinasAlunos(@PathVariable String matricula) {
        ProfessorDisciplinasAlunosDto professorDisciplinasAlunosDto = professorService.getProfessorDisciplinasAlunos(matricula);
        return ResponseEntity.ok(professorDisciplinasAlunosDto);
    }
}