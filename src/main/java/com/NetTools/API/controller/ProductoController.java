package com.NetTools.API.controller;




import com.NetTools.API.Dominio.producto.*;

import com.NetTools.API.Infra.Exceptions.ArchivoYaExistenteException;
import com.NetTools.API.Infra.Exceptions.NombreArchivoVacioException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/producto")
public class ProductoController {



    private final ProductoService productoService;
    private final ProductoRepository productoRepository;

    public ProductoController(ProductoRepository productoRepository, ProductoService productoService) {
        this.productoRepository = productoRepository;
        this.productoService=productoService;
    }

    @PostMapping
    public ResponseEntity registrarProducto(
            @RequestParam("imagen") MultipartFile imagen,
            @ModelAttribute @Valid DatosRegistroProducto datosRegistroProducto,
            UriComponentsBuilder uriComponentsBuilder)  {

        try {
            String imagePath = productoService.guardarArchivoEnCarpeta(imagen);

            Producto producto = productoService.registrar(
                    datosRegistroProducto.codigo(),
                    datosRegistroProducto.descripcion(),
                    datosRegistroProducto.nombreCategoria(),
                    datosRegistroProducto.marca(),
                    datosRegistroProducto.precioEntrada(),
                    datosRegistroProducto.precioSalida(),
                    imagePath);


            DatosDetalleProducto datosDetalleProducto = new DatosDetalleProducto(
                    producto.getProductoId(),
                    producto.getCodigo(),
                    producto.getDescripcion(),
                    producto.getCategoria().getCategoriaId(),
                    producto.getMarca(),
                    producto.getPrecioEntrada(),
                    producto.getPrecioSalida(),
                    producto.getImagePath());

            URI url = uriComponentsBuilder.path("/producto/{id}").buildAndExpand(producto.getProductoId()).toUri();
            return ResponseEntity.created(url).body(datosDetalleProducto);

        }  catch (NombreArchivoVacioException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: El nombre del archivo es nulo o vacío");

        } catch (ArchivoYaExistenteException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: El archivo ya existe en la carpeta de destino");

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar el archivo en la carpeta de destino");
        }
    }

    ////////////////////////////////////////////////////////
    @GetMapping
    public ResponseEntity<Page<DatosDetalleProducto>>  listadoProductos(@PageableDefault(size = 20,page = 0,sort="descripcion",direction = Sort.Direction.ASC) Pageable paginacion){
                                         //como el metodo devuelve objetos producto uso la Api stream y lo mapeo a el record
                                         //luego se conveirte a lista
        //return productoRepository.findAll(paginacion).stream().map(DatosDetalleProducto::new).toList();
        //return productoRepository.findAll(paginacion).map(DatosDetalleProducto::new);
        return ResponseEntity.ok(productoRepository.findByActivoTrue(paginacion).map(DatosDetalleProducto::new));
    }

    @GetMapping("all")
    public ResponseEntity<List<Producto>> obtenerProductosActivos() {
        List<Producto> productos = productoRepository.findByActivoTrue();
        return ResponseEntity.ok(productos);
    }



    //////////////////////////////////////////////////////
    @PutMapping
    public ResponseEntity<DatosDetalleProducto> actualizarProductoController(@RequestBody @Valid DatosActualizarProducto datosActualizarProducto){
        Producto productoActualizado=productoService.actualizarProductoService(datosActualizarProducto);
        if(productoActualizado!=null){
            DatosDetalleProducto datosDetalleProducto= new DatosDetalleProducto(productoActualizado);
            return ResponseEntity.ok(datosDetalleProducto);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }


    }


    //////////////////////////////////////////////////////
    //DELETE en DB
    @DeleteMapping
    @Transactional
    public ResponseEntity eliminarStock(@RequestBody Long id){
        Producto producto =productoRepository.getReferenceById(id);
        productoRepository.delete(producto);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Mensaje", "El producto con ID " + id + " ha sido eliminado exitosamente");
        return ResponseEntity.noContent().headers(headers).build();
    }


    //////////////////////////////////////////////////////
   /*@DeleteMapping("/{id}")
    @Transactional
    //ResponseEntity nos ayuda a retornar respuestas al cliente
    public ResponseEntity eliminarProductoLogico(@PathVariable Long id){
        Producto producto =productoRepository.getReferenceById(id);
        producto.desactivarProducto();
        //es necesario el build para setear el codigo de la respuesta y con build convertirlo a response Entity
        //noContent retorna un 204 ya que Spring mapea la respuesta a un 204
        //Es decir noContent() define el codigo 204 y el build() lo convierte a un responseEntity
        return ResponseEntity.noContent().build();
    }*/
    @DeleteMapping("/codigo/{codigo}")
    @Transactional
    public ResponseEntity eliminarProductoCoodigo(@PathVariable String codigo){
        Producto producto =productoRepository.getReferenceByCodigo(codigo);
        productoRepository.delete(producto);
        return ResponseEntity.noContent().build();
    }



    //////////////////////////////////////////////////////
    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<DatosDetalleProducto> productoPorCodigo(@PathVariable String codigo) {
        Producto producto = productoRepository.getProductoByCodigo(codigo);
        if(producto!=null){
            var datosProducto = new DatosDetalleProducto(
                    producto.getProductoId(),
                    producto.getCodigo(),
                    producto.getDescripcion(),
                    producto.getCategoria().getCategoriaId(),
                    producto.getMarca(),
                    producto.getPrecioEntrada(),
                    producto.getPrecioSalida(),
                    producto.getImagePath()
            );
            return ResponseEntity.ok(datosProducto);
        }else{
            throw new EntityNotFoundException("el codigo del producto no fue encontrado el producto no existe");
        }


    }
    @GetMapping("/{id}")
    public ResponseEntity<DatosDetalleProducto> productoPorId(@PathVariable Long id) {
        Producto producto = productoRepository.findByProductoId(id);
        if(producto!=null){
            var datosProducto = new DatosDetalleProducto(
                    producto.getProductoId(),
                    producto.getCodigo(),
                    producto.getDescripcion(),
                    producto.getCategoria().getCategoriaId(),
                    producto.getMarca(),
                    producto.getPrecioEntrada(),
                    producto.getPrecioSalida(),
                    producto.getImagePath()
            );
            return ResponseEntity.ok(datosProducto);
        }else{
            throw new EntityNotFoundException("el Id del producto no fue encontrado el producto no existe");
        }


    }

    @GetMapping("/count")
    public ResponseEntity<DatosConteoProducto> contarProductos() {
        Long totalProductos = productoRepository.contarProductos();
        if (totalProductos == null) {
            totalProductos = 0L; // Si el recuento es null, establece el valor en cero
        }
        DatosConteoProducto datosConteoProducto = new DatosConteoProducto(totalProductos);
        return ResponseEntity.ok(datosConteoProducto);
    }

}




