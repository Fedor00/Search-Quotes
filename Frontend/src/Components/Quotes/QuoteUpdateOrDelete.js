import React, { useState, useEffect } from "react";
import { Form, Button, Container } from "react-bootstrap";
import { fetchCategoriesByKeyword } from "../Api/CategoryApi";
import { addPersonalQuote, updatePersonalQuote } from "../Api/QuoteApi";
import useNotification from "../Notification";
import CategorySearch from "../Category/CategorySearch";

const QuoteForm = ({ handleModalClose, existingQuote = null }) => {
  const [quote, setQuote] = useState(
    existingQuote || { description: "", category: { name: "" } }
  );
  const [searchTerm, setSearchTerm] = useState("");
  const [categories, setCategories] = useState([]);
  const { notify } = useNotification();

  useEffect(() => {
    if (searchTerm) {
      fetchCategoriesByKeyword(searchTerm).then(setCategories);
    } else {
      setCategories([]); // Hide categories dropdown when input is empty
    }
  }, [searchTerm]);

  const handleChange = ({ target: { name, value } }) => {
    setQuote((prevQuote) => ({ ...prevQuote, [name]: value }));
  };

  const handleCategorySelect = (category, event) => {
    event.stopPropagation();
    setQuote((prevQuote) => ({ ...prevQuote, category }));
    setSearchTerm(""); // Clear the input after a category is selected
  };

  const handleSearchTermChange = ({ target: { value } }) =>
    setSearchTerm(value);

  const handleSubmit = async (event) => {
    event.preventDefault();
    try {
      if (existingQuote) {
        console.log(existingQuote);
        await updatePersonalQuote(quote);
        notify("Quote updated successfully", "success");
      } else {
        await addPersonalQuote(quote);
        notify("Quote created successfully", "success");
      }
      handleModalClose(); // Close the modal after submit
    } catch (error) {
      notify("An error occurred while processing your request", "error");
    }
  };

  return (
    <Container>
      <Form onSubmit={(event) => event.preventDefault()}>
        <Form.Group controlId="quoteDescription">
          <Form.Label>Description</Form.Label>
          <Form.Control
            type="text"
            placeholder="Enter quote description"
            name="description"
            value={quote.description}
            onChange={handleChange}
          />
        </Form.Group>

        <Form.Group controlId="quoteCategory">
          <CategorySearch
            searchTerm={searchTerm}
            categories={categories}
            handleSearchTermChange={handleSearchTermChange}
            handleCategorySelect={handleCategorySelect}
          />
          {quote.category.name && (
            <p>Selected Category: {quote.category.name}</p>
          )}
        </Form.Group>

        <Button variant="primary" onClick={handleSubmit}>
          Submit
        </Button>
      </Form>
    </Container>
  );
};

export default QuoteForm;
