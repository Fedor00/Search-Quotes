import React from "react";
import { Form, ListGroup } from "react-bootstrap";

const CategorySearch = ({
  searchTerm,
  categories,
  handleSearchTermChange,
  handleCategorySelect,
}) => {
  return (
    <Form.Group controlId="quoteCategory">
      <Form.Label>Category</Form.Label>
      <Form.Control
        type="text"
        placeholder="Search category"
        name="category"
        value={searchTerm}
        onChange={handleSearchTermChange}
      />
      {categories && categories.length > 0 && (
        <ListGroup>
          {categories.map((category, index) => (
            <ListGroup.Item
              key={index}
              action
              onClick={(event) => handleCategorySelect(category, event)}
            >
              {category.name}
            </ListGroup.Item>
          ))}
        </ListGroup>
      )}
    </Form.Group>
  );
};

export default CategorySearch;
