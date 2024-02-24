import Home from './pages/Home';
import About from './pages/About';
import Contact from './pages/Contact';
import './App.css';
import { BrowserRouter, Routes, Route, Router } from 'react-router-dom';
import Topbar from './header/Topbar';

function App() {
  return (
      <BrowserRouter>
          <Topbar user={true}  />
          <div className="App">
            <Routes>
              <Route path="/" element={<Home />} />
              <Route path="/about" element={<About />} />
              <Route path="/contact" element={<Contact />} />
            </Routes>    
          </div>
      </BrowserRouter>
  );
}

export default App;
