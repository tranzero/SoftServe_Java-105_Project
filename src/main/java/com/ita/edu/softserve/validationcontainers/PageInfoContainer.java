package com.ita.edu.softserve.validationcontainers;

public class PageInfoContainer {
	private Integer pageNumber;
	private Integer resultsPerPage;
	private Integer maxPages;
	private long count;
	

	public PageInfoContainer(Integer pageNumber, Integer resultsPerPage,
			long count) {
		super();
		this.pageNumber = pageNumber;
		this.resultsPerPage = resultsPerPage;
		this.count = count;
	}
	/**
	 * @return the pageNumber
	 */
	public Integer getPageNumber() {
		return pageNumber;
	}
	/**
	 * @param pageNumber the pageNumber to set
	 */
	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}
	/**
	 * @return the resultsPerPage
	 */
	public Integer getResultsPerPage() {
		return resultsPerPage;
	}
	/**
	 * @param resultsPerPage the resultsPerPage to set
	 */
	public void setResultsPerPage(Integer resultsPerPage) {
		this.resultsPerPage = resultsPerPage;
	}
	/**
	 * @return the maxPages
	 */
	public Integer getMaxPages() {
		return maxPages;
	}
	/**
	 * @param maxPages the maxPages to set
	 */
	public void setMaxPages(Integer maxPages) {
		this.maxPages = maxPages;
	}
	/**
	 * @return the count
	 */
	public long getCount() {
		return count;
	}
	/**
	 * @param count the count to set
	 */
	public void setCount(long count) {
		this.count = count;
	}
}
