import React, { useState } from "react";
import { Card, DropdownButton, Dropdown, Button } from "react-bootstrap";
import "../../Assets/Css/UserProfile.css";
import FavoriteQuotes from "../Favorite/FavoriteQuotes";
import PersonalQuotes from "../Personal/PersonalQuotes";
const UserProfile = () => {
  const [selectedQuoteType, setSelectedQuoteType] = useState("Favorite Quotes");
  const [create, setCreate] = useState(false);

  const handleSelect = (eventKey) => {
    setSelectedQuoteType(eventKey);
  };
  const handleCreate = () => {
    setCreate(true);
  };
  return (
    <div className="user-profile-container">
      <Card className="user-profile-card">
        <Card.Body>
          <div className="dropdown-wrapper d-flex">
            {selectedQuoteType === "Personal Quotes" && (
              <Button variant="info" className="mr-2" onClick={handleCreate}>
                Add
              </Button>
            )}

            <DropdownButton
              variant="secondary"
              id="quote-type-dropdown"
              title={selectedQuoteType}
              onSelect={handleSelect}
              className="custom-dropdown-button custom-dropdown ml-2"
            >
              <Dropdown.Item eventKey="Personal Quotes">
                Personal Quotes
              </Dropdown.Item>
              <Dropdown.Item eventKey="Favorite Quotes">
                Favorite Quotes
              </Dropdown.Item>
            </DropdownButton>
          </div>

          {selectedQuoteType === "Personal Quotes" ? (
            <>
              <Card.Body className="custom-text">
                <PersonalQuotes
                  variant={"dark"}
                  create={create}
                  setCreate={setCreate}
                />
              </Card.Body>
            </>
          ) : selectedQuoteType === "Favorite Quotes" ? (
            <Card.Body className="custom-text">
              <FavoriteQuotes variant={"dark"} />
            </Card.Body>
          ) : null}
        </Card.Body>
      </Card>
    </div>
  );
};

export default UserProfile;
