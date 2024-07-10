package com.example.ticaretappv0.services;

import java.util.List;

public interface TypeService<T> {
    void saveAll(List<?> entities);
}
