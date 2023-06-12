package com.example.graduatedemo.demos.web.repository;
import com.example.graduatedemo.demos.web.domain.Style;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StyleDao extends JpaRepository<Style, Long>{
    Style findByStyleid(Long styleid);
}
