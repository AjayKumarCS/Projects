import React, { useContext, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { BlogsContext } from '../Contexts/BlogsContext';




export const AddBlog = (props) => {

    const [title, setTitle] = useState("")
    const [categories, setCategories] = useState("")
    const [content, setContent] = useState("")

    const {blogs, setBlogs} = useContext(BlogsContext);

    const navigate = useNavigate();
    const goToIndex = () => {
        navigate("/");
    }

    const submit = (e) => {
        e.preventDefault()

        if (!title || !categories || !content) {
            alert("Title or Categories Or Content cannot be empty")
        }
        else {

            let sno;
            if (blogs.length === 0) {
                sno = 0;
            } else {
                sno = blogs[blogs.length - 1].sno + 1;
            }
            const myBlog = {

                sno: sno,
                title: title,
                categories: categories,
                content: content,
                likes: 0
            }
            console.log(sno);
            setBlogs([...blogs, myBlog]);
            console.log(myBlog);

            setTitle("");
            setCategories("");
            setContent("");
        }

    }
    return (

        <div className='container '>

            <div className='text-center'>
                <div className='row '>
                    <div className='col-md-6 offset-md-3'>
                        <div className='card mt-3 '>
                            <div className="card-header">
                                <h3 className='text-center py-2'>Add Blog</h3>
                            </div>
                        </div>

                    </div>
                </div>
            </div>

            <div className='row' >
                <div className='col-md-6 offset-md-3'>

                    <div className='card'>
                        <div className='card-body'>

                            <form onSubmit={submit}>
                                <div className="form-group mt-4">
                                    <label htmlFor="title">Title</label>
                                    <input type="text" value={title} onChange={(e) => {
                                        setTitle(e.target.value)
                                    }} className="form-control mt-2" id="tile" aria-describedby="textHelp" placeholder="Enter Title" />
                                </div>
                                <div className="form-group mt-4">
                                    <label htmlFor="categories">Categories</label>
                                    <input type="text" value={categories} onChange={(e) => {
                                        setCategories(e.target.value)
                                    }} className="form-control mt-2" id="categories" aria-describedby="textHelp" placeholder="Enter Title" />

                                </div>


                                <div className="form-group mt-4">
                                    <label htmlFor="content">Content</label>
                                    <textarea value={content} onChange={(e) => {
                                        setContent(e.target.value)
                                    }} className="form-control mt-3" id="content" rows="4"></textarea>
                                </div>

                                <button type="submit" className="btn btn-success btn-lg m-3">Submit</button>

                                <button onClick={() => goToIndex()} className="btn btn-secondary btn-lg m-3 ">Cancel </button>

                            </form>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    )
}
