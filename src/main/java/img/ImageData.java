package img;

public class ImageData {
    private final String name;
    private final String path;

    public ImageData(String name,String path) {
        this.name = name;
        this.path = path;
    }

    public String getName() {
        return name;
    }
    public String getPath() {
        return path;
    }


    @Override
    public String toString() {
        return "ImageData{" +
                "name='" + name + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
