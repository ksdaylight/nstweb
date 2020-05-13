package edu.ymw.dao;


import edu.ymw.pojo.LikeProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeProductDao extends JpaRepository<LikeProduct,Integer> {
    void deleteByUIdAndLikeId(int userId,int LikeId);
    LikeProduct findByUIdAndLikeId(int userId,int LikeId);
}
