import React, { useContext } from 'react'
import { Link } from 'react-router-dom'
import { BlogsContext } from '../Contexts/BlogsContext'


export const Blogs = (props) => {

  const {blogs} = useContext(BlogsContext)

  return (

    <div className='container'>

      <div className='row '>
        <div className='col-md-6 offset-md-3'>
          <div className='card my-4'>
            <div className="card-header">
              <h3 className='text-center py-2'>Blogs List</h3>
            </div>
          </div>


          {blogs.length === 0 ? "No Blogs Available" :
            blogs.map((blogItem) => {
              console.log(blogItem);
              return <Link to={'/showBlog'} state={{ blogItem: blogItem }} key={blogItem.sno} style={{ textDecoration: 'none' }} >
                <div className=" card my-1" >
                  <ul className="list-group list-group-flush ">
                    <li className="list-group-item"><h5>{blogItem.title}</h5></li>
                  </ul>
                </div>
              </Link>
            })}
            
        </div>
      </div>
    </div>

  )
}
