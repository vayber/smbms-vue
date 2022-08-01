import {request} from '../request.js'

export function getProviderList(providerCode,prodviderName) {
	return request({
		url: '/providerManagement.do',
		params:{
			method:'queryList',
			queryProCode:providerCode,
			queryProName:prodviderName			
		}
	})
}
export function getProviderCount(providerCode,prodviderName) {
	return request({
		url: '/providerManagement.do',
		params:{
			method:'getProviderCount',
			queryProCode:providerCode,
			queryProName:prodviderName
		}
	})
}

export function getProviderNameList(){
	return request({
		url: '/providerManagement.do',
		params:{
			method:'getNameList'
			
		}
	})
}

export function modifyProvider(item) {
	return request({
		url: '/providerManagement.do',
		params:{
			method:'modify',
			id:item.id,
			proCode:item.proCode,
			proName:item.proName,
			proDesc:item.proDesc,
			proContact:item.proContact,
			proPhone:item.proPhone,
			proAddress:item.proAddress,
			proFax:item.proFax,
			modifyBy:0
		}
	})
}

export function deleteProvider(id){
	return request({
		url: '/providerManagement.do',
		params:{
			method:"delete",
			proid:id
		}
	})
}

export function addProvider(item){
	return request({
		url: '/providerManagement.do',
		params:{
			method:"add",
			createdBy:0,
			proCode:item.proCode,
			proName:item.proName,
			proDesc:item.proDesc,
			proContact:item.proContact,
			proPhone:item.proPhone,
			proAddress:item.proAddress,
			proFax:item.proFax
		}
	})
}