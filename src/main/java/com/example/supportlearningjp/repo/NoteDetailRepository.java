package com.example.supportlearningjp.repo;

import com.example.supportlearningjp.model.NoteDetail;
import com.example.supportlearningjp.model.NoteDetailId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface NoteDetailRepository extends JpaRepository<NoteDetail, NoteDetailId> {

}
