package img.services;

import img.ImageData;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ImagePathService {
    private final ImageValidator imageValidator = new ImageValidator();

    public List<ImageData> getImagesFromDir(String path){
        List<ImageData> images = new ArrayList<>();

        File imagesPath = new File(path);
        File[] files = imagesPath.listFiles();

        if (files != null){
            for (File file : files){
                if (!file.isDirectory()){
                    if (imageValidator.test(file)){
                        images.add(
                                new ImageData(
                                        file.getName(),
                                        file.getAbsolutePath()
                                )
                        );
                    }
                }
            }
        }
        //images.forEach(System.out::println);
        return images;
    }
    public boolean deleteImage(String path){
        File file = new File(path);
        if(file.exists() && !file.isDirectory()) {
            return file.delete();
        }
        return false;
    }
}
