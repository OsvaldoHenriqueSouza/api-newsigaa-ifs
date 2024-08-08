package br.edu.ifs.apinewsigaa.rest.controller;

import br.edu.ifs.apinewsigaa.model.AlunoModel;
import br.edu.ifs.apinewsigaa.rest.Dtos.AlunoDto;
import br.edu.ifs.apinewsigaa.service.AlunoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Validated
@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @GetMapping
    public ResponseEntity<List<AlunoDto>> getAllAlunos() {
        List<AlunoDto> listAlunos = alunoService.getAlunos();
        return ResponseEntity.ok(listAlunos);
    }

    @GetMapping("/{matricula}")
    public ResponseEntity<AlunoDto> getAluno(@PathVariable @NotNull String matricula) {
        AlunoDto aluno = alunoService.getPorMatricula(matricula);
        return ResponseEntity.ok(aluno);
    }

    @PostMapping
    public ResponseEntity<AlunoDto> registerAluno(@RequestBody @Valid AlunoModel alunoRequest) {
        AlunoDto aluno = alunoService.registroAluno(alunoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(aluno);
    }

    @PutMapping
    public ResponseEntity<AlunoDto> updateAluno(@RequestBody AlunoModel aluno) {
        alunoService.updateAluno(aluno);
        return ResponseEntity.ok(aluno.toDto());
    }

//    @DeleteMapping("/{matricula}")
//    public ResponseEntity<String> deleteAluno(@PathVariable String matricula) {
//        alunoService.deleteAlunoPorMatricula(matricula);
//        return ResponseEntity.ok("Aluno deletado com sucesso!");
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAlunoPorId(@PathVariable int id) {
        alunoService.deleteAlunoPorId(id);
        return ResponseEntity.ok("Aluno deletado com sucesso!");
    }
}