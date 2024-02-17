import useMultiStepForm from "../hooks/useMultiStepForm";

const GroupForm = () => {
  const { steps } = useMultiStepForm([]);
  return <div>Group form</div>;
};

export default GroupForm;
