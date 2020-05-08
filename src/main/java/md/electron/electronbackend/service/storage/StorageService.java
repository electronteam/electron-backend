package md.electron.electronbackend.service.storage;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService
{
    void storeMedia(MultipartFile file, String target);
}
