import React, { useContext } from 'react'
import { useState } from 'react'
import { useLocation, useNavigate } from 'react-router-dom'
import { BlogsContext } from '../Contexts/BlogsContext';

export const EditBlog = (props) => {

  const blog = useLocation().state.blog;
  console.log(blog.title);
  const [title, setTitle] = useState(blog.title)
  const [categories, setCategories] = useState(blog.categories)
  const [content, setContent] = useState(blog.content)
  const {blogs} = useContext(BlogsContext)
  let sno = blog.sno;
  let likes = blog.likes;

  const submit = (e) => {
    e.preventDefault()

    if (!title || !categories || !content) {
      alert("Title or Categories Or Content cannot be empty")
    }
    else {

      const myBlog = {
        sno:sno,
        title:title,
        categories:categories,
        content:content,
        likes:likes
      }
  
      console.log("edit blog ", blogs);
      blogs.map((blog=>{
        if(blog.sno===sno){
          blogs[sno]=myBlog;
          console.log("sno in app.js is  " + blog.sno);
        } 
      }))

      navigate('/')


    }
  }

  const navigate = useNavigate();
  const cancel = () => {
    navigate('/')
  }


  console.log("sno is " + sno);

  return (
    <>
      <div className='container '>
        <div className='text-center'>
          <div className='row'>
            <div className='col-md-6 offset-md-3'>
              <div className='card mt-3 '>
                <div className="card-header bg-success">
                  <h3 className='text-center py-2 '>Edit Blog</h3>
                </div>
              </div>
            </div>
          </div>
        </div>

        <div className='row' >
          <div className='col-md-6 offset-md-3'>

            <div className='card bg-light'>
              <div className='card-body'>

                <form onSubmit={submit}>

                  <div className="form-group mt-4">
                    <label htmlFor="title"><strong>Title</strong></label>
                    <input type="text" value={title} onChange={(e) => { setTitle(e.target.value) }} className="form-control mt-2" id="tile" aria-describedby="textHelp" placeholder="Enter Title" />
                  </div>

                  <div className="form-group mt-4">
                    <label htmlFor="categories"><strong>Categories</strong></label>
                    <input type="text" value={categories} onChange={(e) => {
                      setCategories(e.target.value)
                    }} className="form-control mt-2" id="categories" aria-describedby="textHelp" placeholder="Enter Title" />
                  </div>
                  <div className="form-group mt-4">
                    <label htmlFor="content"><strong>Content</strong></label>
                    <textarea value={content} onChange={(e) => {
                      setContent(e.target.value)
                    }} className="form-control mt-3" id="content" rows="4"></textarea>
                  </div>

                  <button type="submit" className="btn btn-success btn-lg m-3">Update</button>
                  <button onClick={() => cancel()} className="btn btn-secondary btn-lg m-3 ">Cancel </button>

                </form>
              </div>
            </div>

          </div>
        </div>
      </div>
    </>
  )
}
