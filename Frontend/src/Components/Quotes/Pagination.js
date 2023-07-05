import React from "react";
import { Pagination } from "react-bootstrap";

const MAX_ITEMS = 5;
const MAX_LEFT = (MAX_ITEMS - 1) / 2;

const QuotePagination = ({ totalPages, activePage, setActivePage }) => {
  const calculateFirstItemIndex = () => {
    if (activePage <= MAX_LEFT) {
      return 1;
    } else if (totalPages - activePage < MAX_LEFT) {
      return totalPages - MAX_ITEMS + 1;
    } else {
      return activePage - MAX_LEFT;
    }
  };

  const renderPaginationItems = () => {
    const items = [];
    const firstItemIndex = calculateFirstItemIndex();

    for (
      let number = firstItemIndex;
      number < firstItemIndex + MAX_ITEMS;
      number++
    ) {
      items.push(
        <Pagination.Item
          key={number}
          active={number === activePage}
          onClick={() => setActivePage(number)}
        >
          {number}
        </Pagination.Item>
      );
    }

    return items;
  };

  if (totalPages > 1) {
    return (
      <Pagination className="justify-content-center mt-3">
        <Pagination.First
          onClick={() => setActivePage(1)}
          disabled={activePage === 1}
        />
        <Pagination.Prev
          onClick={() => setActivePage((prevPage) => Math.max(1, prevPage - 1))}
          disabled={activePage === 1}
        />
        {activePage > MAX_LEFT && <Pagination.Ellipsis />}
        {renderPaginationItems()}
        {totalPages - activePage > MAX_LEFT && <Pagination.Ellipsis />}
        <Pagination.Next
          onClick={() =>
            setActivePage((prevPage) => Math.min(totalPages, prevPage + 1))
          }
          disabled={activePage === totalPages}
        />
        <Pagination.Last
          onClick={() => setActivePage(totalPages)}
          disabled={activePage === totalPages}
        />
      </Pagination>
    );
  }

  return null;
};

export default QuotePagination;
