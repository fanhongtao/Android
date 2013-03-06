/*
 * This file is in PUBLIC DOMAIN. You can use it freely. No guarantee.
 */
package org.fanhongtao.utils;

import java.io.File;

/**
 * Utilities for file.
 * @author Fan Hongtao &ltfanhongtao@gmail.com&gt
 */
public class FileUtils {
    public static String appendPath(String path, String name) {
        return path + File.pathSeparator + name;
    }
}
