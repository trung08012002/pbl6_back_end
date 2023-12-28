package com.example.supportlearningjp.repo;

import com.example.supportlearningjp.model.AmKun;
import com.example.supportlearningjp.model.AmKunId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HanTuAmKunRepository extends JpaRepository<AmKun, AmKunId> {
}
