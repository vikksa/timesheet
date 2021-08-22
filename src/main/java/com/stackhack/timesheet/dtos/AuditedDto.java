package com.stackhack.timesheet.dtos;

import com.stackhack.timesheet.models.AuditedEntity;
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


public abstract class AuditedDto {

  private Long createdAt;
  private Long updatedAt;
  private String createdBy;
  private String updatedBy;

  public AuditedDto() {
  }

  public AuditedDto(AuditedEntity auditedEntity) {
    this.createdAt = auditedEntity.getCreatedAt().getTime();
    this.updatedAt = auditedEntity.getUpdatedAt().getTime();
    this.createdBy = auditedEntity.getCreatedBy();
    this.updatedBy = auditedEntity.getUpdatedBy();
  }

  public Long getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Long createdAt) {
    this.createdAt = createdAt;
  }

  public Long getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Long updatedAt) {
    this.updatedAt = updatedAt;
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
    AuditedDto that = (AuditedDto) o;
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
