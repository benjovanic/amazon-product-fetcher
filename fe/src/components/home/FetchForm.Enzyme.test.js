import React from "react";
import FetchForm from "./FetchForm";
import { shallow } from "enzyme";

function renderFetchForm(args) {
  const defaultProps = {
    asin: "",
    saving: false,
    errors: {},
    onSave: () => {},
    onChange: () => {}
  };

  const props = { ...defaultProps, ...args };
  return shallow(<FetchForm {...props} />);
}

it("renders form and header", () => {
  const wrapper = renderFetchForm();
  expect(wrapper.find("form").length).toBe(1);
  expect(wrapper.find("h2").text()).toEqual("Find Product by ASIN");
});

it('labels save buttons as "Fetch" when not saving', () => {
  const wrapper = renderFetchForm();
  expect(wrapper.find("button").text()).toBe("Fetch");
});

it('labels save button as "Fetching..." when saving', () => {
  const wrapper = renderFetchForm({ saving: true });
  expect(wrapper.find("button").text()).toBe("Fetching...");
});
