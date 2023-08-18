package fr.jichay.simplecloud.infra.storage;

import fr.jichay.simplecloud.domain.storage.service.StorageProvider;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class FSStorage implements StorageProvider {

    private final File saveDir;

    public FSStorage(@Value("${storage.fs.dir}") File saveDir) {
        this.saveDir = saveDir;
        saveDir.mkdirs();
    }

    @Override
    public long store(String key, InputStream content) {
        var destination = new File(saveDir, key);
        //TODO Clean code trycatch
        FileOutputStream outs = null;
        try {
            outs = new FileOutputStream(destination);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        long storedBytesLength = 0;
        try {
            storedBytesLength = IOUtils.copyLarge(content, outs);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        IOUtils.closeQuietly(content);
        IOUtils.closeQuietly(outs);

        return storedBytesLength;
    }

    @Override
    public InputStream get(String key) {
        var destination = new File(saveDir, key);
        try {
            return new FileInputStream(destination);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
