package br.edu.ifs.apinewsigaa.exception;


import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;

public class DataIntegrityException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public DataIntegrityException(String msg){
        super(msg);
    }
    public DataIntegrityException(String msg, Throwable cause){
        super(msg, cause);
    }

    public static String extrairErro(DataIntegrityViolationException e){
        Throwable causa = e.getCause();
        if(causa instanceof ConstraintViolationException){
            String msg = causa.getMessage();
            if(msg.contains("Unique index or primary key violation")){
                if (msg.contains("CELULAR")){
                    return "CELULAR já cadastrado";
                } else if (msg.contains("CPF")) {
                    return "CPF já cadastrado";
                } else if (msg.contains("EMAIL")) {
                    return "EMAIL já cadastrado";
                } else if (msg.contains("MATRICULA")) {
                    return "MATRICULA já cadastrada";
                } else if (msg.contains("NOME")) {
                    return "NOME já cadastrado";
                }
            }
        }
        return "Violação de integridade de Dados";
    }
}