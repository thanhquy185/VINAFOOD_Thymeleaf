package vn.tuhoc.foodshop.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.ServletContext;

@Service
public class UploadService {
    // Properties
    private final ServletContext servletContext;

    // Constructors
    public UploadService(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    // Methods
    public String uploadImageFiles(MultipartFile file, String folder, String id) {
        String rootPath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "static" + File.separator + "assets" + File.separator
                + "images";
        String filename = "";
        try {
            byte[] bytes = file.getBytes();
            
            File dir = new File(rootPath + File.separator + folder);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            filename = System.currentTimeMillis() + "-" + folder.substring(0, folder.length() - 1) + "-" + id + "."
                    + String.valueOf(file.getContentType()).split("/")[1];
            File serverFile = new File(dir.getAbsolutePath() + File.separator + filename);

            System.out.println(dir.getAbsolutePath() + File.separator + filename);

            BufferedOutputStream stream = new BufferedOutputStream(
                    new FileOutputStream(serverFile));
            stream.write(bytes);
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return filename;
    }
}
