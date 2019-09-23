import { combineReducers } from "redux";
import products from "./productReducer";
import apiCallsInProgress from "./apiStatusReducer";

const rootReducer = combineReducers({
  products,
  apiCallsInProgress
});

export default rootReducer;
