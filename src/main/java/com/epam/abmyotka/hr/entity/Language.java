package com.epam.abmyotka.hr.entity;

public class Language extends Entity {
    private int languageId;
    private String name;
    private String level;

    public int getLanguageId() {
        return languageId;
    }

    public void setLanguageId(int languageId) {
        this.languageId = languageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Language language = (Language) o;

        return languageId == language.languageId &&
                (name != null ? name.equals(language.name) : language.name == null) &&
                (level != null ? level.equals(language.level) : language.level == null);
    }

    @Override
    public int hashCode() {
        int result = languageId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (level != null ? level.hashCode() : 0);
        return result;
    }
}
