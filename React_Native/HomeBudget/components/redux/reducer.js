import { SAVE_ELEMENT } from "./constants"
const initialState=[]

export default reducer = (state=initialState,action) => {
    switch(action.type){
    case SAVE_ELEMENT:
        return [
            ...state,
            action.data
        ]
    
    default:
        return state
  }
}
