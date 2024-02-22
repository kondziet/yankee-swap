import { useState } from "react";
import useMultiStepForm from "../hooks/useMultiStepForm";
import GroupDetails from "./GroupDetails";
import MembersDetails from "./MembersDetails";
import OwnerDetails from "./OwnerDetails";
import MembersConstraints from "./MembersConstraints";

const INITIAL_DATA = {
  owner: {
    name: "",
  },
  group: {
    name: "",
    description: "",
  },
  members: [{ name: "" }, { name: "" }, { name: "" }],
  constraints: [],
};

const GroupForm = () => {
  const [data, setData] = useState(INITIAL_DATA);
  const updateData = (fields) => {
    setData((prev) => ({ ...prev, ...fields }));
  };

  const { steps, currentStepIndex, step, back, next, isFirstStep, isLastStep } =
    useMultiStepForm([
      <OwnerDetails {...data} updateData={updateData} />,
      <GroupDetails {...data} updateData={updateData} />,
      <MembersDetails {...data} updateData={updateData} />,
      <MembersConstraints {...data} updateData={updateData} />,
    ]);

  const handleNext = () => {
    next();
  };

  return (
    <div>
      {step}
      {currentStepIndex + 1} / {steps.length}
      {!isFirstStep && (
        <button type="button" onClick={back}>
          Back
        </button>
      )}
      <button onClick={handleNext}>{isLastStep ? "Submit" : "Next"}</button>
    </div>
  );
};

export default GroupForm;
