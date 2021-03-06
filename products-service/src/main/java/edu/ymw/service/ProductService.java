package edu.ymw.service;
import edu.ymw.dao.ProductDao;
import edu.ymw.pojo.Product;
import edu.ymw.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
    @Cacheable(key="'products-page-'+#p0+ '-' + #p1+ '-' + #p2 ")
    public Page4Navigator<Product> list(int start, int size,int pSort, int navigatePages) {
        Specification querySpecifi = new Specification() {
            @Nullable
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return null;
            }
        };
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        Date e = c.getTime();
//        String endTime=format.format(s);
//        String startTime="";
        switch(pSort){
            //太赶，不屑道Const中了
            case 0:
                c.add(Calendar.DATE, - 1);
                Date d = c.getTime();
                querySpecifi = tiaoJian(d,e);
//                startTime = format.format(d);
                break;
            case 1:
                c.add(Calendar.DATE, - 7);
                Date w = c.getTime();
                querySpecifi = tiaoJian(w,e);
//                startTime = format.format(w);
                break;
            case 2:
                c.add(Calendar.MONTH, -1);
                Date m = c.getTime();
                querySpecifi = tiaoJian(m,e);

//                startTime = format.format(m);
                break;
            case 3:
                c.add(Calendar.YEAR, -9);
                Date y = c.getTime();
                querySpecifi = tiaoJian(y,e);
//                startTime = format.format(y);
                break;

            default:
                c.add(Calendar.YEAR, -9);
                y = c.getTime();
                querySpecifi = tiaoJian(y,e);
        }
        Sort sort = new Sort(Sort.Direction.DESC, "score");
        Pageable pageable = new PageRequest(start, size,sort);
        Page pageFromJPA = productDao.findAll(querySpecifi,pageable);

        return new Page4Navigator<>(pageFromJPA,navigatePages);
    }

    private Specification tiaoJian(Date startTime, Date endTime){
        Specification querySpecifi = new Specification<Product>(){
            @Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();

                predicates.add(criteriaBuilder.between(root.get("create_time"),startTime,endTime));

                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        return querySpecifi;
    }
    public Page4Navigator<Product> search(String keyword, int start, int size, int navigatePages) {

        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(start, size, sort);
        Page<Product> pageFromJPA  =productDao.findByTitleLike("%"+keyword+"%",pageable);
        return new Page4Navigator<>(pageFromJPA,navigatePages);
    }
}
