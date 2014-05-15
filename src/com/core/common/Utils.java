package com.core.common;

import java.io.File;
import java.io.FileFilter;

/**
 *
 * @author Mahdi
 */
public class Utils {

    public static FileFilter getImagesFileFilter() {
        FileFilter filter = new FileFilter() {

            @Override
            public boolean accept(File file) {
                if (file.isFile()) {
                    String path = file.getAbsolutePath().toLowerCase();
                    for (int i = 0, n = Config.VALID_IMAGE_EXTENSIONS.length; i < n; i++) {
                        String extension = Config.VALID_IMAGE_EXTENSIONS[i];
                        if ((path.endsWith(extension) && (path.charAt(path.length()
                                - extension.length() - 1)) == '.')) {
                            return true;
                        }
                    }
                }
                return false;
            }
        };
        return filter;
    }

}
