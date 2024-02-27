const GroupDetails = ({ name, description, updateData }) => {
  return (
    <div className="rounded-md bg-gray-100 p-4 shadow-md">
      <h2 className="mb-4 text-xl font-bold">Group Details</h2>
      <div className="mb-4">
        <label className="mr-2 text-gray-600">Group Name:</label>
        <input
          type="text"
          value={name}
          onChange={(e) => updateData({ name: e.target.value })}
          className="rounded-md border p-2 focus:border-blue-300 focus:outline-none focus:ring"
        />
      </div>
      <div>
        <label className="mr-2 text-gray-600">Description:</label>
        <textarea
          value={description}
          onChange={(e) => updateData({ description: e.target.value })}
          className="h-24 w-full resize-none rounded-md border p-2 focus:border-blue-300 focus:outline-none focus:ring"
        />
      </div>
    </div>
  );
};

export default GroupDetails;
