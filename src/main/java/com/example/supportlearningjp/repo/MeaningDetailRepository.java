package com.example.supportlearningjp.repo;

import com.example.supportlearningjp.model.MeaningDetail;
import com.example.supportlearningjp.model.MeaningDetailId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeaningDetailRepository extends JpaRepository<MeaningDetail, MeaningDetailId> {
}
