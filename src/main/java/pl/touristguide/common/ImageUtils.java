package pl.touristguide.common;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

public class ImageUtils {
    public static String createImage(String encodedImg, String image) throws IOException {
        byte[] decodedImg = decodeImage(encodedImg);
        String imageName = createImageName(image);
        Path destinationFile = getCreatingImagePath(imageName);

        String s = destinationFile.toAbsolutePath().toString();
        System.out.println("Current relative path is: " + s);

        Files.write(destinationFile, decodedImg);

        return imageName;
    }

    public static byte[] decodeImage(String encodedImg) {
        String partSeparator = ",";

        if (encodedImg.contains(partSeparator)) {
            encodedImg = encodedImg.split(partSeparator)[1];
        }

        return Base64.getDecoder().decode(encodedImg.getBytes(StandardCharsets.UTF_8));
    }

    public static String createImageName(String image){
        return image + ".png";
    }

    public static Path getCreatingImagePath(String imageName) {
        return Paths.get("web/tourist-guide/src/assets/images", imageName);
    }

    public static String getExistingImagePath(String imageName) {
        return Paths.get("web/tourist-guide/src/assets/images", imageName).toString();
    }

    public static void deleteImage(String imageName) throws Exception {
        File image = new File(getExistingImagePath(imageName));
        image.delete();
    }

}
