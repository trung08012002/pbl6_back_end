package com.example.supportlearningjp.repo;

import com.example.supportlearningjp.model.ExampleDetail;
import com.example.supportlearningjp.model.ExampleDetailId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface ExampleDetailRepository extends JpaRepository<ExampleDetail, ExampleDetailId> {
}
