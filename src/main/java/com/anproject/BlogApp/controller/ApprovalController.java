package com.anproject.BlogApp.controller;

import com.anproject.BlogApp.service.ApprovalService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ApprovalController {

    ApprovalService approvalService;

    public ApprovalController(ApprovalService approvalService) {
        this.approvalService = approvalService;
    }

    @GetMapping("/admin/news/news-approval")
    public String showApproval(Model model){
        model.addAttribute("approvalList", approvalService.getAllAprovals());
        return"/admin/news/news-approval";
    }

    @PostMapping("/admin/news/approval-status")
    public String addApprovalStatus(@RequestParam("approvalId") Long approvalId,
                                    @RequestParam("isChecked") boolean isChecked){
        approvalService.approvalStatusChange(approvalId, isChecked);
        return"admin/news/news-approval";
    }

}
