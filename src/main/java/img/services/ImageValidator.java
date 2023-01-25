package img.services;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.function.Predicate;

public class ImageValidator implements Predicate<File> {
    @Override
    public boolean test(File image) {
        String[] imageExtensions = {"gif","png","bmp","jpg"};
        boolean result = false;
        int i = image.getName().lastIndexOf(".");
        if (i > 0){
            for (String imageExtension : imageExtensions){
                if (image.getName().substring(i+1).equals(imageExtension)){
                    try {
                        ImageIO.read(image);
                        result = true;

                    }
                    catch (IOException | ArrayIndexOutOfBoundsException e){
                        System.out.println(e.getMessage());

                    }
                    break;

                }
            }

        }

        return result;
    }
}
