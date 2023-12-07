import StudentTable from "./StudentTable";
import { useEffect, useState } from "react";

function StudentForm() {
  let [firstName, setFirstName] = useState("");
  let [lastName, setLastName] = useState("");
  let [lovesClass, setLovesClass] = useState(false);

  useEffect(() => {
    console.log(lovesClass);
  }, [lovesClass]);

  function handleSubmit(e) {
    e.preventDefault();

    const data = {
      firstName,
      lastName,
      lovesClass,
    };

    fetch("http://localhost:8080/api/v2/students", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(data),
    }).then((response) => console.log(response));

    setFirstName("");
    setLastName("");
    setLovesClass(false);
  }

  return (
    <div className="container">
      <div className="row justify-content-center">
        <div className="col-md-6">
          <form onSubmit={handleSubmit}>
            <div className="form-group mb-3">
              <label htmlFor="firstName">First Name</label>
              <input
                type="text"
                className="form-control"
                id="firstName"
                placeholder="Enter first name"
                value={firstName}
                onChange={(e) => setFirstName(e.target.value)}
              />
            </div>
            <div className="form-group mb-3">
              <label htmlFor="lastName">Last Name</label>
              <input
                type="text"
                className="form-control"
                id="lastName"
                placeholder="Enter last name"
                value={lastName}
                onChange={(e) => setLastName(e.target.value)}
              />
            </div>
            <div className="form-group form-check">
              <input
                type="checkbox"
                className="form-check-input"
                id="lovesClass"
                value={lovesClass}
                checked={lovesClass}
                onChange={(e) => setLovesClass(e.target.checked)}
              />
              <label className="form-check-label mb-3" htmlFor="loveClass">
                Loves Class
              </label>
            </div>
            <button type="submit" className="btn btn-success">
              Submit
            </button>
          </form>
        </div>
      </div>
      <StudentTable />
    </div>
  );
}

export default StudentForm;
