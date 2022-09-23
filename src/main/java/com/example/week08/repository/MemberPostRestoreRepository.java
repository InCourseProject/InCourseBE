package com.example.week08.repository;

import com.example.week08.domain.Member;
import com.example.week08.domain.MemberPostRestore;
import com.example.week08.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberPostRestoreRepository extends JpaRepository<MemberPostRestore,Long> {
    Optional<MemberPostRestore> findByPostAndMember(Post post, Member member);

    Optional<List<MemberPostRestore>> findAllByMember(Member member);
}
