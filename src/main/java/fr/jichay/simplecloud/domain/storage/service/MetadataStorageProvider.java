package fr.jichay.simplecloud.domain.storage.service;

import fr.jichay.simplecloud.domain.storage.model.ContentMetadata;

public interface MetadataStorageProvider {

    ContentMetadata store(ContentMetadata metadata);

    ContentMetadata getMetadata(String key);

}
