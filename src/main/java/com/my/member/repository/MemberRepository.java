package com.my.member.repository;

import com.my.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // 선택사항 굳이 안붙여도됨
public interface MemberRepository extends JpaRepository<Member, Long> {
}
