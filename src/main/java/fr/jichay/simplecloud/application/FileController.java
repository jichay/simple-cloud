package fr.jichay.simplecloud.application;

import fr.jichay.simplecloud.domain.storage.service.MetadataStorageConsumer;
import fr.jichay.simplecloud.domain.storage.service.StorageConsumer;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload2.jakarta.JakartaServletFileUpload;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import static fr.jichay.simplecloud.AppConstant.BASE_API;

@RestController
@RequestMapping(BASE_API + "/file")
public class FileController {

    private final StorageConsumer storageService;
    private final MetadataStorageConsumer metadataService;

    public FileController(StorageConsumer storageService, MetadataStorageConsumer metadataService) {
        this.storageService = storageService;
        this.metadataService = metadataService;
    }

    @PostMapping("/upload")
    public ResponseEntity<Void> upload(HttpServletRequest request) throws Exception {
        if (!JakartaServletFileUpload.isMultipartContent(request)) {
            throw new RuntimeException("Multipart request expected");
        }

        var jakartaServlet = new JakartaServletFileUpload<>();
        var items = jakartaServlet.getItemIterator(request);

        while (items.hasNext()) {
            var item = items.next();
            if (item.isFormField()) {
                continue;
            }
            var start = System.currentTimeMillis();
            var fileName = item.getName();
            storageService.storeContent(fileName, item.getContentType(), item.getInputStream());
            var end = System.currentTimeMillis();

            System.out.printf("Thread: %s | FileName: %s | Time (ms): %s%n", Thread.currentThread().getName(), fileName, (end - start));
        }

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/{key}")
    public void download(@PathVariable("key") String key, HttpServletResponse response) throws IOException {

        var metadata = metadataService.getMetadata(key);

        response.setContentType(metadata.contentType());
        response.setHeader("Content-Length", Long.toString(metadata.size()));
        response.setHeader("Content-Disposition", "attachment; filename=\"" + metadata.fileName() + "\"");

        var ins = storageService.getContent(key);

        IOUtils.copy(ins, response.getOutputStream());
        IOUtils.closeQuietly(ins);
        IOUtils.closeQuietly(response.getOutputStream());
    }

}
