package com.epam.abmyotka.hr.command.impl.adminImpl;

import com.epam.abmyotka.hr.command.Command;
import com.epam.abmyotka.hr.constant.MessageConstant;
import com.epam.abmyotka.hr.constant.ParameterConstant;
import com.epam.abmyotka.hr.constant.PathConstant;
import com.epam.abmyotka.hr.controller.Router;
import com.epam.abmyotka.hr.entity.Candidate;
import com.epam.abmyotka.hr.entity.Employer;
import com.epam.abmyotka.hr.entity.Interview;
import com.epam.abmyotka.hr.entity.Vacancy;
import com.epam.abmyotka.hr.manager.MessageManager;
import com.epam.abmyotka.hr.service.CandidateService;
import com.epam.abmyotka.hr.service.EmployerService;
import com.epam.abmyotka.hr.service.InterviewService;
import com.epam.abmyotka.hr.service.VacancyService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Locale;

import static com.epam.abmyotka.hr.constant.MessageConstant.CANDIDATE_HAS_BEEN_REMOVED;
import static com.epam.abmyotka.hr.constant.MessageConstant.EMPLOYER_NOT_ASSIGNED;
import static com.epam.abmyotka.hr.constant.MessageConstant.VACANCY_HAS_BEEN_REMOVED;

public class AdminInterviewViewCommand implements Command {
    private InterviewService interviewService;
    private CandidateService candidateService;
    private VacancyService vacancyService;
    private EmployerService employerService;

    public AdminInterviewViewCommand(InterviewService interviewService, CandidateService candidateService,
                                     VacancyService vacancyService, EmployerService employerService) {
        this.interviewService = interviewService;
        this.candidateService = candidateService;
        this.vacancyService = vacancyService;
        this.employerService = employerService;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router(PathConstant.PATH_PAGE_ADMIN_INTERVIEW, Router.RouteType.FORWARD);

        List<Interview> interviews = interviewService.takeAll();

        for(Interview interview : interviews) {
            int candidateID = interview.getCandidateId();
            if (candidateID != 0) {
                Candidate candidate = candidateService.findById(candidateID);
                String mainCandidateInformation = candidate.getMainInformation();
                interview.setCandidateInfo(mainCandidateInformation);
            } else {
                interview.setCandidateInfo(CANDIDATE_HAS_BEEN_REMOVED);
            }

            int vacancyId = interview.getVacancyId();
            if (vacancyId != 0) {
                Vacancy vacancy = vacancyService.findById(vacancyId);
                String mainVacancyInformation = vacancy.getMainInformation();
                interview.setVacancyInfo(mainVacancyInformation);

                int employerId = vacancy.getEmployerId();
                if (employerId != 0) {
                    Employer employer = employerService.findById(employerId);
                    String mainEmployerInformation = employer.getMainInformation();
                    interview.setEmployerInfo(mainEmployerInformation);
                } else {
                    interview.setEmployerInfo(EMPLOYER_NOT_ASSIGNED);
                }
            } else {
                interview.setVacancyInfo(VACANCY_HAS_BEEN_REMOVED);
                interview.setEmployerInfo(EMPLOYER_NOT_ASSIGNED);
            }
        }

        request.setAttribute("interviewList", interviews);

        return router;
    }
}
