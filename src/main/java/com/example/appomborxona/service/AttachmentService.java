package com.example.appomborxona.service;

import com.example.appomborxona.entity.Attachment;
import com.example.appomborxona.entity.AttachmentContent;
import com.example.appomborxona.payload.Result;
import com.example.appomborxona.repository.AttachmentContentRepository;
import com.example.appomborxona.repository.AttachmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class AttachmentService {

    @Autowired
    AttachmentRepository attachmentRepository;

    @Autowired
    AttachmentContentRepository attachmentContentRepository;

    public Result upload(MultipartHttpServletRequest request) throws IOException {

        // Check attachment exists
        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());
        Optional<Attachment> exists = attachmentRepository.findByNameAndSizeAndContextType(file.getOriginalFilename(), file.getSize(), file.getContentType());
        if (exists.isPresent())
            return new Result("File uploaded", true, exists.get().getId());


        // Upload to database
        Attachment attachment = new Attachment();
        attachment.setName(file.getOriginalFilename());
        attachment.setSize(file.getSize());
        attachment.setContextType(file.getContentType());
        Attachment save = attachmentRepository.save(attachment);

        AttachmentContent attachmentContent = new AttachmentContent();
        attachmentContent.setBytes(file.getBytes());
        attachmentContent.setAttachment(save);
        attachmentContentRepository.save(attachmentContent);
        return new Result("File uploaded", true, save.getId());
    }

    public List<Attachment> getAllInfo() {
        return attachmentRepository.findAll();
    }

    public Optional<Attachment> getInfo(Integer id) {
        return attachmentRepository.findById(id);
    }

    public Result downloadById(Integer id, HttpServletResponse response) throws IOException {

        // Check attachment exists
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        if (optionalAttachment.isEmpty())
            return new Result("file not found", false);

        // Check attachmentContent exists
        Optional<AttachmentContent> optionalAttachmentContent = attachmentContentRepository.findByAttachmentId(optionalAttachment.get().getId());
        if (optionalAttachmentContent.isEmpty())
            return new Result("file bytes not found", false);

        // Download attachment
        Attachment attachment = optionalAttachment.get();
        response.setHeader("Content-Disposition", "attachment; filename=" + attachment.getName());
        response.setContentType(attachment.getContextType());
        FileCopyUtils.copy(optionalAttachmentContent.get().getBytes(), response.getOutputStream());
        return new Result("file downloaded", true);
    }

    public Result edit(Integer id, MultipartHttpServletRequest request) throws IOException {

        // Check attachment exists
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        if (optionalAttachment.isEmpty())
            return new Result("file not found", false);

        // Edit file
        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());

        Attachment attachment = optionalAttachment.get();
        assert file != null;
        attachment.setName(file.getOriginalFilename());
        attachment.setSize(file.getSize());
        attachment.setContextType(file.getContentType());

        Attachment save = attachmentRepository.save(attachment);
        return new Result("File edited", true, save.getId());
    }

    public Result delete(Integer id) {

        // Check file exists
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        if (optionalAttachment.isEmpty())
            return new Result("file not found", false);

        // Delete both of them
        Optional<AttachmentContent> optionalAttachmentContent = attachmentContentRepository.findByAttachmentId(optionalAttachment.get().getId());
        optionalAttachmentContent.ifPresent(attachmentContent -> attachmentContentRepository.deleteById(attachmentContent.getId()));
        attachmentRepository.deleteById(id);
        return new Result("File deleted", true);
    }
}