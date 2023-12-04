import React, { useContext, useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom'
import { Link, useLocation } from 'react-router-dom'
import { BlogsContext } from '../Contexts/BlogsContext';

export const ShowBlog = ({ props }) => {
  const navigate = useNavigate();
  const goToIndex = () => {
    navigate("/");
  }
  const blog = useLocation().state.blogItem;
  const { blogs, setBlogs } = useContext(BlogsContext);
  const [likes, setLikes] = useState(blog.likes);
  let sno = blog.sno;

  const addLike = () => {
    setLikes(likes + 1);
    console.log(blogs);

  }

  useEffect(() => {
    blogs.map((myBlog => {
      if (myBlog.sno === sno) {
        blogs[sno].likes = likes;
        console.log(blogs);
        console.log("likes in app.js is  " + myBlog);
        setBlogs(blogs)
      }
    }))
  }, [likes])

  const onDelete = (blogItem) => {
    setBlogs(blogs.filter((e) => {
      return e !== blogItem;
    }));

    localStorage.setItem("blogs", JSON.stringify(blogs));
  }

  return (
    <>

      <div className='container mt-5'>
        <div className='row' >
          <div className='col-md-8 offset-md-2'>
            <div className="jumbotron">
              <h1 className="display-4">{blog.title}</h1>
              <p className="lead">{blog.categories}</p>
              <hr className="my-4" />
              <p>{blog.content}.</p>
              <p className="lead d-flex justify-content-center">{likes} Likes</p>

              <div className="d-flex justify-content-between">
                <button className='btn btn-danger sm-3 ' onClick={() => { onDelete(blog); goToIndex() }}>
                  Delete Blog
                </button>

                <button className='btn btn-primary sm-3' onClick={addLike}>
                  Like
                </button>

                <button className='btn btn-success sm-3'>
                  <Link to={'/edit'} state={{ blog: blog }} key={blog.sno} style={{ textDecoration: 'none' }} className='text-white' >Edit blog </Link>
                </button>


              </div>
            </div>

          </div>

        </div>
      </div>

    </>

  )
}
