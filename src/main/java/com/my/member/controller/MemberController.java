package com.my.member.controller;

import com.my.member.dto.MemberDto;
import com.my.member.entity.Member;
import com.my.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MemberController {
    // 서비스를 가져와서 실행 준비
    // 1. @Autowired 사용
    @Autowired
    MemberService service;

    @GetMapping("/list")
    public String showList(Model model) {
        model.addAttribute("title", "리스트보기");
        // 서비스에 멤버리스트 정보 요청
        List<MemberDto> memberList = service.getAllList();
        model.addAttribute("list", memberList);
        return "showMember";
    }

    @GetMapping("/member/insertForm")
    public String insertFormView() {
        return "insertForm";
    }

    @PostMapping("/member/insert")
    public String insert(MemberDto dto) {
        // 1. 폼에서 보낸 정보를 DTO로 받음
        // System.out.println(dto);
        // 2. 받은 DTO를 서비스로 보냄
        service.insertMember(dto);
        // 3. 서비스에서 DTO를 엔티티로 변환
        // 4. 리포지토리를 이용해 저장
        // 5. 메인리스트화면으로 돌아감
        return "redirect:/list";
    }

    @PostMapping("/member/delete/{id}")
    public String deleteMember(@PathVariable("id") Long id) {
        service.deleteMember(id);
        return "redirect:/list";
    }

    @GetMapping("/member/updateView")
    public String updateView(
            @RequestParam("updateId") Long updateId,
            Model model) {
        // 1. 받은 updateId로 데이터를 검색해온다(DTO)
        MemberDto dto = service.findMember(updateId);
        // 2. DTO가 비어있는지 확인한다(ID의 유무를 확인) => 조치
        if (ObjectUtils.isEmpty(dto)) {
            return "redirect:/list";
        } else {
            // 3. 받은 DTO를 수정폼으로 보낸다
            model.addAttribute("dto", dto);
            return "updateForm";
        }
    }
}
