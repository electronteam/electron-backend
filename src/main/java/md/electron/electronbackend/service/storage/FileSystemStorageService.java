package md.electron.electronbackend.service.storage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileSystemStorageService implements StorageService
{
    @Value("${data.media.dir}")
    private String mediaFolder;

    private Path mediaPath;

    @PostConstruct
    public void init()
    {
        try
        {
            mediaPath = Paths.get(this.mediaFolder);
            final File mediaFolder = mediaPath.toFile();
            if (!mediaFolder.exists())
            {
                final boolean created = mediaFolder.mkdirs();

                if (created)
                {
                    System.out.println("Created media folder");
                }
                else
                {
                    System.out.println("Couldn't create media folder");
                }
            }
        }
        catch (final Exception ex)
        {
            throw new StorageException("Could not initialize file system storage service", ex);
        }
    }

    @Override
    public void storeMedia(final MultipartFile file, final String target)
    {
        try
        {
            if (file.isEmpty())
            {
                throw new StorageException("Failed to store empty file " + file.getOriginalFilename());
            }

            Files.copy(file.getInputStream(), this.mediaPath.resolve(target), StandardCopyOption.REPLACE_EXISTING);
        }
        catch (final Exception e)
        {
            throw new StorageException("Failed to store file " + file.getOriginalFilename(), e);
        }
    }
}
