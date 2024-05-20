package com.NetTools.API.Dominio.producto;

import com.NetTools.API.Dominio.categoria.Categoria;
import com.NetTools.API.Dominio.categoria.CategoriaRepository;
import com.NetTools.API.Infra.Exceptions.ArchivoYaExistenteException;
import com.NetTools.API.Infra.Exceptions.NombreArchivoVacioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.*;

@Service
public class ProductoService {

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

    public String guardarArchivoEnCarpeta(MultipartFile imagen) throws IOException {
        // Obtener el nombre del archivo desde la ruta
        String nombreArchivo = imagen.getOriginalFilename();

        // Verificar si el nombre del archivo es nulo o vacío
        if (nombreArchivo == null || nombreArchivo.trim().isEmpty()) {
            throw new NombreArchivoVacioException("El nombre del archivo es nulo o vacío");
        }
        System.out.println(nombreArchivo);

        // Definir la carpeta de destino con una ruta absoluta
        String carpetaDestino = "/home/back/mediaFiles";

        // Verificar si la carpeta de destino existe y crearla si no existe
        Path directorioDestino = Paths.get(carpetaDestino);
        if (!Files.exists(directorioDestino)) {
            Files.createDirectories(directorioDestino);
        }

        // Definir la ruta completa de destino
        Path destino = directorioDestino.resolve(nombreArchivo);
        System.out.println("validando existencia del archivo en carpeta");

        // Verificar si el archivo ya existe en la carpeta de destino
        if (Files.exists(destino)) {
            throw new ArchivoYaExistenteException("El archivo ya existe en la carpeta de destino");
        }

        // Copiar el contenido del archivo al destino
        Files.copy(imagen.getInputStream(), destino, StandardCopyOption.REPLACE_EXISTING);

        // Construir la URL completa del archivo
        String urlCompleta = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/mediaFiles/" + nombreArchivo) // Asegúrate de que esta ruta coincida con la ruta del servidor
                .toUriString();

        // Devolver la URL completa
        return urlCompleta;
    }

    @Transactional
    public Producto actualizarProductoService(DatosActualizarProducto datosActualizarProducto){

        Categoria categoria=categoriaRepository.findByNombreCategoria(datosActualizarProducto.categoria());
        Producto producto = productoRepository.findById(datosActualizarProducto.id())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        producto.actualizarDatos(
                datosActualizarProducto.codigo(),
                datosActualizarProducto.descripcion(),
                categoria,
                datosActualizarProducto.marca(),
                datosActualizarProducto.precioEntrada(),
                datosActualizarProducto.precioSalida(),
                producto.getImagePath()
        );

        return productoRepository.save(producto);
    }

}




