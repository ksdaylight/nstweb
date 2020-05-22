package edu.ymw.dao;

import edu.ymw.pojo.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public  interface  ReportDao extends JpaRepository<Report,Integer> {
}
