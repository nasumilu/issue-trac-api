package io.nasumilu.issuetrac.repository;

import io.nasumilu.issuetrac.entity.Issue;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource
@CrossOrigin(origins = {"http://localhost:8080", "https://nasumilu.io"})
public interface IssueRepository extends CrudRepository<Issue, Long>, PagingAndSortingRepository<Issue, Long> {

    @Override
    @RestResource(exported = false)
    void deleteAll();

    @Override
    @RestResource(exported = false)
    void delete(Issue entity);

    @Override
    @RestResource(exported = false)
    void deleteById(Long id);

}