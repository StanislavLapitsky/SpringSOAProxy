package org.proxysoa.spring.dto;

/**
 * Represents simple page request - offset, page size (rows on page) and sort.
 *
 * @author stanislav.lapitsky created 4/14/2017.
 */
public class SimplePageRequest {
    public static final int DEFAULT_PAGE_SIZE = 10;

    private int offset = 0;
    private int pageSize = DEFAULT_PAGE_SIZE;
    private String sort;

    /**
     * Default constructor
     */
    public SimplePageRequest() {
    }

    /**
     * Constructor with desired params
     *
     * @param offset   offset
     * @param pageSize page size (rows on page)
     * @param sort     sorting info
     */
    public SimplePageRequest(int offset, int pageSize, String sort) {
        this.offset = offset;
        this.pageSize = pageSize;
        this.sort = sort;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}
