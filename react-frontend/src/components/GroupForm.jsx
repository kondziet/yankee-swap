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
  const [groupId, setGroupId] = useState();
  const [error, setError] = useState("");
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
      try {
        const response = await publicClientRequest.post("/group", data);
        setGroupId(response.data);
      } catch (error) {
        setError(error.response.data.details.join('\n'));
      }
    } else {
      next();
    }
  };

  return (
    <div className="flex min-h-96 w-4/5 flex-col justify-between rounded-md bg-gray-200 p-4 shadow-md md:w-1/2 lg:w-1/3 xl:w-1/4">
      {!groupId ? (
        <div className="flex flex-1 flex-col">
          <div className="flex-1">{step}</div>
          <div className="text-md flex justify-between font-bold">
            {currentStepIndex + 1} / {steps.length}
            <div className="flex gap-4">
              {!isFirstStep && (
                <button type="button" onClick={back}>
                  Back
                </button>
              )}
              <button onClick={handleNext}>
                {isLastStep ? "Submit" : "Next"}
              </button>
            </div>
          </div>
        </div>
      ) : (
        <div className="flex flex-1 flex-col items-center justify-center gap-4">
          <div className="mb-4 text-4xl font-bold text-teal-600">
            🎉 Group Created 🎉
          </div>
          <div className="rounded-md bg-green-100 p-4 text-xl font-bold text-green-800 shadow-md">
            Group ID: {groupId}
          </div>
        </div>
      )}
      {error && (
        <div className="rounded-md bg-red-100 p-4 text-xl font-bold text-red-800 shadow-md">
          {error}
        </div>
      )}
    </div>
  );
};

export default GroupForm;
