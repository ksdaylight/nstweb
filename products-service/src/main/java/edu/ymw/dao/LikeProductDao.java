package edu.ymw.dao;


import edu.ymw.pojo.LikeProduct;
import edu.ymw.pojo.Product;
import edu.ymw.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeProductDao extends JpaRepository<LikeProduct,Integer> {
    void deleteByUIdAndLikeId(int userId,int LikeId);
    LikeProduct findByUIdAndLikeId(int userId,int LikeId);
}
