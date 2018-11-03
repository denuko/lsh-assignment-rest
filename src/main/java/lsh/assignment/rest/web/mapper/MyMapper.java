package lsh.assignment.rest.web.mapper;

import org.mapstruct.MappingTarget;

import java.util.List;

public interface MyMapper<E, D> {
    E mapToEntity(D dto);

    D mapToDto(E entity);

    List<E> mapToEntity(List<D> dtoList);

    List<D> mapToDto(List<E> entityList);

    void mapToExistingEntity(D dto, @MappingTarget E entity);
}
