import React, { useEffect, useState } from "react";
import { Form } from "react-bootstrap";

import Quote from "./Quote";
import QuotePagination from "./Pagination";

const QuoteList = ({
  variant,
  options,
  initialQuotesPerPage,
  fetchQuotes,
  quoteActions,
  searchKeywords,
}) => {
  const [activePage, setActivePage] = useState(1);
  const [quotes, setQuotes] = useState([]);
  const [quotesPerPage, setQuotesPerPage] = useState(initialQuotesPerPage);
  const [totalPages, setTotalPages] = useState(0);

  useEffect(() => {
    const loadQuotes = async () => {
      let fetchedQuotes;
      if (searchKeywords) {
        // If searchKeywords are available, pass them to fetchQuotes
        fetchedQuotes = await fetchQuotes(
          searchKeywords,
          activePage,
          quotesPerPage
        );
      } else {
        // If searchKeywords are not available, fetch quotes without them
        fetchedQuotes = await fetchQuotes(activePage, quotesPerPage);
      }
      setQuotes(fetchedQuotes.quotes);
      setTotalPages(fetchedQuotes.totalPages);
    };
    loadQuotes();
  }, [searchKeywords, activePage, quotesPerPage, fetchQuotes]);

  const quotesPerPageOptions = [5, 10, 15, 20];
  const renderQuotesPerPageDropdown = () => (
    <Form.Control
      as="select"
      value={quotesPerPage}
      onChange={(e) => {
        setQuotesPerPage(parseInt(e.target.value));
      }}
      className="mb-3"
      style={{ width: "auto", display: "inline-block" }}
    >
      {quotesPerPageOptions.map((option) => (
        <option key={option} value={option}>
          {option}
        </option>
      ))}
    </Form.Control>
  );

  return (
    <div>
      {renderQuotesPerPageDropdown()}
      {quotes.map((quote, index) => (
        <Quote
          key={index}
          quote={quote}
          options={options}
          variant={variant}
          quoteActions={quoteActions}
        />
      ))}
      <QuotePagination
        totalPages={totalPages}
        activePage={activePage}
        setActivePage={setActivePage}
      />
    </div>
  );
};
export default QuoteList;
