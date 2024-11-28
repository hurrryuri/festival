package com.example.festival.repository;

import com.example.festival.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByMemberID (String memberID);
}
