package com.enumAfrica.services;

import com.enumAfrica.data.model.*;
import com.enumAfrica.data.repository.CohortRepository;
import com.enumAfrica.dto.request.*;
import com.enumAfrica.dto.response.*;
import com.enumAfrica.exception.*;
import com.enumAfrica.utils.Mapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class CohortServiceImpl implements CohortService{
    private final CohortRepository cohortRepository;
    private final ProgramTypeService programTypeService;
    private final Mapper mapper;
    private final MailService mailService;
    private final CourseService courseService;
    private final CohortUserService cohortUserService;

    @Override
    public CreatedCohortResponse createCohort(CreateCohortRequest createCohortRequest) throws CohortAlreadyExistsException, IOException, ProgramTypeDoesNotExistException {
        Cohort foundCohort = cohortRepository.findCohortByName(createCohortRequest.getName());
        if (foundCohort == null){
            validateProgramType(createCohortRequest);
            Cohort newCohort = mapper.map(createCohortRequest);
            Cohort savedCohort = cohortRepository.save(newCohort);
            return mapper.map(savedCohort);

        }
        throw new CohortAlreadyExistsException("Cohort Already Exists");
    }

    private void validateProgramType(CreateCohortRequest createCohortRequest) throws ProgramTypeDoesNotExistException {
        ProgramType foundProgram = programTypeService.getAProgramType(createCohortRequest.getProgramType());
        if (foundProgram == null) throw new ProgramTypeDoesNotExistException("Program does not exist");
    }

    @Override
    public Cohort getCohort(String name) {
        return cohortRepository.findCohortByName(name);
    }

    @Override
    @Transactional
    public void deleteAll() {
        for (Cohort cohort:cohortRepository.findAll()) {
            List<Course> courses = cohort.getCourses();
            courseService.deleteCourses(courses);
            cohortRepository.delete(cohort);
        }
    }

    @Override
    public List<Cohort> getAllCohorts() {
        return cohortRepository.findAll();
    }

    @Override
    public InvitedInstructorResponse inviteInstructorToCohort(InviteInstructorRequest inviteInstructorRequest) {
        Sender sender = new Sender();
        sender.setName("Esther");
        sender.setEmail("ojamata.semicolon@gmail.com");

        SendMailRequest sendMailRequest = new SendMailRequest();
        sendMailRequest.setLink(inviteInstructorRequest.getLink());
        sendMailRequest.setRecipients(inviteInstructorRequest.getInstructorEmail());
        sendMailRequest.setSender(sender);
        SendMailResponse sendMailResponse = mailService.createMessageAndSendEmail(sendMailRequest);

        InvitedInstructorResponse invitedInstructorResponse = new InvitedInstructorResponse();
        invitedInstructorResponse.setSendMailResponse(sendMailResponse);
        invitedInstructorResponse.setMessage("Invite successfully sent");

        return invitedInstructorResponse;
    }

    @Override
    @Transactional
    public AcceptedInstructorInviteResponse acceptInstructorInvite(AcceptInviteRequest request) throws CohortNotFoundException {
        Optional<Cohort> foundCohort = cohortRepository.findById(request.getCohortId());
        if (foundCohort.isPresent()){
            Instructor instructor = request.getInstructor();
            List<User> userList = foundCohort.get().getUsers();
            userList.add(instructor);
            foundCohort.get().setUsers(userList);
            Cohort savedCohort = cohortRepository.save(foundCohort.get());

            AcceptedInstructorInviteResponse acceptedInstructorInviteResponse = new AcceptedInstructorInviteResponse();
            acceptedInstructorInviteResponse.setInstructor(request.getInstructor());
            acceptedInstructorInviteResponse.setMessage("Congrats!!!, you're now an instructor");
            return acceptedInstructorInviteResponse;
        }
        throw new CohortNotFoundException("Cohort not found !!!");
    }



    @Override
    @Transactional
    public void deleteUser(User user) {
        for (Cohort cohort:cohortRepository.findAll()) {
            cohort.getUsers().remove(user);
            cohortRepository.save(cohort);
        }
    }

    @Override
    public Cohort findById(Long cohortId) throws CohortNotFoundException {
        return cohortRepository.findById(cohortId).orElseThrow(()-> new CohortNotFoundException("Cohort not found"));
    }

    @Override
    public List<Cohort> findAll() {
        return cohortRepository.findAll();
    }

    @Override
    public void save(Cohort cohort) {
        cohortRepository.save(cohort);
    }

    @Override
    @Transactional
    public AddedCourseToCohortResponse addCourse(AddCourseToCohortRequest addCourseToCohortRequest) throws CohortNotFoundException, CourseNotFoundException {
        Cohort cohort = cohortRepository.findById(addCourseToCohortRequest.getCohortId()).orElseThrow(()-> new CohortNotFoundException("Cohort not found"));
        Course course = courseService.findById(addCourseToCohortRequest.getCourseId());
        AddedCourseToCohortResponse addedCourseToCohortResponse = new AddedCourseToCohortResponse();

        if (cohort != null){
            List<Course> courses = cohort.getCourses();
            courses.add(course);
            cohort.setCourses(courses);
            cohortRepository.save(cohort);
            course.setCohort(cohort);
            courseService.save(course);

            addedCourseToCohortResponse.setCourse(course);
            addedCourseToCohortResponse.setMessage("Course added successfully");
            return addedCourseToCohortResponse;
        }
        addedCourseToCohortResponse.setMessage("Course not added");
        return addedCourseToCohortResponse;
    }

    @Override
    public List<Cohort> findCohortByOrganization(Organization organization) {
        return cohortRepository.findCohortsByOrganization(organization);
    }

    @Override
    public void deleteCohorts(List<Cohort> cohortList) {
        cohortRepository.deleteAll(cohortList);
    }

    @Override
    public InvitedLearnerResponse inviteLearnerToCohort(InviteLearnerRequest inviteLearnerRequest) {

        Sender sender = new Sender();
        sender.setName("Esther");
        sender.setEmail("ojamata.semicolon@gmail.com");

        SendMailRequest sendMailRequest = new SendMailRequest();
        sendMailRequest.setLink(inviteLearnerRequest.getLink());
        sendMailRequest.setRecipients(inviteLearnerRequest.getLearnerEmail());
        sendMailRequest.setSender(sender);
        SendMailResponse sendMailResponse = mailService.createMessageAndSendEmailToLearner(sendMailRequest);

        InvitedLearnerResponse invitedLearnerResponse = new InvitedLearnerResponse();
        invitedLearnerResponse.setSendMailResponse(sendMailResponse);
        invitedLearnerResponse.setMessage("Invite successfully sent");

        return invitedLearnerResponse;
    }

    @Override
    @Transactional
    public AcceptedLearnerInviteResponse acceptLearnerInvite(AcceptLearnerInviteRequest acceptInviteRequest) throws CohortNotFoundException {
        Optional<Cohort> foundCohort = cohortRepository.findById(acceptInviteRequest.getCohortId());
        if (foundCohort.isPresent()){
            Learner learner = acceptInviteRequest.getLearner();
            List<User> userList = foundCohort.get().getUsers();
            userList.add(learner);
            foundCohort.get().setUsers(userList);
            Cohort savedCohort = cohortRepository.save(foundCohort.get());

            AcceptedLearnerInviteResponse acceptedLearnerInviteResponse = new AcceptedLearnerInviteResponse();
            acceptedLearnerInviteResponse.setLearners(acceptInviteRequest.getLearner());
            acceptedLearnerInviteResponse.setMessage("Learner added");
            return acceptedLearnerInviteResponse;
        }
        throw new CohortNotFoundException("Cohort not found !!!");
    }

    @Override
    public List<Course> getAllCoursesInA_Cohort(Long cohortId) throws CohortNotFoundException {
        Cohort foundCohort = cohortRepository.findById(cohortId).orElseThrow(() -> new CohortNotFoundException("Cohort not found"));
        return foundCohort.getCourses();
    }

    @Override
    public List<Learner> getAllLearnersIn_A_Cohort(Long cohortId) throws CohortNotFoundException {
        List<Learner> learners = new ArrayList<>();
        Cohort foundCohort = cohortRepository.findById(cohortId).orElseThrow(() -> new CohortNotFoundException("Cohort not found"));
        List<User> users = foundCohort.getUsers();
        for (User user:users) {
            if (user.getRole()== Role.LEARNER){
                learners.add((Learner) user);
            }
        }
        return learners;
    }
    @Override
    public List<Instructor> getAllInstructorsIn_A_Cohort(Long cohortId) throws CohortNotFoundException {
        List<Instructor> instructors = new ArrayList<>();
        Cohort foundCohort = cohortRepository.findById(cohortId).orElseThrow(() -> new CohortNotFoundException("Cohort not found"));
        List<User> users = foundCohort.getUsers();
        for (User user:users) {
            if (user.getRole()== Role.INSTRUCTOR){
                instructors.add((Instructor) user);
            }
        }
        return instructors;
    }

    @Override
    public RemovedInstructorResponse removeInstructor(RemoveInstructorRequest request) throws CohortNotFoundException, UserWithThisCredentialsDoesNotExistException {
        return cohortUserService.removeInstructorFromCohort(request);
    }


}
