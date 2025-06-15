package dev.mordechai.studentcoursemanager.mapper;

public interface BaseMapper<E, C, U, R> {
    E toEntity(C createRequest);
    R toResponse(E entity);
    void updateEntity(E entity, U updateRequest);
} 