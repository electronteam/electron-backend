package md.electron.electronbackend.service.storage;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileSystemStorageService implements StorageService
{
    private static final String DATA_MEDIA_DIR = "data.media.dir";

    @Autowired
    private Environment environment;

    private Path mediaPath;

    @PostConstruct
    public void init()
    {
        try
        {
            final ApplicationHome home = new ApplicationHome(this.getClass());
            final File jarDir = home.getDir();
            final File projectRoot = jarDir.getParentFile().getParentFile();
            final String projectRootPath = projectRoot.getAbsolutePath();

            mediaPath = Paths.get(projectRootPath + getMediaFolder());
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
    public void storeMedia(MultipartFile file, String newFileName)
    {
        try
        {
            if (file.isEmpty())
            {
                throw new StorageException("Failed to store empty file " + file.getOriginalFilename());
            }

            final String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());

            Files.copy(file.getInputStream(), this.mediaPath.resolve(newFileName + "." + fileExtension));
        }
        catch (IOException e)
        {
            throw new StorageException("Failed to store file " + file.getOriginalFilename(), e);
        }
    }

    private String getMediaFolder()
    {
        return environment.getProperty(DATA_MEDIA_DIR);
    }
}
