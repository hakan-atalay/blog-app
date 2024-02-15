package com.anproject.BlogApp.controller;

import com.anproject.BlogApp.entity.CustomUserDetail;
import com.anproject.BlogApp.payload.request.ParaphraseRequestDto;
import com.anproject.BlogApp.service.ParaphraseService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ParaphraseController {

    private final ParaphraseService paraphraseService;

    public ParaphraseController(ParaphraseService paraphraseService) {
        this.paraphraseService = paraphraseService;
    }

    @PostMapping("/news/add-paraphrase")
    public String addParaphrase(@AuthenticationPrincipal CustomUserDetail user,
                                ParaphraseRequestDto paraphraseRequestDto,
                                @RequestParam("newsId") Long newsId){
        paraphraseService.save(paraphraseRequestDto, user, newsId);
        return "redirect:/news/news-content/" + newsId;
    }

}
