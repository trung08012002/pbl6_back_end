package com.example.supportlearningjp.config.Converter;

import com.example.supportlearningjp.dto.NoteDetailDto;
import com.example.supportlearningjp.model.NoteDetail;
import com.example.supportlearningjp.model.NoteDetailId;
import org.modelmapper.AbstractConverter;

public class NoteDetailConverter  extends AbstractConverter<NoteDetailDto, NoteDetail> {
    @Override
    protected NoteDetail convert(NoteDetailDto source) {
        NoteDetail noteDetail=new NoteDetail();
        NoteDetailId noteDetailId=new NoteDetailId();
        noteDetailId.setNote(source.getNoteDetail());
        noteDetail.setNoteDetailId(noteDetailId);
        return  noteDetail;
    }
}
