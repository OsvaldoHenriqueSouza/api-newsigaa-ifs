package br.edu.ifs.apinewsigaa.rest.controller;

import br.edu.ifs.apinewsigaa.model.TurmaModel;
import br.edu.ifs.apinewsigaa.rest.Dtos.TurmaDto;
import br.edu.ifs.apinewsigaa.service.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("/turmas")
public class TurmaController {
    @Autowired
    private TurmaService turmaService;

    @GetMapping
    public ResponseEntity<List<TurmaDto>> registroTurma(){
        List<TurmaDto> listTurmas = turmaService.getTurmas();
        return ResponseEntity.ok(listTurmas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TurmaDto> getTurma(int id){
        TurmaDto turma = turmaService.getPorId(id);
        return ResponseEntity.ok(turma);
    }

    @PostMapping
    public ResponseEntity<TurmaDto> registerTurma(@RequestBody TurmaModel turma){
        TurmaDto turmaDto = turmaService.registroTurma(turma);
        return ResponseEntity.ok(turmaDto);
    }

    @PutMapping
    public ResponseEntity<TurmaDto> updateTurma(@RequestBody TurmaModel turma){
        turmaService.updateTurma(turma);
        return ResponseEntity.ok(turma.toDto());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTurmaPorId(@PathVariable int id){
        turmaService.deleteTurmaPorId(id);
        return ResponseEntity.ok("Turma deletada com sucesso!");
    }
}
