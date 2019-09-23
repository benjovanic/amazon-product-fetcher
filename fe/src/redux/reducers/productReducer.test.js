import productReducer from "./productReducer";
import * as actions from "../actions/productActions";

it("should add product when passed FETCH_PRODUCT", () => {
  // arrange
  const initialState = [
    {
      id: "A"
    },
    {
      id: "B"
    }
  ];

  const newProduct = {
    id: "C"
  };

  const action = actions.fetchProductSuccess(newProduct);

  // act
  const newState = productReducer(initialState, action);

  // assert
  expect(newState.length).toEqual(3);
  expect(newState[0].id).toEqual("A");
  expect(newState[1].id).toEqual("B");
  expect(newState[2].id).toEqual("C");
});
