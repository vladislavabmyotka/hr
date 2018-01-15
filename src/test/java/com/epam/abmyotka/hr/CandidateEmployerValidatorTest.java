package com.epam.abmyotka.hr;

import com.epam.abmyotka.hr.validator.CandidateEmployerValidator;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CandidateEmployerValidatorTest {

    @Test
    public void correctIdTest() {
        String id = "4";
        Assert.assertTrue(CandidateEmployerValidator.checkID(id));
    }

    @Test
    public void incorrectIdTest() {
        String id = "-1";
        Assert.assertFalse(CandidateEmployerValidator.checkID(id));
    }

    @Test
    public void nullIdTest() {
        String id = null;
        Assert.assertFalse(CandidateEmployerValidator.checkID(id));
    }

    @Test
    public void correctNamesTest() {
        String name = "Duke";
        Assert.assertTrue(CandidateEmployerValidator.checkNames(name));
    }

    @Test
    public void incorrectNamesTest() {
        String name = "D1Uke21";
        Assert.assertFalse(CandidateEmployerValidator.checkNames(name));
    }

    @Test
    public void nullNamesTest() {
        String name = null;
        Assert.assertFalse(CandidateEmployerValidator.checkNames(name));
    }

    @Test
    public void correctLastnameTest() {
        String lastname = "Duke";
        Assert.assertTrue(CandidateEmployerValidator.checkLastname(lastname));
    }

    @Test
    public void incorrectLastnameTest() {
        String lastname = "D1Uke21";
        Assert.assertFalse(CandidateEmployerValidator.checkLastname(lastname));
    }

    @Test
    public void nullLastnameTest() {
        String lastname = null;
        Assert.assertTrue(CandidateEmployerValidator.checkLastname(lastname));
    }

    @Test
    public void correctAgeTest() {
        String age = "18";
        Assert.assertTrue(CandidateEmployerValidator.checkAge(age));
    }

    @Test
    public void incorrectAgeTest() {
        String age = "225";
        Assert.assertFalse(CandidateEmployerValidator.checkAge(age));
    }

    @Test
    public void nullAgeTest() {
        String age = null;
        Assert.assertFalse(CandidateEmployerValidator.checkAge(age));
    }

    @Test
    public void correctEmailTest() {
        String email = "qwertq23@gmail.com";
        Assert.assertTrue(CandidateEmployerValidator.checkEmail(email));
    }

    @Test
    public void incorrectEmailTest() {
        String email = "agsfdgds";
        Assert.assertFalse(CandidateEmployerValidator.checkEmail(email));
    }

    @Test
    public void nullEmailTest() {
        String email = null;
        Assert.assertFalse(CandidateEmployerValidator.checkEmail(email));
    }

    @Test
    public void correctCitizenshipTest() {
        String citizenship = "Республика Беларусь";
        Assert.assertTrue(CandidateEmployerValidator.checkCitizenship(citizenship));
    }

    @Test
    public void incorrectCitizenshipTest() {
        String citizenship = "523235";
        Assert.assertFalse(CandidateEmployerValidator.checkCitizenship(citizenship));
    }

    @Test
    public void nullCitizenshipTest() {
        String citizenship = null;
        Assert.assertFalse(CandidateEmployerValidator.checkCitizenship(citizenship));
    }

    @Test
    public void correctPhoneTest() {
        String phone = "8 (029) 123-45-67";
        Assert.assertTrue(CandidateEmployerValidator.checkPhone(phone));
    }

    @Test
    public void incorrectPhoneTest() {
        String phone = "bsdbdss";
        Assert.assertFalse(CandidateEmployerValidator.checkPhone(phone));
    }

    @Test
    public void nullPhoneTest() {
        String phone = null;
        Assert.assertFalse(CandidateEmployerValidator.checkPhone(phone));
    }

    @Test
    public void correctExperienceTest() {
        String experience = "8";
        Assert.assertTrue(CandidateEmployerValidator.checkExperience(experience));
    }

    @Test
    public void incorrectExperienceTest() {
        String experience = "bsdbdss";
        Assert.assertFalse(CandidateEmployerValidator.checkExperience(experience));
    }

    @Test
    public void nullExperienceTest() {
        String experience = null;
        Assert.assertFalse(CandidateEmployerValidator.checkExperience(experience));
    }
}
