package com.barros.publication.service;

import com.barros.publication.domain.Comment;
import com.barros.publication.domain.Publication;
import com.barros.publication.mapper.PublicationMapper;
import com.barros.publication.repository.PublicationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PublicationService {

    @Autowired
    private PublicationRepository publicationRepository;

    @Autowired
    private PublicationMapper publicationMapper;

    @Autowired
    private CommentService commentService;

    public void insert(Publication publication) {
        publicationRepository.save(publicationMapper.toPublicationEntity(publication));
    }

    public List<Publication> findAll() {
        return publicationRepository.findAll().stream()
                .map(publicationMapper::toPublication)
                .toList();
    }

    public Publication findById(String id) {
        final Publication publication = publicationRepository.findById(id)
                .map(publicationMapper::toPublication)
                .orElseThrow(RuntimeException::new);

        final List<Comment> comments = commentService.getComments(id);
        publication.setComments(comments);
        return publication;
    }
}
