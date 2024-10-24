 package com.cufs.contoller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@RestController
@RequestMapping("/files")
public class FileSystemController 
{

    private static final String UPLOAD_DIR = "uploads/";

    public FileSystemController()
    {
        File directory = new File(UPLOAD_DIR);
        if (!directory.exists()) 
        {
            directory.mkdirs();  
        }
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) 
    {
        try 
        {
            File uploadDir = new File(UPLOAD_DIR);
            if (!uploadDir.exists()) 
            {
                uploadDir.mkdirs();  
            }

            Path filePath = Paths.get(UPLOAD_DIR + file.getOriginalFilename());
            Files.write(filePath, file.getBytes());

            return ResponseEntity.ok("File uploaded successfully: " + filePath.toString());
        } 
        catch (IOException e) 
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File upload failed");
        }
    }



    @GetMapping("/download/{filename}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String filename) throws IOException 
    {
        byte[] fileData = Files.readAllBytes(Paths.get(UPLOAD_DIR + filename));
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                .body(fileData);
    }

    @PutMapping("/update/{filename}")
    public ResponseEntity<String> updateFile(
            @PathVariable String filename,
            @RequestParam("content") String content) 
    {
        
        String filePath = "uploads/" + filename;
        File file = new File(filePath);
        
        if (!file.exists()) 
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("File not found: " + filename);
        }
        
        try 
        {
            Files.write(Paths.get(filePath), content.getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
            return ResponseEntity.ok("File updated successfully: " + filename);
        } catch (IOException e) 
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error updating file: " + filename);
        }
    }

    @DeleteMapping("/delete/{filename}")
    public String deleteFile(@PathVariable String filename) 
    {
        File file = new File(UPLOAD_DIR + filename);
        if (file.exists()) 
        {
            file.delete();
            return "File deleted successfully: " + filename;
        } 
        else 
        {
            return "File not found: " + filename;
        }
    }
}



