import React, { Component } from 'react'
import EmployeeService from '../service/EmployeeService';

export default class EmployeeComponent extends Component {
    constructor(props) {
        super(props);
        
        this.state = {
            // initializing the objects.
            employee: {},
            department: {},
            organization: {}
        }
    }

    // REST API call 
    componentDidMount() {
        // call the employee service here
        EmployeeService.getEmployee().then((response => {
            // get the value from the response object
            // set the values of the objects:
            // we stored the data into the objects.
            //this data will contains the response of the API.
            //axios objects.
            this.setState({employee: response.data.employee}),
            this.setState({department: response.data.department}),
            this.setState({organization: response.data.organization})
            // log in the console 
            console.log(this.state.employee)
            console.log(this.state.organization)
            console.log(this.state.department)
        }))
    }
   
  render() {
    return (
        // JSX code= js extension to add html code in your js files.
      <div> <br></br>
        {/* add bootstrap */}
        {/* border -card */}
        <div className='card col-md-6 offset-md-3'>
            <h3 className='text-center card-header'>View Employee Details</h3>
            <div className='card-body'>
                 <div className='row'>
                    {/* access the employee object */}
                    {/* stored employee object in a state */}
                    <p><strong>Employee First Name</strong> {this.state.employee.firstName}</p>
                 </div>
                 <div className='row'>
                    {/* access the employee object */}
                    {/* stored employee object in a state */}
                    <p><strong>Employee Last name</strong> {this.state.employee.lastName}</p>
                 </div>

                 <div className='row'>
                    {/* access the employee object */}
                    {/* stored employee object in a state */}
                    <p><strong>Employee Email</strong> {this.state.employee.email}</p>
                 </div>
            </div>
            <h3 className='text-center card-header'>View Department Details</h3>
            <div className='card-body'>
            <div className='row'>
                <p><strong>Department Name: </strong>{this.state.department.departmentName}</p>
            </div>
            <div className='row'>
                <p><strong>Department Description: </strong>{this.state.department.departmentDescription}</p>
            </div>
            <div className='row'>
                <p><strong>Department Code: </strong>{this.state.department.departmentCode}</p>
            </div>
            </div>

            <h3 className='text-center card-header'>View Organization Details</h3>
            <div className='card-body'>
            <div className='row'>
                <p><strong>Organization Name: </strong>{this.state.organization.organizationName}</p>
            </div>
            <div className='row'>
                <p><strong>Organization Description: </strong>{this.state.organization.organizationDescription}</p>
            </div>
            <div className='row'>
                <p><strong>Organization Code: </strong>{this.state.organization.organizationCode}</p>
            </div>
            </div>


        </div>
      </div>
    )
  }
}
