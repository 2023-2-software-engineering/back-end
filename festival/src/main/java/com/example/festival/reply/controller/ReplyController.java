package com.example.festival.reply.controller;

import com.example.festival.reply.dto.ReplyDto;
import com.example.festival.reply.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reply")

public class ReplyController {
    private final ReplyService replyService;

    public ReplyController(
            @Autowired ReplyService replyService
    ) {
        this.replyService = replyService;
    }

    @PostMapping("/{commentId}")
    public String replyCreate(@PathVariable("commentId")Integer commentId, @RequestBody ReplyDto replyDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        this.replyService.replyCreate(authentication.getName(), replyDto, commentId);
        return "답글 작성 완료";
    }

    @GetMapping("/{commentId}") //댓글 별로 답글 읽기
    public List<ReplyDto> replyReadByComment(@PathVariable("commentId")Integer commentId) {
        return this.replyService.replyReadByComment(commentId);
    }

    @PatchMapping("/{replyId}")
    public String replyUpdate(@PathVariable("replyId")Integer replyId, @RequestBody ReplyDto replyDto) {
        return this.replyService.replyUpdate(replyId, replyDto);
    }

    @DeleteMapping("/{replyId}")
    public void replyDelete(@PathVariable("replyId")Integer replyId) {
        this.replyService.replyDelete(replyId);
    }


}
