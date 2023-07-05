import React, { useEffect, useState, useContext } from "react";

import "../../Assets/Css/Quote.css";
import AuthContext from "../../Context/AuthContext";
import { fetchDailyQuote } from "../Api/QuoteApi";

const DailyQuote = () => {
  const [quote, setQuote] = useState(null);
  const [isLoading, setIsLoading] = useState(true);
  const { isLoggedIn } = useContext(AuthContext);

  useEffect(() => {
    fetchRandomQuote();
  }, []);

  const fetchRandomQuote = async () => {
    setIsLoading(true);
    try {
      const response = await fetchDailyQuote();
      setQuote(response);
    } catch (error) {
      console.error("Error fetching random quote:", error);
    } finally {
      setIsLoading(false);
    }
  };

  return (
    isLoggedIn && (
      <div className="container">
        <div className="quote-container">
          {isLoading ? (
            <p>Loading...</p>
          ) : (
            quote && (
              <div className="quote-text-container">
                <p>{quote.description}</p>
                <p>— {quote.author ? quote.author.name : "Unknown author"}</p>
              </div>
            )
          )}
        </div>
      </div>
    )
  );
};

export default DailyQuote;
