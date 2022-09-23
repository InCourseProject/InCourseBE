package com.example.week08.repository;

import com.example.week08.domain.SubComment;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SubCommentRepository extends JpaRepository<SubComment,Long> {
}
