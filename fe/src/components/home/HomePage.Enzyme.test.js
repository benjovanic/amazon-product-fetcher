import React from "react";
import { mount } from "enzyme";
import { products } from "../../../tools/mockData";
import { HomePage } from "./HomePage";

function render(args) {
  const defaultProps = {
    products,
    actions: {},
    loading: false
  };

  const props = { ...defaultProps, ...args };

  return mount(<HomePage {...props} />);
}

it("sets error when attempting to fetch with an empty asin field", () => {
  const wrapper = render();
  wrapper.find("form").simulate("submit");
  const error = wrapper.find(".alert").first();
  expect(error.text()).toBe("ASIN is required.");
});
