import * as types from "../actions/actionTypes";
import initialState from "./initialState";

export default function productReducer(state = initialState.products, action) {
  switch (action.type) {
    case types.FETCH_PRODUCT:
      return [
        // state can hold products with the same ASIN so remove the old one, if exists,
        // before adding the new product
        ...state.filter(product => product.id !== action.product.id),
        { ...action.product }
      ];
    case types.LOAD_PRODUCTS_SUCCESS:
      return action.products;
    default:
      return state;
  }
}
