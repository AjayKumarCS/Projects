import React from 'react'
import { Link } from 'react-router-dom'
import { useNavigate } from 'react-router-dom';

export default function Header(props) {


  const navigate = useNavigate();

  const goToAddBlog = () => {
    navigate("/addblog");
  }


  return (
    <header>
      <nav className="navbar navbar-expand-lg text-bg-dark">
        <div className="container d-flex justify-content-start">
          <Link to={'/'} className="navbar-brand text-light" >Home</Link>
          
          <div className="container d-flex justify-content-end" >
                <button onClick={() => goToAddBlog()} className="btn btn-success px-3">Add Blog</button>
            </div>
          </div>
      </nav>
    </header>
  )
}
