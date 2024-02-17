import { useState } from "react";

export default ({ steps }) => {
  const [currentStepIndex, setCurrentStepIndex] = useState(0);

  const next = () => {
    setCurrentStepIndex((prev) => {
      if (prev >= steps.length - 1) {
        return prev;
      }
      return prev + 1;
    });
  };

  const back = () => {
    setCurrentStepIndex((prev) => {
      if (prev <= 0) {
        return prev;
      }
      return prev - 1;
    });
  };

  const goTo = (stepIndex) => {
    setCurrentStepIndex(stepIndex);
  };

  return { currentStepIndex, step: steps[currentStepIndex], next, back, goTo, steps };
};
