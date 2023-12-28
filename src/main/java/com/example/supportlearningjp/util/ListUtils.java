package com.example.supportlearningjp.util;

import jakarta.persistence.Query;

import java.util.List;

public class ListUtils {
    private  ListUtils(){}
    public  static <T> List<T> safeList(Query query)
    {
        return (List<T>) query.getResultList();
    }
}
