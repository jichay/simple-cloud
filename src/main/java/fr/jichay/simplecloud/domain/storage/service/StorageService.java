package fr.jichay.simplecloud.domain.storage.service;

import fr.jichay.simplecloud.domain.storage.model.ContentMetadata;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.UUID;

@Service
public class StorageService implements StorageConsumer {

    private final StorageProvider storageProvider;
    private final MetadataStorageConsumer metadataService;

    public StorageService(StorageProvider storageProvider, MetadataStorageConsumer metadataService) {
        this.storageProvider = storageProvider;
        this.metadataService = metadataService;
    }

    @Override
    public boolean storeContent(String fileName, String contentType, InputStream content) {
        var contentKey = UUID.randomUUID();
        System.out.println(contentKey);
        var storedSize = storageProvider.store(contentKey.toString(), content);
        metadataService.storeMetadata(new ContentMetadata(contentKey.toString(), fileName, contentType, storedSize));
        return storedSize > 0;
    }

    @Override
    public InputStream getContent(String contentKey) {
        return storageProvider.get(contentKey);
    }

}
