package com.merida.tecnm.market_backend_v2.persistence.crud;

import com.merida.tecnm.market_backend_v2.persistence.entity.Producto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductoCrudRepository extends CrudRepository<Producto, Integer> {

    /* SQL Query
    SELECT *
    FROM productos
    WHERE id=categoria = 10?
    ORDER BY nombre ASC
     */

    List<Producto> findByIdCategoriaOrderByNombreAsc(int idCategoria);

    //Cantidad stock

    Optional<List<Producto>> findByCantidadStockLessThenAndEstado(int cantidadStock, boolean estado);

}
