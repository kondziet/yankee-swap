import GroupForm from "../components/GroupForm";
import background from "../img/home-background-2.jpg";

const CreateGroup = () => {
  return (
    <div
      className="flex h-screen items-center justify-center"
      style={{
        backgroundImage: `url(${background})`,
        backgroundSize: "cover",
        backgroundPosition: "center",
      }}
    >
      <GroupForm />
    </div>
  );
};

export default CreateGroup;
