import React from "react";
import PropTypes from "prop-types";

const ProductList = ({ products }) => (
  <>
    <h2>Products</h2>
    <table className="table">
      <thead>
        <tr>
          <th>ASIN</th>
          <th>Name</th>
          <th>Category</th>
          <th>Dimensions</th>
          <th>Rank</th>
        </tr>
      </thead>
      <tbody>
        {products.map(product => {
          return (
            <tr key={product.id}>
              <td>
                <a href={`http://amazon.com/dp/${product.id}`}>{product.id}</a>
              </td>
              <td>{product.name}</td>
              <td>{product.category}</td>
              <td>{product.dimensions}</td>
              <td>{product.rank}</td>
            </tr>
          );
        })}
      </tbody>
    </table>
  </>
);

ProductList.propTypes = {
  products: PropTypes.array.isRequired
};

export default ProductList;
