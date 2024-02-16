import { useEffect, useState } from "react";
import publicClientRequest from "../api/ClientRequest";
import { useParams } from "react-router-dom";

const DrawResult = () => {
  const { groupId, userName } = useParams();
  const [result, setResult] = useState("");
  useEffect(() => {
    publicClientRequest
      .get(`/group/${groupId}/${userName}`)
      .then((response) => {
        setResult(response.data.name);
      });
  }, []);
  return <div>{result}</div>;
};

export default DrawResult;
