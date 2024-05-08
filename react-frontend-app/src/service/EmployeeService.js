// making a REST API call from React App to API Gateway.
// first, import the Axios third party library

import axios from 'axios';

// define the constant which will hold the employee service base URL, will call the url
const EMPLOYEE_SERVICE_BASE_URL="http://localhost:9191/employees";
// create one more constant name as employee_id, will call the employee id
const EMPLOYEE_ID=2;


class EmployeeService {
    // this method will make a rest API call to the API Gateway
    // the API Gateway is running on port 9191
    // it means we are making rest API call to API Gatewy
    getEmployee() {
        // get employee by rest api
        // we pass the url 
        // will create a complete URL.
        return axios.get(EMPLOYEE_SERVICE_BASE_URL + "/" + EMPLOYEE_ID);
    }
}

// export the instance of the employee service object so that other react components can be able to use this
// employee service object. 
export default new EmployeeService;