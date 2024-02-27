import { useState } from "react";
import useMultiStepForm from "../hooks/useMultiStepForm";
import GroupDetails from "./GroupDetails";
import MembersDetails from "./MembersDetails";
import OwnerDetails from "./OwnerDetails";
import MembersConstraints from "./MembersConstraints";
import AdditionalDetails from "./AdditionalDetails";
import publicClientRequest from "../api/ClientRequest";

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

  const handleNext = async () => {
    if (isLastStep) {
      const response = await publicClientRequest.post("/group", data);
      console.log(response);
    } else {
      next();
    }
  };

  return (
    <div className="flex min-h-96 w-4/5 flex-col justify-between rounded-md shadow-md bg-gray-200 p-4 md:w-1/2 lg:w-1/3 xl:w-1/4">
      <div className="flex-1">{step}</div>
      <div className="flex justify-between text-md font-bold">
        {currentStepIndex + 1} / {steps.length}
        <div className="flex gap-4">
          {!isFirstStep && (
            <button type="button" onClick={back}>
              Back
            </button>
          )}
          <button onClick={handleNext}>{isLastStep ? "Submit" : "Next"}</button>
        </div>
      </div>
    </div>
  );
};

export default GroupForm;
