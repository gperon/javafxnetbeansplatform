/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asgteach.familytree.model;

import java.io.Serializable;
import java.util.Objects;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author gail
 */
public class Person implements Serializable {

    private final long id;
    private final StringProperty firstname =
            new SimpleStringProperty(this, "firstname", "");
    private final StringProperty middlename =
            new SimpleStringProperty(this, "middlename", "");
    private final StringProperty lastname =
            new SimpleStringProperty(this, "lastname", "");
    private final StringProperty suffix =
            new SimpleStringProperty(this, "suffix", "");
    private final ObjectProperty<Person.Gender> gender =
            new SimpleObjectProperty<>(this, "gender", Gender.UNKNOWN);
    private final StringProperty notes =
            new SimpleStringProperty(this, "notes", "");
    
    private final StringBinding fullNameBinding = new StringBinding() {
        {
            super.bind(firstname, middlename, lastname, suffix);
        }

        @Override
        protected String computeValue() {
            StringBuilder sb = new StringBuilder();
            if (!firstname.get().isEmpty()) {
                sb.append(firstname.get());
            }
            if (!middlename.get().isEmpty()) {
                sb.append(" ").append(middlename.get());
            }
            if (!lastname.get().isEmpty()) {
                sb.append(" ").append(lastname.get());
            }
            if (!suffix.get().isEmpty()) {
                sb.append(" ").append(suffix.get());
            }
            return sb.toString();
        }
    };
    private final ReadOnlyStringWrapper fullname =
            new ReadOnlyStringWrapper(this, "fullname");
    private static long count = 0;

    public enum Gender {
        MALE, FEMALE, UNKNOWN
    }

    public Person() {
        this("", "", Gender.UNKNOWN);
    }

    public Person(String first, String last, Person.Gender gender) {
        this.firstname.set(first);
        this.lastname.set(last);
        this.gender.set(gender);
        this.id = count++;
        this.fullname.bind(fullNameBinding);
    }

    public Person(Person person) {
        this.firstname.set(person.getFirstname());
        this.middlename.set(person.getMiddlename());
        this.lastname.set(person.getLastname());
        this.suffix.set(person.getSuffix());
        this.gender.set(person.getGender());
        this.notes.set(person.getNotes());
        this.id = person.getId();
        this.fullname.bind(fullNameBinding);
    }

    public final long getId() {
        return id;
    }

    public final ReadOnlyStringProperty fullnameProperty() {
        return fullname.getReadOnlyProperty();
    }

    public final String getFullname() {
        return fullname.get();
    }

    public String getNotes() {
        return notes.get();
    }

    public void setNotes(String notes) {
        this.notes.set(notes);
    }

    public final StringProperty notesProperty() {
        return notes;
    }

    public String getFirstname() {
        return firstname.get();
    }

    public void setFirstname(String firstname) {
        this.firstname.set(firstname);
    }

    public final StringProperty firstnameProperty() {
        return firstname;
    }

    public Person.Gender getGender() {
        return gender.get();
    }

    public void setGender(Person.Gender gender) {
        this.gender.set(gender);
    }

    public final ObjectProperty<Person.Gender> genderProperty() {
        return gender;
    }

    public String getLastname() {
        return lastname.get();
    }

    public void setLastname(String lastname) {
        this.lastname.set(lastname);
    }

    public final StringProperty lastnameProperty() {
        return lastname;
    }

    public String getMiddlename() {
        return middlename.get();
    }

    public void setMiddlename(String middlename) {
        this.middlename.set(middlename);
    }

    public final StringProperty middlenameProperty() {
        return middlename;
    }

    public String getSuffix() {
        return suffix.get();
    }

    public void setSuffix(String suffix) {
        this.suffix.set(suffix);
    }

    public final StringProperty suffixProperty() {
        return suffix;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.id)
                + Objects.hashCode(this.fullname.get())
                + Objects.hashCode(this.notes.get())
                + Objects.hashCode(this.gender.get());
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Person other = (Person) obj;
        return Objects.equals(this.id, other.id)
                && Objects.equals(this.fullname.get(), other.fullname.get())
                && Objects.equals(this.notes.get(), other.notes.get())
                && Objects.equals(this.gender.get(), other.gender.get());
    }

    @Override
    public String toString() {
        return fullname.get();
    }
}
