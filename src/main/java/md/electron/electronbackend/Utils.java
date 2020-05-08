package md.electron.electronbackend;

import org.springframework.boot.system.ApplicationHome;

import java.io.File;

public class Utils
{
    public static String getProjectRootPath()
    {
        final ApplicationHome home = new ApplicationHome(Utils.class);
        final File jarDir = home.getDir();
        final File projectRoot = jarDir.getParentFile().getParentFile();

        return projectRoot.getAbsolutePath();
    }
}
