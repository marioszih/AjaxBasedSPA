import React, { useState } from 'react';
import axios from 'axios';
import { Link, useNavigate } from 'react-router-dom';

export default function RegistrationForm(){

    const navigate = useNavigate();
    const [formData, setFormData] = useState({
        name: '',
        surname: '',
        email: ''
    });

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({
            ...formData,
            [name]: value
        });
    }

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post('http://localhost:8090/register', formData);
            console.log('User created successfully:', response.data);
        } catch (error) {
            console.error('There was an error creating the user!', error);
        }
        navigate("/");
    };

    return (
        <form onSubmit={handleSubmit}>
            <div class="mb-3 mt-4" align = "center">
                <label>Name:</label>
                <input type="text" name="name" class="form-control" style={{width: "370px", borderColor: 'black'}} 
                value={formData.name} onChange={handleChange} required />
            </div>
            <div class="mb-3" align = "center">
                <label>Surname:</label>
                <input type="text" name="surname" class="form-control" style={{width: "370px", borderColor: 'black'}} 
                value={formData.surname} onChange={handleChange} required />
            </div>
            <div class="mb-3" align = "center">
                <label>Email:</label>
                <input type="email" name="email" class="form-control" style={{width: "370px", borderColor: 'black'}} 
                value={formData.email} onChange={handleChange} required />
            </div>
            <button className = "btn btn-outline-primary" type="submit">Submit</button>
            <Link className="btn btn-outline-danger" to="/">Cancel</Link>
        </form>
    );
}