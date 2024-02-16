package com.anproject.BlogApp.service;


import com.anproject.BlogApp.entity.Approval;
import com.anproject.BlogApp.entity.News;
import com.anproject.BlogApp.payload.response.ApprovelResponseDto;
import com.anproject.BlogApp.repository.ApprovalRepository;
import com.anproject.BlogApp.repository.NewsRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ApprovalService {

    private final ApprovalRepository approvalRepository;
    private final NewsRepository newsRepository;

    public ApprovalService(ApprovalRepository approvalRepository, NewsRepository newsRepository) {
        this.approvalRepository = approvalRepository;
        this.newsRepository = newsRepository;
    }

    public void save(Long newsId){
        Optional<News> news = newsRepository.findById(newsId);
        Approval approval = new Approval();
        approval.setNews(news.get());
        approval.setUser(news.get().getUser());
        approvalRepository.save(approval);
    }

    public void approvalStatusChange(Long approvalId, boolean approvalInfo){
        Optional<Approval> approval = approvalRepository.findById(approvalId);
        approval.get().setApproveInfo(approvalInfo);
        Date date = new Date();
        approval.get().setApproveDate(date);
        approvalRepository.save(approval.get());
        Optional<News> news = newsRepository.findById(approval.get().getNews().getId());
        news.get().setStatus(approvalInfo);
        newsRepository.save(news.get());
    }

    public List<ApprovelResponseDto> getAllAprovals(){
        List<Approval> approvalList = approvalRepository.findAll();
        List<ApprovelResponseDto> approvelResponseDtoList = approvalList.stream()
                .map(approval -> ApprovelResponseDto.mapEntityToResponseDto(approval))
                .collect(Collectors.toList());
        return approvelResponseDtoList;
    }

}
