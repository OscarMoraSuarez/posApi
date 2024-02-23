package com.NetTools.API.Infra;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.List;

@RestControllerAdvice
public class TratadorDeErrores extends Exception{
    // esta anotacion le dice a Spring que debe llamar a este metodo en caso de que
    // alguna excepcion sea detectada entre parentesis va el tipo de excepcion
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity  tratarError404(){
        //para arrojar el 404 en vez del 500 internal serverError
        return ResponseEntity.notFound().build();
    }

    // alguna excepcion sea detectada entre parentesis va el tipo de excepcion
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity  tratarError400(MethodArgumentNotValidException e){
        List<DatosErrorValidacion> errores=e.getFieldErrors().stream().map(DatosErrorValidacion::new).toList();
        //para arrojar el 404 en vez del 500 internal serverError
        return ResponseEntity.badRequest().body(errores);
    }

    private record DatosErrorValidacion(
            String campo,

            String error
            ){


        public DatosErrorValidacion(FieldError error) {
            this(error.getField(),error.getDefaultMessage());
        }
    }
}
