package com.example.supportlearningjp.repo;

import com.example.supportlearningjp.model.HanTuMeaning;
import com.example.supportlearningjp.model.HanTuMeaningId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HanTuMeaningRepository extends JpaRepository<HanTuMeaning, HanTuMeaningId> {
}
