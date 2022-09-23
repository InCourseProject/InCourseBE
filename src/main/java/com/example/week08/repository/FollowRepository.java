package com.example.week08.repository;

import com.example.week08.domain.Follow;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow,Long> {
    Optional<Follow> findByfollower(String email);
    int countAllByFollower(String email);
    int countAllByFollowing(String email);

}
