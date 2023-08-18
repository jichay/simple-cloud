package fr.jichay.simplecloud.domain.storage.service;

import fr.jichay.simplecloud.domain.storage.model.ContentMetadata;

public interface MetadataStorageConsumer {

    ContentMetadata storeMetadata(ContentMetadata metadata);

    ContentMetadata getMetadata(String key);

}
