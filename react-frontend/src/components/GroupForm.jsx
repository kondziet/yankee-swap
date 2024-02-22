import { useState } from "react";
import useMultiStepForm from "../hooks/useMultiStepForm";
import GroupDetails from "./GroupDetails";
import MembersDetails from "./MembersDetails";
import OwnerDetails from "./OwnerDetails";
import MembersConstraints from "./MembersConstraints";
import AdditionalDetails from "./AdditionalDetails";

const INITIAL_DATA = {
  name: "",
  description: "",
  owner: {
    name: "",
  },
  members: [{ name: "" }, { name: "" }, { name: "" }],
  constraints: [],
  allowMutualDrawing: false,
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
      <AdditionalDetails {...data} updateData={updateData} />,
    ]);

  console.log(data);

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
