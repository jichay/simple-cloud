package fr.jichay.simplecloud.domain.storage.service;

import java.io.InputStream;

//TODO Rename
public interface StorageConsumer {

    boolean storeContent(String contentKey, String contentType, InputStream content);

    InputStream getContent(String contentKey);

}
