package com.example.supportlearningjp.repo;


import com.example.supportlearningjp.model.Katakana;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KatakanaRepository  extends JpaRepository<Katakana,Integer> {
}
