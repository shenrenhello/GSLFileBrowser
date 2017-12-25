package com.example.gsl.gslfilebrowser;

import java.io.File;
import java.text.Collator;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

/**
 * Created by dell on 2017/12/25.
 */
public class FileSort {

    public static final int SORT_BY_NAME = 0;

    private Comparator<File> comparator;

    public FileSort(int sortType){
        sortFiles(sortType);
    }

    private void sortFiles(int sort_Type){
        switch (sort_Type){
            case SORT_BY_NAME:
                comparator = new Comparator<File>() {
                    @Override
                    public int compare(File o1, File o2) {
                        Collator myCollator = Collator.getInstance(Locale.CHINA);
                        int ret = myCollator.compare(o1.getName().toLowerCase(),o2.getName().toLowerCase());
                        return ret;
                    }
                };
                break;
            default:
                break;
        }
    }

    public void sortListFiles(List<File> fileList){
        Collections.sort(fileList,comparator);
    }

}
