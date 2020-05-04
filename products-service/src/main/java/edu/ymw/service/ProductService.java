package edu.ymw.service;
import edu.ymw.dao.ProductDao;
import edu.ymw.pojo.Product;
import edu.ymw.pojo.Template;
import edu.ymw.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames="products")
public class ProductService {

    @Autowired ProductDao productDao;

    @CacheEvict(allEntries=true)
    public void add(Product bean) {
        productDao.save(bean);
    }
    @CacheEvict(allEntries=true)
    public void delete(int id) {
        productDao.deleteById(id);
    }
    @Cacheable(key="'products-one-'+ #p0")
    public Product get(int id) {
        return productDao.findById(id).orElse(null);
    }
    @CacheEvict(allEntries=true)
    public void update(Product bean) {
        productDao.save(bean);
    }
    @Cacheable(key="'products-page-'+#p0+ '-' + #p1")
    public Page4Navigator<Product> list(int start, int size, int navigatePages) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(start, size,sort);
        Page pageFromJPA = productDao.findAll(pageable);

        return new Page4Navigator<>(pageFromJPA,navigatePages);
    }
}
