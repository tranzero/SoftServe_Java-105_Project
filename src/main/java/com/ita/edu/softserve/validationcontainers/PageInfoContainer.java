package com.ita.edu.softserve.validationcontainers;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
//@Scope("request")
public interface PageInfoContainer {

	void setResultsPerPage(Integer resultsPerPage);

	void setMaxPages(Integer maxPages);

	Integer getPageNumber();

	void setPageNumber(Integer pageNumber);

	Integer getResultsPerPage();

	Integer getMaxPages();

	long getCount();

	void setCount(long count);

}
