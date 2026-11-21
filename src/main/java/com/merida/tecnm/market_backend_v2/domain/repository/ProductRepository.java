package com.merida.tecnm.market_backend_v2.domain.repository;

import com.merida.tecnm.market_backend_v2.domain.Product;
import java.util.*;
public interface ProductRepository {
 List<Product> getAll();
 Optional<List<Product>> getByCategory(int categoryId);
 Optional<List<Product>> getScarceProducts(int quantity);
 Optional<Product> getProduct(int productId);
 Product save(Product value);

 Product delete(Product product);

 void delete(int id);
}
