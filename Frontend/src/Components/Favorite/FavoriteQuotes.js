import React from "react";
import QuoteList from "../Quotes/QuoteList";
import { deleteFavoriteQuote, fetchFavoriteQuotes } from "../Api/FavoriteApi";
import useNotification from "../Notification";

const FavoriteQuotes = ({ variant }) => {
  const options = ["Remove", "Share"];
  const { notify } = useNotification();
  const handleRemove = async (quote) => {
    try {
      await deleteFavoriteQuote(quote);
      notify(
        "Favorite Quote was successfully deleted.Refresh to see changes.",
        "success"
      );
    } catch (error) {
      notify("Failed to delete favorite quote.", "error");
    }
  };
  const handleShare = () => {};
  return (
    <QuoteList
      variant={variant}
      options={options}
      initialQuotesPerPage={5}
      fetchQuotes={fetchFavoriteQuotes}
      quoteActions={{
        remove: handleRemove,
        share: handleShare,
      }}
      searchKeywords={null}
    />
  );
};

export default FavoriteQuotes;
