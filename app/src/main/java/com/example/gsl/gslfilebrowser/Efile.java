package com.example.gsl.gslfilebrowser;

import java.io.File;

/**
 * Created by dell on 2017/12/21.
 */

public class Efile {
    private File file = null;

    public Efile(File f){
        file = f;
    }

    public File getFile() {
        return file;
    }

    @Override
    public String toString() {
        if (getFile() == null){
            return "";
        }
        return getFile().getName();
    }
}
