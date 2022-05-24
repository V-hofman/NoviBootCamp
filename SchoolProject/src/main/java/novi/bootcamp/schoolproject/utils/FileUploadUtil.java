package novi.bootcamp.schoolproject.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileUploadUtil {


    //To upload a file to our local directory
    public static void saveFile(String uploadDir, String fileName,
                                MultipartFile multipartFile) throws IOException {

        //Get the directory it needs to be uploaded to
        Path uploadPath = Paths.get(uploadDir);

        //Check if the folder exists where it needs to be saved, if not create it
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        //try to save the image
        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            throw new IOException("Could not save image file: " + fileName, ioe);
        }
    }
}
