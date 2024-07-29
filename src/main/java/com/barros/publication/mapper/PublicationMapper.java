package com.barros.publication.mapper;

import com.barros.publication.controller.request.PublicationRequest;
import com.barros.publication.domain.Publication;
import com.barros.publication.repository.entity.PublicationEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PublicationMapper {

    PublicationEntity toPublicationEntity(Publication publication);

    Publication toPublication(PublicationEntity publicationEntity);

    Publication toPublication(PublicationRequest publicationRequest);
}
