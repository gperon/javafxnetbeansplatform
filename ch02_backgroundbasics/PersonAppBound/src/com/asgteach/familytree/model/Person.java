/*
 * @(#)Person.java   2014-10-02
 * 
 * Copyright (c) 2005-2014 Luxottica Group
 * All Rights Reserved.
 * This program contains proprietary and trade secret information of Luxottica Group.
 *
 *
 *
 */



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

    /** Field description */
    public static final String PROP_FIRSTNAME = "firstname";

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
        String oldFirstname = this.firstname;
        this.firstname = firstname;
        propertyChangeSupport.firePropertyChange(PROP_FIRSTNAME, oldFirstname, firstname);
    }

    private transient final PropertyChangeSupport propertyChangeSupport =
        new PropertyChangeSupport(this);

    /**
     * Add PropertyChangeListener.
     *
     * @param listener
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    /**
     * Remove PropertyChangeListener.
     *
     * @param listener
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    private String middlename;

    /** Field description */
    public static final String PROP_MIDDLENAME = "middlename";

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
        String oldMiddlename = this.middlename;
        this.middlename = middlename;
        propertyChangeSupport.firePropertyChange(PROP_MIDDLENAME, oldMiddlename, middlename);
    }

    private String lastname;

    /** Field description */
    public static final String PROP_LASTNAME = "lastname";

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
        String oldLastname = this.lastname;
        this.lastname = lastname;
        propertyChangeSupport.firePropertyChange(PROP_LASTNAME, oldLastname, lastname);
    }

    private String suffix;

    /** Field description */
    public static final String PROP_SUFFIX = "suffix";

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
        String oldSuffix = this.suffix;
        this.suffix = suffix;
        propertyChangeSupport.firePropertyChange(PROP_SUFFIX, oldSuffix, suffix);
    }

    private String notes;

    /** Field description */
    public static final String PROP_NOTES = "notes";

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
        String oldNotes = this.notes;
        this.notes = notes;
        propertyChangeSupport.firePropertyChange(PROP_NOTES, oldNotes, notes);
    }

    private Gender gender;

    /**
     * Method description
     *
     *
     * @return
     */
    public Gender getGender() {
        return gender;
    }

    private static long count = 0;

    /**
     * Enum description
     *
     */
    public enum Gender { MALE, FEMALE, UNKNOW; }

    /**
     * Constructs ...
     *
     *
     * @param firstname
     * @param lastname
     * @param gender
     */
    public Person(String firstname, String lastname, Gender gender) {
        middlename = "";
        suffix = "";
        id = count++;
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
    }

    /**
     * Constructs ...
     *
     */
    public Person() {
        this("", "", Gender.UNKNOW);
    }

    /**
     * Constructs ...
     *
     *
     * @param person
     */
    public Person(Person person) {
        firstname = person.getFirstname();
        middlename = person.getMiddlename();
        lastname = person.getLastname();
        suffix = person.getSuffix();
        gender = person.getGender();
        notes = person.getNotes();
        id = person.getId();
    }

    /**
     * Method description
     *
     *
     * @return
     */
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

    /**
     * Method description
     *
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (int) (this.id ^ (this.id >>> 32));

        return hash;
    }

    /**
     * Method description
     *
     *
     * @param obj
     *
     * @return
     */
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
