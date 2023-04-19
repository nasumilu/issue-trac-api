package io.nasumilu.issuetrac.repository;

import io.nasumilu.issuetrac.entity.Issue;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.List;

@RepositoryRestResource
@CrossOrigin(origins = {"http://localhost:8080", "https://nasumilu.io"})
public interface IssueRepository extends CrudRepository<Issue, Long>, PagingAndSortingRepository<Issue, Long> {


    @Query(
            value = "SELECT * FROM find_issues_within_extent(:xmax, :ymax, :xmin, :ymin, :srid)",
            countQuery = "SELECT COUNT(*) FROM find_issues_within_extent(:xmax, :ymax, :xmin, :ymin, :srid)",
            nativeQuery = true
    )
    List<Issue> findAllWithinBbox(@Param("xmax") Double xmax,
                                  @Param("ymax") Double ymax,
                                  @Param("xmin") Double xmin,
                                  @Param("ymin") Double ymin,
                                  @Param("srid") Integer srid,
                                  Pageable pageable);

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