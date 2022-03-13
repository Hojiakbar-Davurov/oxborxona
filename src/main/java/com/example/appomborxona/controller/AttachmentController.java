package com.example.appomborxona.controller;

import com.example.appomborxona.entity.Attachment;
import com.example.appomborxona.payload.Result;
import com.example.appomborxona.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/file")
public class AttachmentController {
    @Autowired
    AttachmentService attachmentService;

    @PostMapping("/upload")
    public Result upload(MultipartHttpServletRequest request) throws IOException {
        return attachmentService.upload(request);
    }

    @GetMapping
    public List<Attachment> getAllInfo() {
        return attachmentService.getAllInfo();
    }

    @GetMapping("/{id}")
    public Optional<Attachment> getInfo(@PathVariable Integer id) {
        return attachmentService.getInfo(id);
    }

    @GetMapping("/download/{id}")
    public Result downloadById(@PathVariable Integer id, HttpServletResponse response) throws IOException {
        return attachmentService.downloadById(id, response);
    }

    @PutMapping("/{id}")
    public Result edit(@PathVariable Integer id, MultipartHttpServletRequest request) throws IOException {
        return attachmentService.edit(id, request);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        return attachmentService.delete(id);
    }
}
