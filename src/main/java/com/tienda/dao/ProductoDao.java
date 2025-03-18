package com.tienda.dao;

import com.tienda.domain.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

public interface ProductoDao extends JpaRepository<Producto, Long> {

    public List<Producto> findByPrecioBetweenOrderByDescripcion(double precioInf, double precioSup);

    //Ejemplo de método utilizando Consultas con JPQL
    @Query(value = "SELECT a FROM Producto a where a.precio BETWEEN :precioInf AND :precioSup ORDER BY a.descripcion ASC")
    public List<Producto> metodoJPQL(@Param("precioInf") double precioInf, @Param("precioSup") double precioSup);

    //Ejemplo de método utilizando Consultas con SQL nativo
    @Query(nativeQuery = true,
            value = "SELECT * FROM producto where producto.precio BETWEEN :precioInf AND :precioSup ORDER BY producto.descripcion ASC")
    public List<Producto> metodoNativo(@Param("precioInf") double precioInf, @Param("precioSup") double precioSup);

    // Este metodo utilizado para consultar por nombre
    @Query("SELECT p FROM Producto p WHERE LOWER(p.descripcion) LIKE LOWER(CONCAT('%', :descripcion, '%'))")
    List<Producto> buscarPorDescripcion(@Param("descripcion") String descripcion);

    List<Producto> findByDescripcionContainingIgnoreCase(String descripcion);
}
