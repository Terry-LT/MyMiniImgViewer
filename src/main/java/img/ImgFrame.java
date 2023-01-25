package img;

import img.services.ImagePathService;
import img.services.ImageService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ImgFrame extends JFrame implements ActionListener {
    private final ImagePathService imagePathService = new ImagePathService();
    private final ImageService imageService = new ImageService();
    private final List<ImageData> images = new ArrayList<>();
    //
    private int imgIndex = 0;
    private String currentImagePath = "";
    //
    private final JLabel imageLabel = new JLabel(new ImageIcon("Path/To/Your/Image.png"));
    private final JLabel noImagesLabel = new JLabel("There is no images inside this folder!");
    private final JLabel loadingLabel = new JLabel("Loading...");
    private final JLabel errorDeleteImageLabel = new JLabel("Could not delete this image");
    //Buttons
    private final JButton nextImageButton = new JButton("Next Image");
    private final JButton previousImageButton = new JButton("Previous Image");
    private final JButton selectDirButton = new JButton("Select path");
    private final JButton deleteImageButton = new JButton("Delete Image");


    private JFileChooser selectDirChooser;

    public ImgFrame() throws HeadlessException {
        this.setTitle("MyMiniImgViewer");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new FlowLayout());

        //
        nextImageButton.addActionListener(this);
        previousImageButton.addActionListener(this);
        selectDirButton.addActionListener(this);
        deleteImageButton.addActionListener(this);

        deleteImageButton.setVisible(false);
        //
        noImagesLabel.setVisible(false);
        loadingLabel.setVisible(false);
        errorDeleteImageLabel.setVisible(false);

        this.add(errorDeleteImageLabel);
        this.add(loadingLabel);
        this.add(noImagesLabel);
        this.add(imageLabel);

        this.add(deleteImageButton);
        this.add(selectDirButton);
        this.add(previousImageButton);
        this.add(nextImageButton);

        this.pack();
        this.setVisible(true);
        this.setSize(900,600);
    }
    public void addImages(){
        //To clear the old images
        images.clear();
        images.addAll(imagePathService.getImagesFromDir(currentImagePath));

        if (!images.isEmpty()){
            //To show loadingLabel
            ImageIcon imageIcon = imageService.returnResizedImages(images.get(imgIndex).getPath());
            imageLabel.setIcon(
                    imageIcon
            );

            //To make deleteImageButton visible
            deleteImageButton.setVisible(true);
            //To make noImagesLabel not visible
            noImagesLabel.setVisible(false);
        }
        else{
            imageLabel.setIcon(
                    new ImageIcon("Path/To/Your/Image.png")
            );
            noImagesLabel.setVisible(true);

        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==selectDirButton){
            selectDirChooser = new JFileChooser();
            selectDirChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            selectDirChooser.setAcceptAllFileFilterUsed(false);

            if (selectDirChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                currentImagePath = selectDirChooser.getSelectedFile().toString();
                addImages();

            }

        }
        if (e.getSource()==nextImageButton && images.size() > 0){
            if (imgIndex < images.size()) {
                imgIndex++;
                //to resize image
                ImageIcon imageIcon = imageService.returnResizedImages(images.get(imgIndex).getPath());
                imageLabel.setIcon(
                        imageIcon
                );
            }
            errorDeleteImageLabel.setVisible(false);
        }
        if (e.getSource()==previousImageButton && images.size() > 0){
            if (imgIndex > 0){
                imgIndex--;
                //to resize image
                ImageIcon imageIcon = imageService.returnResizedImages(images.get(imgIndex).getPath());
                imageLabel.setIcon(
                        imageIcon
                );
            }
            errorDeleteImageLabel.setVisible(false);
        }
        if (e.getSource() == deleteImageButton){
            if (images.isEmpty()){
                deleteImageButton.setVisible(false);
            }
            else{
                if (imageService.deleteImage(images.get(imgIndex).getPath())){
                    //To refresh List ImagesData
                    addImages();

                }
                else{
                    //To display errorDeleteImageLabel
                    errorDeleteImageLabel.setVisible(true);
                }
            }
        }
    }

}
