import axios from "axios";

const publicClientRequest = axios.create({
  baseURL: "http://localhost:8080/api",
});

export default publicClientRequest;