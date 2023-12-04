
import './App.css';
import Header from './MyComponents/Header';
import { Blogs } from './MyComponents/Blogs';
import { ShowBlog } from './MyComponents/ShowBlog';
import { AddBlog } from './MyComponents/AddBlog';
import { EditBlog } from './MyComponents/EditBlog';
import React, { useState, useEffect } from 'react';

import {
  BrowserRouter as Router,
  Routes,
  Route,

} from "react-router-dom";
import { BlogsContext } from './Contexts/BlogsContext';


function App() {

  let initBlog;
  if(localStorage.getItem("blogs")===null){
    initBlog=[];
  }else{
    initBlog = JSON.parse(localStorage.getItem("blogs"));
  }

  const [blogs, setBlogs] = useState(initBlog);
  
  useEffect(() => {
    localStorage.setItem("blogs",JSON.stringify(blogs));
  }, [blogs])
  return (
    <>
      
      <BlogsContext.Provider value={{blogs,setBlogs}}>
      <Router>
      <Header title="My Blogs" />
        <Routes>
          <Route path='/showBlog' element={<ShowBlog />}/>
          <Route path='/addblog' element={<AddBlog />} />
          <Route path='/edit' element={<EditBlog />} />
          <Route index path='/' element={<Blogs />} />
        </Routes>
      </Router>
      </BlogsContext.Provider>


    </>
  );
}

export default App;
