package io.nasumilu.issuetrac.repository;

import io.nasumilu.issuetrac.entity.Issue;
import io.nasumilu.issuetrac.entity.Media;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource
public interface MediaRepository extends CrudRepository<Media, Long>, PagingAndSortingRepository<Media, Long> {

    @RestResource(path = "by-issue")
    List<Media> findMediasByIssue(Issue issue, Pageable page);

    @Override
    @RestResource(exported = false)
    void delete(Media media);

    @Override
    @RestResource(exported = false)
    void deleteAll();

    @Override
    @RestResource(exported = false)
    void deleteById(Long id);

}