package com.ita.edu.softserve.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.*;

/**
 * @author admin
 *
 */

@Entity
@Table(name = "USERS")
public class Users {
        @Id
        @Column(name = "USERID", nullable = false)
        @GeneratedValue(strategy = GenerationType.AUTO)
        private int userId;
        
        @Column(name = "FIRSTNAME", length = 100)
        private String firstName;
        
        @Column(name = "LASTNAME", length = 100)
        private String lastName;
        
        @Column(name = "EMAIL", length = 100)
        private String eMail;
        
        @Column(name = "PASSWD", length = 100)
        private String passwd;
        
        @Column(name = "REGDATE")
        private Date regDate;
        
        @Column(name = "PERMISSION", nullable = false)
        private Permission permission;
        

        /**
         * @return the userId
         */
        public int getUserId() {
                return userId;
        }

        /**
         * @return the firstName
         */
        public String getFirstName() {
                return firstName;
        }

        /**
         * @return the lastName
         */
        public String getLastName() {
                return lastName;
        }

        /**
         * @return the eMail
         */
        public String geteMail() {
                return eMail;
        }

        /**
         * @return the passwd
         */
        public String getPasswd() {
                return passwd;
        }

        /**
         * @return the regDate
         */
        public Date getRegDate() {
                return regDate;
        }

        /**
         * @return the permission
         */
        public Permission getPermission() {
                return permission;
        }

        /**
         * @param userId the userId to set
         */
        public void setUserId(int userId) {
                this.userId = userId;
        }

        /**
         * @param firstName the firstName to set
         */
        public void setFirstName(String firstName) {
                this.firstName = firstName;
        }

        /**
         * @param lastName the lastName to set
         */
        public void setLastName(String lastName) {
                this.lastName = lastName;
        }

        /**
         * @param eMail the eMail to set
         */
        public void seteMail(String eMail) {
                this.eMail = eMail;
        }

        /**
         * @param passwd the passwd to set
         */
        public void setPasswd(String passwd) {
                this.passwd = passwd;
        }

        /**
         * @param regDate the regDate to set
         */
        public void setRegDate(Date regDate) {
                this.regDate = regDate;
        }

        /**
         * @param permission the permission to set
         */
        public void setPermission(Permission permission) {
                this.permission = permission;
        }
        

}
