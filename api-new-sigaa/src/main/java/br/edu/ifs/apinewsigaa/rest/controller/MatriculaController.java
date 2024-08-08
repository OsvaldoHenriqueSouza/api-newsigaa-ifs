package br.edu.ifs.apinewsigaa.rest.controller;

import br.edu.ifs.apinewsigaa.model.MatriculaModel;
import br.edu.ifs.apinewsigaa.rest.Dtos.MatriculaDto;
import br.edu.ifs.apinewsigaa.service.MatriculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("/matriculas")
public class MatriculaController {
    @Autowired
    private MatriculaService matriculaService;
    @GetMapping
    public ResponseEntity<List<MatriculaDto>> registroMatricula(){
        List<MatriculaDto> listMatriculas = matriculaService.getMatriculas();
        return ResponseEntity.ok(listMatriculas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MatriculaDto> getMatricula(int id){
        MatriculaDto matricula = matriculaService.getMatriculaPorId(id);
        return ResponseEntity.ok(matricula);
    }

    @PostMapping
    public ResponseEntity<MatriculaDto> registerMatricula(@RequestBody MatriculaModel matricula){
        MatriculaDto matriculaDto = matriculaService.registroMatricula(matricula);
        return ResponseEntity.ok(matriculaDto);
    }

    @PutMapping
    public ResponseEntity<MatriculaDto> updateMatricula(@RequestBody MatriculaModel matricula){
        matriculaService.updateMatricula(matricula);
        return ResponseEntity.ok(matricula.toDto());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMatriculaPorId(@PathVariable int id){
        matriculaService.deleteMatriculaPorId(id);
        return ResponseEntity.ok("Matrícula deletada com sucesso!");
    }
}
