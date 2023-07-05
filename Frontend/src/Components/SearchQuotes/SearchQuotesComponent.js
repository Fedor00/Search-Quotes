import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { FaSearch } from "react-icons/fa";
import { fetchCategoriesByKeyword } from "../Api/CategoryApi";
import DailyQuote from "../Quotes/DailyQuote";
import Switch from "react-switch";
import { Button } from "react-bootstrap";
import QuoteList from "../Quotes/QuoteList";
import {
  fetchQuotesByAuthor,
  fetchQuotesByKeyword,
  fetchQuotesSortedByLikes,
} from "../Api/QuoteApi";
import "../../Assets/Css/SearchQuotes.css";
import { addFavoriteQuote } from "../Api/FavoriteApi";
import useNotification from "../Notification";
import QuoteUpdateOrDelete from "../Quotes/QuoteUpdateOrDelete";

function SearchQuotesComponent() {
  const navigate = useNavigate();
  const [keyword, setKeyword] = useState("");
  const [byLikes, setByLikes] = useState(true);
  const [checked, setChecked] = useState(false);
  const { notify } = useNotification();

  const handleChange = (checked) => {
    setChecked(checked);
  };

  const handleInputChange = (event) => {
    setKeyword(event.target.value);
    if (event.target.value === "") {
      setByLikes(true);
    }
  };

  const getFetchQuotesFunction = () => {
    if (byLikes && keyword === "") {
      return fetchQuotesSortedByLikes;
    } else {
      return checked ? fetchQuotesByAuthor : fetchQuotesByKeyword;
    }
  };
  const handleAddToFavorite = async (quote) => {
    try {
      await addFavoriteQuote(quote.id);
      notify("Quote was successfully added o favorites.", "success");
    } catch (error) {
      notify("Failed to delete quote.", "error");
    }
  };
  const handleShare = (quote) => {};
  const handleSeeBio = (quote) => {};

  return (
    <div>
      <div className="quote-search-container">
        <div className="input-container">
          <FaSearch id="search-icon" />
          <input
            type="search"
            className="quote-search-input"
            placeholder={checked ? "Search by Author" : "Search by Keyword"}
            value={keyword}
            onChange={handleInputChange}
          />

          <Switch
            onChange={handleChange}
            checked={checked}
            handleDiameter={28}
            uncheckedIcon={false}
            checkedIcon={false}
            height={20}
            width={48}
            offColor="#282c34"
            onColor="#61dafb"
          />
          <div className="search-button">
            <Button variant="dark">go</Button>
          </div>
        </div>
      </div>
      <div className="quotes-container">
        <div className="all-quotes">
          <QuoteList
            variant="dark"
            options={["Add to Favorites", "share", "Bio"]}
            initialQuotesPerPage={5}
            fetchQuotes={getFetchQuotesFunction()}
            quoteActions={{
              add: handleAddToFavorite,
              share: handleShare,
              bio: handleSeeBio,
            }}
            searchKeywords={keyword}
          />
        </div>
      </div>
    </div>
  );
}

export default SearchQuotesComponent;
