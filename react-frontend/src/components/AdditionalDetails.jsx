import { useEffect, useState } from "react";

const yankeeSwapCountdownHoursVariants = [24, 48, 72];

const AdditionalDetails = ({ allowMutualDrawing, updateData }) => {
  const [yankeeSwapCountdownHours, setYankeeSwapCountdownHours] = useState();

  useEffect(() => {
    if (yankeeSwapCountdownHours) {
      updateData({ yankeeSwapCountdownHours });
    }
  }, [yankeeSwapCountdownHours]);

  const renderedCountdownHoursVariants = yankeeSwapCountdownHoursVariants.map(
    (hours) => {
      return (
        <p onClick={() => setYankeeSwapCountdownHours(hours)}>
          {hours}
        </p>
      );
    },
  );

  return (
    <div>
      <p>allow mutual drawing</p>
      <input
        type="checkbox"
        checked={allowMutualDrawing}
        onChange={(e) => updateData({ allowMutualDrawing: e.target.checked })}
      />
      {renderedCountdownHoursVariants}
    </div>
  );
};

export default AdditionalDetails;
