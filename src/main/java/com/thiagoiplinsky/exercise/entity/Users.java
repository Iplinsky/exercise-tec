package com.thiagoiplinsky.exercise.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.codec.binary.Base64;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thiagoiplinsky.exercise.config.ByteUtil;

@Entity
public class Users implements Serializable {
	private static final long serialVersionUID = 201904161725L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Size(min = 2, max = 15, message = "Enter your first name.")
	private String firstName;

	@Size(min = 2, max = 15, message = "Enter your last name.")
	private String lastName;

	@Transient
	private String imagemString;

	@JsonIgnore
	private Byte[] imgLogo;

	@NotNull
	private LocalDateTime creationDate;

	@NotNull
	private LocalDateTime changeDate;

	public Users() {
	}

	public Users(Integer id, @Size(min = 2, max = 15) String firstName, @Size(min = 2, max = 15) String lastName,
			@NotNull LocalDateTime creationDate, @NotNull LocalDateTime changeDate) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.creationDate = creationDate;
		this.changeDate = changeDate;
	}

//	Conversão para byte
	public String getImagemBase64() {
		if (this.imgLogo == null) {
			return null;
		}
		return Base64.encodeBase64String(ByteUtil.convertByteArray(this.imgLogo));
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getImagemString() {
		return imagemString;
	}

	public void setImagemString(String imagemString) {
		this.imagemString = imagemString;
	}

	public Byte[] getImgLogo() {
		return imgLogo;
	}

	public void setImgLogo(Byte[] imgLogo) {
		this.imgLogo = imgLogo;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public LocalDateTime getChangeDate() {
		return changeDate;
	}

	public void setChangeDate(LocalDateTime changeDate) {
		this.changeDate = changeDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((changeDate == null) ? 0 : changeDate.hashCode());
		result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		return result;
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
		if (changeDate == null) {
			if (other.changeDate != null)
				return false;
		} else if (!changeDate.equals(other.changeDate))
			return false;
		if (creationDate == null) {
			if (other.creationDate != null)
				return false;
		} else if (!creationDate.equals(other.creationDate))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", creationDate="
				+ creationDate + ", changeDate=" + changeDate + "]";
	}
}