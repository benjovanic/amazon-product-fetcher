import React from "react";
import PropTypes from "prop-types";
import TextInput from "../common/TextInput";

const FetchForm = ({ asin, onSave, onChange, saving = false, errors = {} }) => {
  return (
    <form onSubmit={onSave}>
      <h2>Find Product by ASIN</h2>
      {errors.onSave && (
        <div className="alert alert-danger" role="alert">
          {errors.onSave}
        </div>
      )}
      <TextInput
        name="asin"
        label="ASIN"
        value={asin}
        onChange={onChange}
        error={errors.asin}
      />

      <button type="submit" disabled={saving} className="btn btn-primary">
        {saving ? "Fetching..." : "Fetch"}
      </button>
    </form>
  );
};

FetchForm.propTypes = {
  asin: PropTypes.string.isRequired,
  errors: PropTypes.object,
  onSave: PropTypes.func.isRequired,
  onChange: PropTypes.func.isRequired,
  saving: PropTypes.bool
};

export default FetchForm;
