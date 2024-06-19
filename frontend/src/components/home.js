import React, { useEffect, useState } from 'react'
import axios from 'axios'
import { Link } from 'react-router-dom';

export default function Home(){

    const [users, setUsers] = useState([])

    useEffect(() => 
    {
        loadUsers();
    }, []);

    const loadUsers = async() =>
    {
        const result = axios.get(`http://localhost:8090/`);
        setUsers((await result).data)
    }

    const deleteUser = async(id) =>
    {
        try{
            await axios.delete(`http://localhost:8090/user/${id}`);
            loadUsers();
        } catch {
            console.log("Sorry brother");
        }
    }

    return (
        <div>
            <table class="table">
                <thead>
                    <tr>
                    <th scope="col">#</th>
                    <th scope="col">Name</th>
                    <th scope="col">Surname</th>
                    <th scope="col">Email</th>
                    <th scope="col">Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        users.map((user,index) => (
                            <tr>
                            <th scope="row" key={index}>{index+1}</th>
                            <td>{user.name}</td>
                            <td>{user.surname}</td>
                            <td>{user.email}</td>
                            <td>
                                <Link className="btn btn-outline-primary mx-2" to={`/edit/${user.id}`}>Edit</Link>
                                <button className="btn btn-danger mx-2" onClick={() => deleteUser(user.id)}>Delete</button>
                            </td>
                            </tr>
                        ))
                    }
                </tbody>
            </table>
        </div>
    );
}