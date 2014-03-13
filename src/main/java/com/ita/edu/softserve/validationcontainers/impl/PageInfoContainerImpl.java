package com.ita.edu.softserve.validationcontainers.impl;

import org.springframework.stereotype.Component;

import com.ita.edu.softserve.validationcontainers.PageInfoContainer;

@Component
//@Scope("request")
public class PageInfoContainerImpl implements PageInfoContainer {
	private Integer pageNumber;
	private Integer resultsPerPage;
	private Integer maxPages;
	private long count;
	
	public PageInfoContainerImpl(){}
	public PageInfoContainerImpl(Integer pageNumber, Integer resultsPerPage,
			long count) {
		super();
		this.pageNumber = pageNumber;
		this.resultsPerPage = resultsPerPage;
		this.count = count;
	}
	/**
	 * @return the pageNumber
	 */
	@Override
	public Integer getPageNumber() {
		return pageNumber;
	}
	/**
	 * @param pageNumber the pageNumber to set
	 */
	@Override
	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}
	/**
	 * @return the resultsPerPage
	 */
	@Override
	public Integer getResultsPerPage() {
		return resultsPerPage;
	}
	/**
	 * @param resultsPerPage the resultsPerPage to set
	 */
	@Override
	public void setResultsPerPage(Integer resultsPerPage) {
		this.resultsPerPage = resultsPerPage;
	}
	/**
	 * @return the maxPages
	 */
	@Override
	public Integer getMaxPages() {
		return maxPages;
	}
	/**
	 * @param maxPages the maxPages to set
	 */
	@Override
	public void setMaxPages(Integer maxPages) {
		this.maxPages = maxPages;
	}
	/**
	 * @return the count
	 */
	@Override
	public long getCount() {
		return count;
	}
	/**
	 * @param count the count to set
	 */
	@Override
	public void setCount(long count) {
		this.count = count;
	}
}
