package br.edu.ifs.apinewsigaa.exception;


import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.HashMap;
import java.util.Map;

public class DataIntegrityException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public DataIntegrityException(String msg){
        super(msg);
    }
    public DataIntegrityException(String msg, Throwable cause){
        super(msg, cause);
    }

    public static String tratamentoErro(DataIntegrityViolationException e) {
        Throwable causa = e.getCause();
        if (causa instanceof ConstraintViolationException) {
            String msg = causa.getMessage();
            if (msg.contains("Unique index or primary key violation")) {
                Map<String, String> violacoes = new HashMap<>();
                violacoes.put("CELULAR", "CELULAR já cadastrado");
                violacoes.put("CPF", "CPF já cadastrado");
                violacoes.put("EMAIL", "EMAIL já cadastrado");
                violacoes.put("MATRICULA", "MATRICULA já cadastrada");
                violacoes.put("NOME", "NOME já cadastrado");

                for (Map.Entry<String, String> entry : violacoes.entrySet()) {
                    if (msg.contains(entry.getKey())) {
                        return entry.getValue();
                    }
                }
            }
        }
        return "Violação de integridade de Dados";
    }
}