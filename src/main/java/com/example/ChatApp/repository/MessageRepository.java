package com.example.ChatApp.repository;

import com.example.ChatApp.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findAllByOrderByTimestampAsc();
}
