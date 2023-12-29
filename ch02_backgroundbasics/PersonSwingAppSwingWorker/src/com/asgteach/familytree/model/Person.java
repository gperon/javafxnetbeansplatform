/*
 * @(#)Person.java   2023-12-29
 *
 * Copyright 2024 Giorgio Peron <giorgio.peron@gmail.com>, Belluno, Italy
 * All rights reserved.
 *
 * Redistribution and use of this script, with or without modification, is
 * permitted provided that the following conditions are met:
 *
 * 1. Redistributions of this script must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 *  THIS SOFTWARE IS PROVIDED BY THE AUTHOR ''AS IS'' AND ANY EXPRESS OR IMPLIED
 *  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
 *  MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO
 *  EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 *  SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 *  PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS;
 *  OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 *  WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR
 *  OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 *  ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
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
    public static final String PROP_FIRSTNAME = "firstname";
    public static final String PROP_MIDDLENAME = "middlename";
    public static final String PROP_LASTNAME = "lastname";
    public static final String PROP_SUFFIX = "suffix";
    public static final String PROP_NOTES = "notes";
    private static long count = 0;
    private transient final PropertyChangeSupport propertyChangeSupport =
        new PropertyChangeSupport(this);
    private long id;
    private String firstname;
    private String middlename;
    private String lastname;
    private String suffix;
    private String notes;
    private Gender gender;

    public enum Gender { MALE, FEMALE, UNKNOW; }

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

    public Person(String firstname, String lastname, Gender gender) {
        middlename = "";
        suffix = "";
        id = count++;
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
    }

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

    /**
     * Get the value of id
     *
     * @return the value of id
     */
    public long getId() {
        return id;
    }

    /**
     * Get the value of firstname
     *
     * @return the value of firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Get the value of middlename
     *
     * @return the value of middlename
     */
    public String getMiddlename() {
        return middlename;
    }

    /**
     * Get the value of lastname
     *
     * @return the value of lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Get the value of suffix
     *
     * @return the value of suffix
     */
    public String getSuffix() {
        return suffix;
    }

    /**
     * Get the value of notes
     *
     * @return the value of notes
     */
    public String getNotes() {
        return notes;
    }

    public Gender getGender() {
        return gender;
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

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
