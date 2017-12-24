package com.epam.abmyotka.hr.entity;

public class Interview extends Entity {
    private int candidateId;
    private int vacancyId;
    private boolean preResult;
    private boolean finalResult;

    public int getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(int candidateId) {
        this.candidateId = candidateId;
    }

    public int getVacancyId() {
        return vacancyId;
    }

    public void setVacancyId(int vacancyId) {
        this.vacancyId = vacancyId;
    }

    public boolean isPreResult() {
        return preResult;
    }

    public void setPreResult(boolean preResult) {
        this.preResult = preResult;
    }

    public boolean isFinalResult() {
        return finalResult;
    }

    public void setFinalResult(boolean finalResult) {
        this.finalResult = finalResult;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Interview interview = (Interview) o;

        return candidateId == interview.candidateId && vacancyId == interview.vacancyId &&
                preResult == interview.preResult && finalResult == interview.finalResult;
    }

    @Override
    public int hashCode() {
        int result = candidateId;
        result = 31 * result + vacancyId;
        result = 31 * result + (preResult ? 1 : 0);
        result = 31 * result + (finalResult ? 1 : 0);
        return result;
    }
}
