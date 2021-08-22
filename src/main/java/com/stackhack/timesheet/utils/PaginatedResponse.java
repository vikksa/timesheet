package com.stackhack.timesheet.utils;

import java.util.List;

public class PaginatedResponse<T> {

  private List<T> content;
  private Integer from;
  private Integer lastPage;
  private Integer perPage;
  private Long to;
  private Long total;
  private Integer currentPage;

  public static <T> PaginatedResponse<T> toPage(org.springframework.data.domain.Page<T> page) {
    PaginatedResponse<T> responsePage = new PaginatedResponse<>();
    responsePage.setContent(page.getContent());
    responsePage.setFrom(page.getSize() * page.getNumber() + 1);
    responsePage.setLastPage(page.getTotalPages());
    responsePage.setPerPage(page.getSize());
    responsePage.setTo(page.isLast() ?
        page.getTotalElements() : (page.getNumber() + 1) * page.getSize());
    responsePage.setTotal(page.getTotalElements());
    responsePage.setCurrentPage(page.getNumber() + 1);
    return responsePage;
  }

  public List<T> getContent() {
    return content;
  }

  public void setContent(List<T> content) {
    this.content = content;
  }

  public Integer getFrom() {
    return from;
  }

  public void setFrom(Integer from) {
    this.from = from;
  }

  public Integer getLastPage() {
    return lastPage;
  }

  public void setLastPage(Integer lastPage) {
    this.lastPage = lastPage;
  }

  public Integer getPerPage() {
    return perPage;
  }

  public void setPerPage(Integer perPage) {
    this.perPage = perPage;
  }

  public Long getTo() {
    return to;
  }

  public void setTo(Long to) {
    this.to = to;
  }

  public Long getTotal() {
    return total;
  }

  public void setTotal(Long total) {
    this.total = total;
  }

  public Integer getCurrentPage() {
    return currentPage;
  }

  public void setCurrentPage(Integer currentPage) {
    this.currentPage = currentPage;
  }
}
