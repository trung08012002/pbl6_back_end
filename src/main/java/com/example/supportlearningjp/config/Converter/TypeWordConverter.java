package com.example.supportlearningjp.config.Converter;

import com.example.supportlearningjp.dto.TypeWordDto;
import com.example.supportlearningjp.model.TypeWord;
import org.modelmapper.AbstractConverter;

public class TypeWordConverter  extends AbstractConverter<TypeWordDto, TypeWord> {
    @Override
    protected TypeWord convert(TypeWordDto source) {
       TypeWord typeWord=new TypeWord();
       typeWord.setType(source.getType());
       typeWord.setId(source.getId());
       return  typeWord;
    }
}
