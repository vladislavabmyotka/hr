package com.epam.abmyotka.hr;

import com.epam.abmyotka.hr.validator.CandidateValidator;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CandidateValidatorTest {

    @Test
    public void correctCandidateIdTest() {
        String candidateId = "4";
        Assert.assertTrue(CandidateValidator.checkID(candidateId));
    }

    @Test
    public void incorrectCandidateIdTest() {
        String candidateId = "-1";
        Assert.assertFalse(CandidateValidator.checkID(candidateId));
    }

    @Test
    public void nullCandidateIdTest() {
        String candidateId = null;
        Assert.assertFalse(CandidateValidator.checkID(candidateId));
    }

    @Test
    public void correctNamesTest() {
        String name = "Duke";
        Assert.assertTrue(CandidateValidator.checkNames(name));
    }

    @Test
    public void incorrectNamesTest() {
        String name = "D1Uke21";
        Assert.assertFalse(CandidateValidator.checkNames(name));
    }

    @Test
    public void nullNamesTest() {
        String name = null;
        Assert.assertFalse(CandidateValidator.checkNames(name));
    }

    @Test
    public void correctLastnameTest() {
        String lastname = "Duke";
        Assert.assertTrue(CandidateValidator.checkLastname(lastname));
    }

    @Test
    public void incorrectLastnameTest() {
        String lastname = "D1Uke21";
        Assert.assertFalse(CandidateValidator.checkLastname(lastname));
    }

    @Test
    public void nullLastnameTest() {
        String lastname = null;
        Assert.assertTrue(CandidateValidator.checkLastname(lastname));
    }

    @Test
    public void correctAgeTest() {
        String age = "18";
        Assert.assertTrue(CandidateValidator.checkAge(age));
    }

    @Test
    public void incorrectAgeTest() {
        String age = "225";
        Assert.assertFalse(CandidateValidator.checkAge(age));
    }

    @Test
    public void nullAgeTest() {
        String age = null;
        Assert.assertFalse(CandidateValidator.checkAge(age));
    }

    @Test
    public void correctEmailTest() {
        String email = "qwertq23@gmail.com";
        Assert.assertTrue(CandidateValidator.checkEmail(email));
    }

    @Test
    public void incorrectEmailTest() {
        String email = "agsfdgds";
        Assert.assertFalse(CandidateValidator.checkEmail(email));
    }

    @Test
    public void nullEmailTest() {
        String email = null;
        Assert.assertFalse(CandidateValidator.checkEmail(email));
    }

    @Test
    public void correctCitizenshipTest() {
        String citizenship = "Республика Беларусь";
        Assert.assertTrue(CandidateValidator.checkCitizenship(citizenship));
    }

    @Test
    public void incorrectCitizenshipTest() {
        String citizenship = "523235";
        Assert.assertFalse(CandidateValidator.checkCitizenship(citizenship));
    }

    @Test
    public void nullCitizenshipTest() {
        String citizenship = null;
        Assert.assertFalse(CandidateValidator.checkCitizenship(citizenship));
    }

    @Test
    public void correctPhoneTest() {
        String phone = "8 (029) 123-45-67";
        Assert.assertTrue(CandidateValidator.checkPhone(phone));
    }

    @Test
    public void incorrectPhoneTest() {
        String phone = "bsdbdss";
        Assert.assertFalse(CandidateValidator.checkPhone(phone));
    }

    @Test
    public void nullPhoneTest() {
        String phone = null;
        Assert.assertFalse(CandidateValidator.checkPhone(phone));
    }

    @Test
    public void correctExperienceTest() {
        String experience = "8";
        Assert.assertTrue(CandidateValidator.checkExperience(experience));
    }

    @Test
    public void incorrectExperienceTest() {
        String experience = "bsdbdss";
        Assert.assertFalse(CandidateValidator.checkExperience(experience));
    }

    @Test
    public void nullExperienceTest() {
        String experience = null;
        Assert.assertFalse(CandidateValidator.checkExperience(experience));
    }
}
