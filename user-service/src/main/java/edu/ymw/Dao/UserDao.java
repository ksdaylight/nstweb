package edu.ymw.Dao;
import edu.ymw.pojo.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  UserDao extends JpaRepository<User,Integer>{
    User findByName(String name);

}
