package kz.iitu.itse1908.daniyal.database;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
@Data
public class FileModel {
    private MultipartFile file;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
