import { useEffect, useState } from "react";

const yankeeSwapCountdownHoursVariants = [24, 48, 72];

const AdditionalDetails = ({
  allowMutualDrawing,
  yankeeSwapCountdownHours,
  updateData,
}) => {
  const [selectedCountdownHours, setSelectedCountdownHours] = useState(
    yankeeSwapCountdownHours,
  );

  useEffect(() => {
    if (selectedCountdownHours !== yankeeSwapCountdownHours) {
      updateData({ yankeeSwapCountdownHours: selectedCountdownHours });
    }
  }, [selectedCountdownHours, yankeeSwapCountdownHours, updateData]);

  const renderedCountdownHoursVariants = yankeeSwapCountdownHoursVariants.map(
    (hours) => (
      <div key={hours} className="mb-2 flex items-center">
        <input
          type="checkbox"
          id={`countdown-${hours}`}
          checked={selectedCountdownHours === hours}
          onChange={() => setSelectedCountdownHours(hours)}
          className="mr-2"
        />
        <label htmlFor={`countdown-${hours}`} className="cursor-pointer">
          {hours} Hours
        </label>
      </div>
    ),
  );

  return (
    <div className="rounded-md bg-gray-100 p-4 shadow-md">
      <h2 className="mb-2 text-xl font-bold">Additional Details</h2>
      <div className="mb-2 flex items-center">
        <label className="mr-2">Allow Mutual Drawing:</label>
        <input
          type="checkbox"
          checked={allowMutualDrawing}
          onChange={(e) => updateData({ allowMutualDrawing: e.target.checked })}
        />
      </div>
      <div>
        <p className="mb-2 font-bold">Yankee Swap Countdown Hours:</p>
        {renderedCountdownHoursVariants}
      </div>
    </div>
  );
};

export default AdditionalDetails;
