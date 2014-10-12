/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asgteach.familytree.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

/**
 *
 * @author GiorgioP
 */
public class Person implements Serializable {

    private long id;

    /**
     * Get the value of id
     *
     * @return the value of id
     */
    public long getId() {
        return id;
    }

    private String firstname;


    /**
     * Get the value of firstname
     *
     * @return the value of firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Set the value of firstname
     *
     * @param firstname new value of firstname
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }


    private String middlename;

    /**
     * Get the value of middlename
     *
     * @return the value of middlename
     */
    public String getMiddlename() {
        return middlename;
    }

    /**
     * Set the value of middlename
     *
     * @param middlename new value of middlename
     */
    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    private String lastname;

    /**
     * Get the value of lastname
     *
     * @return the value of lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Set the value of lastname
     *
     * @param lastname new value of lastname
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    private String suffix;

    /**
     * Get the value of suffix
     *
     * @return the value of suffix
     */
    public String getSuffix() {
        return suffix;
    }

    /**
     * Set the value of suffix
     *
     * @param suffix new value of suffix
     */
    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    private String notes;

    /**
     * Get the value of notes
     *
     * @return the value of notes
     */
    public String getNotes() {
        return notes;
    }

    /**
     * Set the value of notes
     *
     * @param notes new value of notes
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }
    private Gender gender;

    public Gender getGender() {
        return gender;
    }
    private static long count = 0;

    public enum Gender {

        MALE, FEMALE, UNKNOW;
    }

    public Person(String firstname, String lastname, Gender gender) {
        middlename = "";
        suffix = "";
        id = count++;
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
    }

    public Person() {
        this("", "", Gender.UNKNOW);
    }

    public Person(Person person) {
        firstname = person.getFirstname();
        middlename = person.getMiddlename();
        lastname = person.getLastname();
        suffix = person.getSuffix();
        gender = person.getGender();
        notes = person.getNotes();
        id = person.getId();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (!firstname.isEmpty()) {
            sb.append(firstname);
        }
        if (!middlename.isEmpty()) {
            sb.append(" ").append(middlename);
        }
        if (!lastname.isEmpty()) {
            sb.append(" ").append(lastname);
        }
        if (!suffix.isEmpty()) {
            sb.append(" ").append(suffix);
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Person other = (Person) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}
