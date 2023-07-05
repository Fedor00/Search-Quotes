import React, { useState, useEffect } from "react";
import QuoteList from "../Quotes/QuoteList";
import {
  fetchPersonalQuotes,
  updatePersonalQuote,
  deletePersonalQuote,
} from "../Api/QuoteApi";
import useNotification from "../Notification";
import { Button, Modal } from "react-bootstrap";
import QuoteForm from "../Quotes/QuoteUpdateOrDelete";

const PersonalQuotes = ({ variant, create, setCreate }) => {
  const [showModal, setShowModal] = useState(false);
  const [currentQuote, setCurrentQuote] = useState(null);
  const options = ["Delete", "Update", "Share"];
  const { notify } = useNotification();

  useEffect(() => {
    if (create) {
      setShowModal(true);
    }
  }, [create]);

  const handleDelete = async (quote) => {
    try {
      await deletePersonalQuote(quote);
      notify("Quote was successfully deleted.", "success");
    } catch (error) {
      notify("Failed to delete quote.", "error");
    }
  };

  const handleClose = () => {
    setCurrentQuote(null);
    setShowModal(false);
    setCreate(false);
  };

  const handleUpdate = async (quote) => {
    setCurrentQuote(quote);
    setShowModal(true);
  };

  const handleShare = (quote) => {
    // Implement your share logic here
  };

  return (
    <>
      <QuoteList
        variant={variant}
        options={options}
        initialQuotesPerPage={5}
        fetchQuotes={fetchPersonalQuotes}
        quoteActions={{
          update: handleUpdate,
          delete: handleDelete,
          share: handleShare,
        }}
        searchKeywords={null}
      />
      <Modal show={showModal} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>{create ? "Add Quote" : "Update Quote"}</Modal.Title>
        </Modal.Header>

        <Modal.Body>
          <QuoteForm
            handleModalClose={handleClose}
            existingQuote={currentQuote}
          />
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleClose}>
            Close
          </Button>
        </Modal.Footer>
      </Modal>
    </>
  );
};

export default PersonalQuotes;
