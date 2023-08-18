package fr.jichay.simplecloud.domain.storage.service;

import fr.jichay.simplecloud.domain.storage.model.ContentMetadata;
import org.springframework.stereotype.Service;

@Service
public class MetadataService implements MetadataStorageConsumer {

    private final MetadataStorageProvider metadataStorageProvider;

    public MetadataService(MetadataStorageProvider metadataStorageProvider) {
        this.metadataStorageProvider = metadataStorageProvider;
    }


    @Override
    public ContentMetadata storeMetadata(ContentMetadata metadata) {
        return metadataStorageProvider.store(metadata);
    }

    @Override
    public ContentMetadata getMetadata(String key) {
        return metadataStorageProvider.getMetadata(key);
    }
}
