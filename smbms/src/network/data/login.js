import {request} from '../request.js'

export function getUser(name,password) {
	return request({
		url: '/operate.do',
		params:{
			method:'checkLogin',
			userCode:name,
			userPassword:password
		}
	})
}