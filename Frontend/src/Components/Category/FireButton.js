import React, { useState } from "react";
import "../../Assets/Css/FireButton.css";

const FireButton = ({ likes, handleLikes }) => {
  const [isBurning, setIsBurning] = useState(false);

  const handleClick = () => {
    setIsBurning(!isBurning);
    handleLikes();
  };

  return (
    <button
      className={`fire-button ${isBurning ? "burning" : ""}`}
      onClick={handleClick}
    >
      <span role="img" aria-label="Fire">
        🔥
      </span>
      <span>{likes}</span>
    </button>
  );
};

export default FireButton;
