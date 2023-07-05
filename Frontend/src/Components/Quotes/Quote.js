import React, { useState } from "react";
import { Button, Card, Dropdown } from "react-bootstrap";
import "../../Assets/Css/UserProfile.css";
import { addLikeQuote } from "../Api/LikesApi";
import useNotification from "../Notification";
import FireButton from "../Category/FireButton";

// Updated Quote component to include variant prop
const Quote = ({ quote, options, variant, quoteActions }) => {
  const { notify } = useNotification();
  const [likes, setLikes] = useState(quote.likeCount); // Initialize likes state with quote.likes

  const handleOptionSelect = (option) => {
    switch (option) {
      case "Update":
        quoteActions.update && quoteActions.update(quote);
        break;
      case "Delete":
        quoteActions.delete && quoteActions.delete(quote);
        break;
      case "Remove":
        quoteActions.remove && quoteActions.remove(quote);
        break;
      case "Share":
        quoteActions.share && quoteActions.share(quote);
        break;
      case "Add to Favorites":
        quoteActions.add && quoteActions.add(quote);
        break;
      default:
        break;
    }
  };
  const handleLike = async () => {
    try {
      const response = await addLikeQuote(quote.id);
      quote.likeCount++; // Set likes state
      notify("You successfully liked this quote.", "success");
    } catch (error) {
      notify("Failed to like this quote.", "error");
    }
  };
  const renderDropdown = () => {
    if (options && options.length > 0) {
      return (
        <Dropdown className="custom-dropdown">
          <Dropdown.Toggle
            variant="secondary"
            size="sm"
            className="dropdown-no-arrow"
          >
            ⋮
          </Dropdown.Toggle>
          <Dropdown.Menu>
            {options.map((option, index) => (
              <Dropdown.Item
                key={index}
                onClick={() => handleOptionSelect(option)}
              >
                {option}
              </Dropdown.Item>
            ))}
          </Dropdown.Menu>
        </Dropdown>
      );
    }
  };

  return (
    quote && (
      <Card
        className="mb-3"
        bg={variant}
        text={variant === "light" ? "dark" : "white"}
        style={{ width: "100%" }}
      >
        <Card.Header className="d-flex justify-content-between align-items-center">
          <span>
            {quote.author
              ? quote.author.name
              : quote.user
              ? quote.user.name
              : ""}
          </span>
          {renderDropdown()}
        </Card.Header>
        <Card.Body>
          <Card.Title>“{quote.description}”</Card.Title>
        </Card.Body>
        <div className="likes-container">
          <div className="category-container">#{quote.category.name}</div>

          <FireButton
            likes={quote.likeCount}
            handleLikes={handleLike}
          ></FireButton>
        </div>
      </Card>
    )
  );
};

export default Quote;
