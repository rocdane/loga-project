package com.loga.microservices.sms.service;

import com.loga.microservices.sms.entity.Product;
import java.util.List;

/**
 * Interface de l'application LoGa représentant les opérations du service Vente
 * @author rochdane sabi rocdanesabi@gmail.com
 * @version 2.1
 * @since 2.0
 */
public interface IProductService {
    Product create(Product product);
    List<Product> list();
    List<Product> search(String text);
    Product read(Long id);
    Product read(String txt);
    boolean edit(Product up);
}
