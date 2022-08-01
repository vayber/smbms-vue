import {request} from '../request.js'

export function getRoleNameList() {
	return request({
		url: '/roleManagement.do',
		params:{
			method:'queryList'
			
		}
	})
}