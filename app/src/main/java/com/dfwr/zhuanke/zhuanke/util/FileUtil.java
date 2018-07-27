package com.dfwr.zhuanke.zhuanke.util;

import android.os.Environment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

    public enum PathStatus {
        SUCCESS,
        EXITS,
        ERROR
    }



    public static List<String> listPath(String root) {
        List<String> allDir = new ArrayList();
        SecurityManager checker = new SecurityManager();
        File path = new File(root);
        checker.checkRead(root);
        if (path.isDirectory()) {
            for (File f : path.listFiles()) {
                if (f.isDirectory() && !f.getName().startsWith(".")) {
                    allDir.add(f.getAbsolutePath());
                }
            }
        }
        return allDir;
    }

    public static List<File> listPathFiles(String root) {
        List<File> allDir = new ArrayList();
        SecurityManager checker = new SecurityManager();
        File path = new File(root);
        checker.checkRead(root);
        for (File f : path.listFiles()) {
            if (f.isFile()) {
                allDir.add(f);
            } else {
                listPath(f.getAbsolutePath());
            }
        }
        return allDir;
    }



    public static String getSDPath() {
        return Environment.getExternalStorageDirectory().getPath();
    }
}