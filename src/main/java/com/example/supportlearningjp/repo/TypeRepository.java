package com.example.supportlearningjp.repo;

import com.example.supportlearningjp.model.TypeWord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepository extends JpaRepository<TypeWord,Integer> {
}
