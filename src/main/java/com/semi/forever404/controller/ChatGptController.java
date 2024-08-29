package com.semi.forever404.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.semi.forever404.model.dto.ChatGptResponseDto;
import com.semi.forever404.model.dto.QuestionRequestDto;
import com.semi.forever404.service.ChatGptService;

@RestController
@RequestMapping("/chat-gpt")
@CrossOrigin(origins = {"*"}, maxAge = 6000)
public class ChatGptController {

    private final ChatGptService chatGptService;

    public ChatGptController(ChatGptService chatGptService) {
        this.chatGptService = chatGptService;
    }

    @PostMapping("/question")
    public ChatGptResponseDto sendQuestion(@RequestBody QuestionRequestDto requestDto) {
    	
        return chatGptService.askQuestion(requestDto);
    }
}
