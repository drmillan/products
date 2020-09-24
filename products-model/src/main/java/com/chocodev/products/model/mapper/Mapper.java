package com.chocodev.products.model.mapper;

public interface Mapper<T, Q> {
    Q map(T t);
}
