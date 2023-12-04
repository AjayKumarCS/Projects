import { SAVE_ELEMENT } from './constants'

export function saveElement(item){
    return {
        type: SAVE_ELEMENT,
        data:item
    }
}