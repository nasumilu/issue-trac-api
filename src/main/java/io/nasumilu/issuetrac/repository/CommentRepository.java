package io.nasumilu.issuetrac.repository;

import io.nasumilu.issuetrac.entity.Comment;
import io.nasumilu.issuetrac.entity.Issue;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource
public interface CommentRepository extends CrudRepository<Comment, Long>,  PagingAndSortingRepository<Comment, Long> {

    @RestResource(path = "by-issue")
    List<Comment> findCommentsByIssue(Issue issue, Pageable page);

    @Override
    @RestResource(exported = false)
    void delete(Comment comment);

    @Override
    @RestResource(exported = false)
    void deleteAll();

    @Override
    @RestResource(exported = false)
    void deleteById(Long id);

}