import { useState } from "react";
import useMultiStepForm from "../hooks/useMultiStepForm";
import GroupDetails from "./GroupDetails";
import MembersDetails from "./MemberDetails";
import OwnerDetails from "./OwnerDetails";

const INITIAL_DATA = {
  owner: {
    name: "",
  },
  group: {
    name: "",
    description: "",
  },
  members: [],
};

const GroupForm = () => {
  const [data, setData] = useState(INITIAL_DATA);
  const updateData = (fields) => {
    setData((prev) => ({ ...prev, ...fields }));
  };

  const { steps, currentStepIndex, step, back, next, isFirstStep, isLastStep } =
    useMultiStepForm([<OwnerDetails />, <GroupDetails />, <MembersDetails />]);

  const handleSubmit = (e) => {
    e.preventDefault();
    next();
  };

  return (
    <div>
      <form onSubmit={handleSubmit}>
        {step}
        {currentStepIndex + 1} / {steps.length}
        {!isFirstStep && (
          <button type="button" onClick={back}>
            Back
          </button>
        )}
        <button type="submit">{isLastStep ? "Submit" : "Next"}</button>
      </form>
    </div>
  );
};

export default GroupForm;
