package com.ita.edu.softserve.entity;

import java.util.Date;
import javax.persistence.*;
/**
 * @author tranzero
 *
 */

@Entity
@Table(name = "USERS")
@NamedQuery(name = Users.FIND_BY_NAME, query = Users.FIND_BY_NAME_QUERY)
public class Users {
	
		public enum Role {
			REGUSER,
			MANAGER,
			ADMIN
		}
        public static final String FIND_BY_NAME = "Users.findByName";
        public static final String FIND_BY_NAME_QUERY = "SELECT u FROM Users u WHERE u.username = ?1";

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
        
        @Enumerated(EnumType.STRING)
        @Column(columnDefinition="enum('REGUSER','MANAGER', 'ADMIN')")
        private Role role;
        
        @Column(name = "USERNAME", nullable = false, unique = true)
        private String username;
        
        public Users() {

        }

        public Users(String username, String email, String password) {
            this.username = username;
            this.eMail = email;
            this.passwd = password;
            
        }

        public Users(String username, String firstname, String lastname, String email, String password) {
            this.username = username;
            this.firstName = firstname;
            this.lastName = lastname;
            this.eMail = email;
            this.passwd = password;
        }
        

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
        
        
        

		/**
		 * @param userId the userId to set
		 */
		private void setUserId(int userId) {
			this.userId = userId;
		}

		/**
		 * @param firstName the firstName to set
		 */
		private void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		/**
		 * @param lastName the lastName to set
		 */
		private void setLastName(String lastName) {
			this.lastName = lastName;
		}

		/**
		 * @param eMail the eMail to set
		 */
		private void seteMail(String eMail) {
			this.eMail = eMail;
		}

		/**
		 * @param passwd the passwd to set
		 */
		private void setPasswd(String passwd) {
			this.passwd = passwd;
		}

		/**
		 * @param regDate the regDate to set
		 */
		private void setRegDate(Date regDate) {
			this.regDate = regDate;
		}

		/**
		 * @param permission the permission to set
		 */
		

		

		public Role getRole() {
			return role;
		}

		private void setRole(Role role) {
			this.role = role;
		}

		@Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Users other = (Users) obj;
            if (passwd == null) {
                if (other.passwd != null)
                    return false;
            } else if (!passwd.equals(other.passwd))
                return false;
            return true;
        }
        
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((passwd == null) ? 0 :passwd.hashCode());
            return result;
        }
        

}
