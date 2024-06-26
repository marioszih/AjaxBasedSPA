import React, { useEffect, useState } from 'react'
import axios from 'axios'
import { useNavigate, useParams } from 'react-router-dom';
import { Link } from 'react-router-dom';

export default function Edit(){

    const navigate = useNavigate();
    const {id} = useParams();

    const [user, setUser] = useState({
        name: '',
        surname: '',
        email: ''
    });

    const {name, surname, email} = user;

    useEffect(() => 
    {    
        loadUser();
    }, []);

    

    const onInputChange = async(e) =>
    {
        setUser({...user, [e.target.name]: e.target.value})
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            await axios.put(`http://localhost:8090/edit/${id}`, user);
        } catch (error) {
            console.error('Error updating user:', error);
        }
        navigate("/");
    };

    const loadUser = async () => {
        try {
            const result = await axios.get(`http://localhost:8090/edit/${id}`);
            setUser(result.data);
        } catch (error) {
            console.error('There was an error loading the user data!', error);
        }
    };

    return (
        <form onSubmit={handleSubmit}>
            <div className="mb-3 mt-4" align = "center">
                <label>Name:</label>
                <input type="text" name="name" class="form-control" style={{width: "370px", borderColor: 'black'}} 
                value={name} onChange={onInputChange} required />
            </div>
            <div className="mb-3" align = "center">
                <label>Surname:</label>
                <input type="text" name="surname" class="form-control" style={{width: "370px", borderColor: 'black'}} 
                value={surname} onChange={onInputChange} required />
            </div>
            <div className="mb-3" align = "center">
                <label>Email:</label>
                <input type="text" name="email" class="form-control" style={{width: "370px", borderColor: 'black'}} 
                value={email} onChange={onInputChange} required />
            </div>
            <button className = "btn btn-outline-primary" type="submit">Submit</button>
            <Link className="btn btn-outline-danger" to="/">Cancel</Link>
        </form>
    );
}