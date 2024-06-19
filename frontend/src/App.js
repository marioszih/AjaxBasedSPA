import './App.css';
import Navbar from './components/menu';
import Home from './components/home'
import Edit from './components/edit'
import "../node_modules/bootstrap/dist/css/bootstrap.min.css"
import { BrowserRouter as Router, Route, Routes  } from 'react-router-dom';
import RegistrationForm from './components/registration';

function App() {
  return (
    <Router>
      <div className="App">
        <Navbar></Navbar>
        <Routes>
          <Route exact path="/" element={<Home />} />
          <Route path="/register" element={<RegistrationForm />} />
          <Route path="/edit/:id" element={<Edit />} /> 
        </Routes>
      </div>
    </Router>
    
  );
}

export default App;