package com.example.supportlearningjp.repo;

import com.example.supportlearningjp.model.AmOn;
import com.example.supportlearningjp.model.AmOnId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HanTuAmOnRepository extends JpaRepository<AmOn, AmOnId> {
}
