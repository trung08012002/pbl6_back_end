package com.example.supportlearningjp.repo;

import com.example.supportlearningjp.model.HanViet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HanVietRepository extends JpaRepository<HanViet,Integer> {
}
