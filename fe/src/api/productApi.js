import { handleResponse, handleError } from "./apiUtils";
const baseUrl = process.env.API_URL + "/products/";

export function getProducts() {
  return fetch(baseUrl)
    .then(handleResponse)
    .catch(handleError);
}

export function fetchProduct(asin) {
  return fetch(`${baseUrl}fetch-product?asin=${asin}`)
    .then(handleResponse)
    .catch(handleError);
}
