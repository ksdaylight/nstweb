package edu.ymw.dao;

import edu.ymw.pojo.Shield;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShieldDao extends JpaRepository<Shield,Integer> {
    List<Shield> findByUId(int uId);

}
