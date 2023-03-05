package io.nasumilu.issuetrac.repository;

import io.nasumilu.issuetrac.entity.Media;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface MediaRepository extends CrudRepository<Media, Long>, PagingAndSortingRepository<Media, Long> {

}