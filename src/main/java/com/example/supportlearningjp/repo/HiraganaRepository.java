package com.example.supportlearningjp.repo;

import com.example.supportlearningjp.model.HanViet;
import com.example.supportlearningjp.model.Hiragana;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HiraganaRepository  extends JpaRepository<Hiragana,Integer> {
}
