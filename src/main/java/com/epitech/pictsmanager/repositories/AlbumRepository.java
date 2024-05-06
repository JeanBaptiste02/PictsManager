package com.epitech.pictsmanager.repositories;

import com.epitech.pictsmanager.entity.Album;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
public class AlbumRepository implements JpaRepository<Album, Long> {

    @Override
    public List<Album> findAll() {
        return List.of();
    }

    @Override
    public List<Album> findAll(Sort sort) {
        return List.of();
    }

    @Override
    public Page<Album> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Album> findAllById(Iterable<Long> longs) {
        return List.of();
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void delete(Album entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Album> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Album> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Album> List<S> saveAll(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public Optional<Album> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Album> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Album> List<S> saveAllAndFlush(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public void deleteAllInBatch(Iterable<Album> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Album getOne(Long aLong) {
        return null;
    }

    @Override
    public Album getById(Long aLong) {
        return null;
    }

    @Override
    public <S extends Album> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Album> List<S> findAll(Example<S> example) {
        return List.of();
    }

    @Override
    public <S extends Album> List<S> findAll(Example<S> example, Sort sort) {
        return List.of();
    }

    @Override
    public <S extends Album> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Album> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Album> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Album, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

}
