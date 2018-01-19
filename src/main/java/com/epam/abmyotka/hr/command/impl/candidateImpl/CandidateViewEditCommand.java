package com.epam.abmyotka.hr.command.impl.candidateImpl;

import com.epam.abmyotka.hr.command.Command;
import com.epam.abmyotka.hr.constant.MessageConstant;
import com.epam.abmyotka.hr.constant.PathConstant;
import com.epam.abmyotka.hr.controller.Router;
import com.epam.abmyotka.hr.entity.Account;
import com.epam.abmyotka.hr.entity.Candidate;
import com.epam.abmyotka.hr.manager.MessageManager;
import com.epam.abmyotka.hr.service.AccountService;
import com.epam.abmyotka.hr.service.CandidateService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CandidateViewEditCommand implements Command {
    private AccountService accountService;
    private CandidateService candidateService;

    public CandidateViewEditCommand(AccountService accountService, CandidateService candidateService) {
        this.accountService = accountService;
        this.candidateService = candidateService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router(PathConstant.PATH_PAGE_CANDIDATE_VIEW_EDIT, Router.RouteType.FORWARD);

        HttpSession session = request.getSession(true);
        Account candidateAccount = (Account)session.getAttribute("role");
        int accountId = accountService.findAccountIdByLoginPasswordAttachment(candidateAccount);

        if(accountId != 0) {
            Candidate candidate = candidateService.findByAccountId(accountId);
            if (candidate != null) {
                request.setAttribute("candidate", candidate);
            } else {
                router.setPagePath(PathConstant.PATH_PAGE_CANDIDATE);
                Object language = request.getSession(true).getAttribute("language");
                String message = MessageManager.getMessage(language.toString(), MessageConstant.NON_EXIST_CV);
                request.setAttribute("errorMessage", message);
            }
        } else {
            router.setPagePath(PathConstant.PATH_PAGE_CANDIDATE);
            Object language = request.getSession(true).getAttribute("language");
            String message = MessageManager.getMessage(language.toString(), MessageConstant.ERROR_ON_WEBSITE);
            request.setAttribute("errorMessage", message);
        }

        return router;
    }
}
