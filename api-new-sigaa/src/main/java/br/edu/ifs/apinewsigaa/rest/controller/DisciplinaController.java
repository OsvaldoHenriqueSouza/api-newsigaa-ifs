package br.edu.ifs.apinewsigaa.rest.controller;

import br.edu.ifs.apinewsigaa.model.DisciplinaModel;
import br.edu.ifs.apinewsigaa.rest.Dtos.DisciplinaDto;
import br.edu.ifs.apinewsigaa.service.DisciplinaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Validated
@RestController
@RequestMapping("/disciplinas")
public class DisciplinaController {
    @Autowired
    private DisciplinaService disciplinaService;

    @PostMapping
    public ResponseEntity<DisciplinaDto> registroDisciplina(@RequestBody @Valid DisciplinaModel disciplina) {
        DisciplinaDto disciplinaDto = disciplinaService.registroDisciplina(disciplina);
        return ResponseEntity.status(HttpStatus.CREATED).body(disciplinaDto);
    }

    @GetMapping
    public ResponseEntity<?> getDisciplinas() {
        List<DisciplinaDto> disciplinas = disciplinaService.getDisciplinas();
        return ResponseEntity.ok(disciplinas);
    }

    @GetMapping("/{nome}")
    public ResponseEntity<DisciplinaDto> getDisciplina(@PathVariable String nome) {
        DisciplinaDto disciplina = disciplinaService.getPorNome(nome);
        return ResponseEntity.ok(disciplina);
    }

    @PutMapping
    public ResponseEntity<DisciplinaDto> updateDisciplina(@RequestBody DisciplinaModel disciplina) {
        disciplinaService.updateDisciplina(disciplina);
        return ResponseEntity.ok(disciplina.toDto());
    }

    @DeleteMapping("/{nome}")
    public ResponseEntity<String> deleteDisciplina(@PathVariable String nome) {
        disciplinaService.deleteDisciplina(nome);
        return ResponseEntity.ok("Disciplina deletada com sucesso!");
    }
}
