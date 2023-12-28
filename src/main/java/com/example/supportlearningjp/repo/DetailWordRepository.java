package com.example.supportlearningjp.repo;

import com.example.supportlearningjp.model.DetailWord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailWordRepository extends JpaRepository<DetailWord,Integer> {
}
