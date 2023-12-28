package com.example.supportlearningjp.repo;

import com.example.supportlearningjp.model.NguPhap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface NguPhapRepository  extends JpaRepository<NguPhap, Integer> {
}
