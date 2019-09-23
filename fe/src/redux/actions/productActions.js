import * as types from "./actionTypes";
import * as productApi from "../../api/productApi";
import { beginApiCall, apiCallError } from "./apiStatusActions";

export function loadProductsSuccess(products) {
  return { type: types.LOAD_PRODUCTS_SUCCESS, products };
}

export function fetchProductSuccess(product) {
  return { type: types.FETCH_PRODUCT, product };
}

export function loadProducts() {
  return function(dispatch) {
    dispatch(beginApiCall());
    return productApi
      .getProducts()
      .then(products => {
        dispatch(loadProductsSuccess(products));
      })
      .catch(error => {
        dispatch(apiCallError(error));
        throw error;
      });
  };
}

export function fetchProduct(asin) {
  return function(dispatch) {
    return productApi
      .fetchProduct(asin)
      .then(product => {
        dispatch(fetchProductSuccess(product));
      })
      .catch(error => {
        dispatch(apiCallError(error));
        throw error;
      });
  };
}
