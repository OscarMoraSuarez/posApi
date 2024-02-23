package com.NetTools.API.Dominio.producto;

import com.NetTools.API.Dominio.categoria.Categoria;
import com.NetTools.API.Dominio.categoria.CategoriaRepository;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.*;

@Service
public class RegistroDeProductoService {

    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private ProductoRepository productoRepository;
    @Transactional
    public Producto registrar(String codigo, String descripcion, String nombreCategoria, String marca, BigDecimal precioEntrada,BigDecimal precioSalida,String imagePath){

        Categoria categoria = seleccionarCategoria(nombreCategoria);

        if (categoria == null) {
            System.out.println("No existe esa categoria");// aca puedo poner una excepcion   no la categoria la trae el repositorio
            // Aquí deberías manejar el caso en el que la categoría no existe.
        }

        Producto producto = new Producto(
                codigo,
                descripcion,
                categoria,
                marca,
                precioEntrada,
                precioSalida,
                imagePath
        );

        productoRepository.save(producto);

        return producto; //aca le retorno el producto al controller y  el controller lo mapea al dto
    }

    private Categoria seleccionarCategoria(String nombreCategoria) {
        if (nombreCategoria != null) {
            return categoriaRepository.findByNombreCategoria(nombreCategoria);
        }
        return null;
    }

    public String guardarArchivoEnCarpeta(MultipartFile imagen)throws IOException {
        // Obtener el nombre del archivo desde la ruta

        String nombreArchivo =imagen.getOriginalFilename();

        if (nombreArchivo == null || nombreArchivo.trim().isEmpty()) {
            // Manejar el caso cuando el nombre del archivo es null o vacío
            throw new IllegalArgumentException("El nombre del archivo es nulo o vacío");
        }
        System.out.println(nombreArchivo);
        String carpetaDestino = "mediaFiles";

        // Verificar si la carpeta de destino existe
        Path directorioDestino = Paths.get(carpetaDestino);
        if (!Files.exists(directorioDestino)) {
            Files.createDirectories(directorioDestino);
        }

        // Ruta completa de destino
        Path destino = directorioDestino.resolve(nombreArchivo);
        System.out.println("validando existencia del archivo en carpeta");
        // Verificar si el archivo existe en la carpeta
        if (Files.exists(destino)) {
            throw new FileAlreadyExistsException("El archivo ya existe en la carpeta de destino");
        }

        // Copiar el contenido del archivo al destino
        Files.copy(imagen.getInputStream(),destino,StandardCopyOption.REPLACE_EXISTING);

        // Construir la URL completa
        String urlCompleta = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/" + carpetaDestino + "/" + nombreArchivo)
                .toUriString();

        return urlCompleta;
    }


}




