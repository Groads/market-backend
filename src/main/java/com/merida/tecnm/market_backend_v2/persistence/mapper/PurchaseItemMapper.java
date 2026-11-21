package com.merida.tecnm.market_backend_v2.persistence.mapper;

import com.merida.tecnm.market_backend_v2.domain.PurchaseItem;
import com.merida.tecnm.market_backend_v2.persistence.entity.CompraProducto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PurchaseItemMapper {

    @Mapping(source = "id.idProducto", target = "productId")
    @Mapping(source = "cantidad", target = "quantity")
    @Mapping(source = "estado", target = "active")
    PurchaseItem toPurchaseItem(CompraProducto entity);

    @Mapping(source = "productId", target = "id.idProducto")
    @Mapping(source = "quantity", target = "cantidad")
    @Mapping(source = "active", target = "estado")
    @Mapping(target = "id.idCompra", ignore = true)
    @Mapping(target = "compra", ignore = true)
    @Mapping(target = "producto", ignore = true)
    CompraProducto toCompraProducto(PurchaseItem item);
}
