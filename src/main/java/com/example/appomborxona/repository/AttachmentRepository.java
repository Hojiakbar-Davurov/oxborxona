package com.example.appomborxona.repository;

import com.example.appomborxona.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AttachmentRepository extends JpaRepository<Attachment, Integer> {
    Optional<Attachment> findByNameAndSizeAndContextType(String name, long size, String contextType);
}
