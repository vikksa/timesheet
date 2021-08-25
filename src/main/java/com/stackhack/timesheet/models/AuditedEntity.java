package com.stackhack.timesheet.models;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditedEntity {

    @NotNull
    @CreatedDate
    private Date createdAt;
    @LastModifiedDate
    private Date updatedAt;
    @CreatedBy
    @NotNull
    private String createdBy;
    @LastModifiedBy
    private String updatedBy;


    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date modifiedAt) {
        this.updatedAt = modifiedAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String modifiedBy) {
        this.updatedBy = modifiedBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        AuditedEntity that = (AuditedEntity) o;
        return Objects.equals(createdAt, that.createdAt) &&
                Objects.equals(updatedAt, that.updatedAt) &&
                Objects.equals(createdBy, that.createdBy) &&
                Objects.equals(updatedBy, that.updatedBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(createdAt, updatedAt, createdBy, updatedBy);
    }

    @Override
    public String toString() {
        return "AuditedEntity{" +
                "createdAt=" + createdAt +
                ", modifiedAt=" + updatedAt +
                ", createdBy='" + createdBy + '\'' +
                ", modifiedBy='" + updatedBy + '\'' +
                '}';
    }
}
