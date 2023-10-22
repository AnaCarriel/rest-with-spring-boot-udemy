package br.com.anacarriel.services;

import br.com.anacarriel.config.FileStorageConfig;
import br.com.anacarriel.exception.FileStorageException;
import com.ctc.wstx.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageService {

    private final Path fileStorageLocation;

    @Autowired
    public FileStorageService(FileStorageConfig fileStorageConfig){

        this.fileStorageLocation = Paths.get(fileStorageConfig.getUploadDir()).toAbsolutePath().normalize();
        try{
            Files.createDirectories(this.fileStorageLocation);
        }catch (Exception e){
            throw new FileStorageException("Não foi possível criar o diretorio em que os arquivos serão armazenados", e);
        }
    }
    public String storeFile (MultipartFile file){
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (fileName.contains("..")){
                throw new FileStorageException("O nome digitado contem dois pontos" + fileName);
            }
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        } catch (Exception e){
            throw new FileStorageException("Não foi possível armazenar" + fileName + ". Por favor, tente novamente", e);
        }
    }
}
