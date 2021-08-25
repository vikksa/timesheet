package com.stackhack.timesheet.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.ObjectUtils;

public class PaginationRequest {

    private static final Integer MAX_PAGE_SIZE = 100;

    Integer page = 0;
    Integer perPage = 20;
    String sortOrder = "desc";
    String sortBy;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        // Workaround for one-indexed-parameters
        if (page == null || page <= 0) {
            page = 0;
        } else {
            page -= 1;
        }

        this.page = page;
    }

    public Integer getPerPage() {
        return perPage;
    }

    public void setPerPage(Integer perPage) {
        if (perPage == null) {
            perPage = 20;
        }

        if (perPage > MAX_PAGE_SIZE) {
            perPage = MAX_PAGE_SIZE;
        }

        this.perPage = perPage;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public Pageable toPageable(GetSortbyFunc sortbyFunc) {
        SortOrder so = SortOrder.DESC;
        try {
            so = SortOrder.valueOf(sortOrder.toUpperCase());
        } catch (Exception ex) {
            // Ignore
        }

        Sort sort = Sort.unsorted();
        if (!ObjectUtils.isEmpty(sortBy)) {
            sort = Sort.by(sortbyFunc.getSortBy(sortBy));
            sort = so == SortOrder.ASC ? sort.ascending() : sort.descending();
        }

        return PageRequest.of(page, perPage, sort);
    }

    public enum SortOrder {
        ASC,
        DESC
    }


    @FunctionalInterface
    public interface GetSortbyFunc {
        String getSortBy(String sortBy);
    }
}
