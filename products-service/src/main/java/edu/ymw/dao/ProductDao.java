package edu.ymw.dao;
import java.util.List;

import edu.ymw.pojo.Product;
import edu.ymw.pojo.Template;
import edu.ymw.pojo.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDao extends JpaRepository<Product,Integer>{
    Page<Product> findByUser(User user,Pageable pageable);
    Page<Product> findByTemplate(Template template,Pageable pageable);
}
