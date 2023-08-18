package fr.jichay.simplecloud.domain.storage.service;

import java.io.InputStream;

public interface StorageProvider {

    long store(String key, InputStream content);

    InputStream get(String key);

}
