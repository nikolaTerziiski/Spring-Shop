package bg.springshop.springshop.service;

import bg.springshop.springshop.model.entity.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CloudinaryService {
    Image upload(MultipartFile file) throws IOException;
}
