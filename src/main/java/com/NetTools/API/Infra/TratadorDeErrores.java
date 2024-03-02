package com.NetTools.API.Infra;

import com.NetTools.API.Infra.Exceptions.ArchivoYaExistenteException;
import com.NetTools.API.Infra.Exceptions.LocationNotFoundException;
import com.NetTools.API.Infra.Exceptions.NombreArchivoVacioException;
import com.NetTools.API.Infra.Exceptions.ProductNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.List;

@RestControllerAdvice
public class TratadorDeErrores extends Exception{
    // Not found
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity  tratarError404(){
        return ResponseEntity.notFound().build();
    }

    // //para arrojar el 404 en vez del 500 serverError
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity  tratarError400(MethodArgumentNotValidException e){
        List<DatosErrorValidacion> errores=e.getFieldErrors().stream().map(DatosErrorValidacion::new).toList();

        return ResponseEntity.badRequest().body(errores);
    }


    @ExceptionHandler(NombreArchivoVacioException.class)
    public ResponseEntity<String> manejarNombreArchivoVacioException(NombreArchivoVacioException e) {
        // Puedes personalizar el mensaje de error que se envía al cliente
        String mensajeError = "Error relacionado con el nombre del archivo: " + e.getMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensajeError);
    }
    @ExceptionHandler(ArchivoYaExistenteException.class)
    public ResponseEntity<String> manejarArchivoYaExistenteException(NombreArchivoVacioException e) {
        // Puedes personalizar el mensaje de error que se envía al cliente
        String mensajeError = "El Archivo ya existe en el servidor: " + e.getMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensajeError);
    }

    @ExceptionHandler(LocationNotFoundException.class)
    public ResponseEntity<String> manejarUbicacionNoEncontrada(LocationNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> manejarProductoNoEncontrado(ProductNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> tratarErrorGeneral(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error inesperado: " + e.getMessage());
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
