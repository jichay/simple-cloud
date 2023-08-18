package fr.jichay.simplecloud.domain.storage.model;

import fr.jichay.simplecloud.infra.ContentMetadataEntity;

public record ContentMetadata(
        String storageKey,
        String fileName,
        String contentType,
        long size
) {

    public static ContentMetadata from(ContentMetadataEntity entity) {
        return new ContentMetadata(
                entity.getId(),
                entity.getFileName(),
                entity.getContentType(),
                entity.getSize()
        );
    }

}
