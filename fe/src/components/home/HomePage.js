import React, { useEffect, useState } from "react";
import { connect } from "react-redux";
import * as productActions from "../../redux/actions/productActions";
import PropTypes from "prop-types";
import { bindActionCreators } from "redux";
import ProductList from "./ProductList";
import Spinner from "../common/Spinner";
import { toast } from "react-toastify";
import FetchForm from "./FetchForm";

export function HomePage({ products, actions, loading }) {
  const [asin, setAsin] = useState("");
  const [errors, setErrors] = useState();
  const [saving, setSaving] = useState(false);

  useEffect(() => {
    if (products.length === 0) {
      actions.loadProducts().catch(error => {
        alert("Loading products failed: " + error);
      });
    }
  }, []);

  // If the ASIN is empty then show an error
  function formIsValid() {
    const errors = {};

    if (!asin) errors.asin = "ASIN is required.";

    setErrors(errors);
    // Form is valid if the errors object still has no properties
    return Object.keys(errors).length === 0;
  }

  function handleFetch(event) {
    event.preventDefault();
    if (!formIsValid()) return;
    setSaving(true);
    actions
      .fetchProduct(asin)
      .then(() => {
        setSaving(false);
        setAsin("");
        toast.success("Product fetched.");
      })
      .catch(error => {
        setSaving(false);
        setErrors({ onSave: error.message });
      });
  }

  function handleChange(event) {
    setAsin(event.target.value);
  }

  return (
    <>
      <FetchForm
        asin={asin}
        errors={errors}
        saving={saving}
        onSave={handleFetch}
        onChange={handleChange}
      />
      <br />
      {loading ? <Spinner /> : <ProductList products={products} />}
    </>
  );
}

HomePage.propTypes = {
  products: PropTypes.array.isRequired,
  actions: PropTypes.object.isRequired,
  loading: PropTypes.bool.isRequired
};

function mapStateToProps(state) {
  return {
    products: state.products,
    loading: state.apiCallsInProgress > 0
  };
}

function mapDispatchToProps(dispatch) {
  return {
    actions: {
      loadProducts: bindActionCreators(productActions.loadProducts, dispatch),
      fetchProduct: bindActionCreators(productActions.fetchProduct, dispatch)
    }
  };
}

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(HomePage);
