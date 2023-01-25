package img.services;


import javax.swing.*;
import java.awt.*;
import java.io.File;


public class ImageService {
    private final ImagePathService imagePathService = new ImagePathService();
    public ImageIcon returnResizedImages(String imageDirPath){
        return new ImageIcon(
                new ImageIcon(imageDirPath).
                        getImage().
                        getScaledInstance(600, 600, Image.SCALE_DEFAULT)
        );
    }

    public boolean deleteImage(String path){
        File img = new File(path);
        if (!img.isDirectory()){
            return img.delete();
        }
        return  false;
    }
}
