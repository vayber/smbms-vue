import {request} from '../request.js'

export function getBillList(goodName,provider,isPayment) {
	return  request({
		url: '/billManagement.do',
		params:{
			method:'queryList',
			queryProductName:goodName,
			queryProvider:provider,
			queryIsPayment:isPayment
		}
	})
}
export function getBillCount(goodName,provider,isPayment) {
	return request({
		url: '/billManagement.do',
		params:{
			method:'getBillCount',
			queryProductName:goodName,
			queryProvider:provider,
			queryIsPayment:isPayment
		}
	})
}

export function modifyBill(item) {
	return request({
		url: '/billManagement.do',
		params:{
			method:'modify',
			id:item.id,
			billCode:item.billCode,
			productName:item.productName,
			productDesc:item.productDesc,
			productUnit:item.productUnit,
			productCount:item.productCount,
			totalPrice:item.totalPrice,
			providerId:item.providerId,
			isPayment:item.isPayment,
			modifyBy:0
		}
	})
}

export function deleteBill(id){
	return request({
		url: '/billManagement.do',
		params:{
			method:"delete",
			billid:id
		}
	})
}

export function addBill(item){
	return request({
		url: '/billManagement.do',
		params:{
			method:"add",
			createdBy:0,
			billCode:item.billCode,
			productName:item.productName,
			productDesc:item.productDesc,
			productUnit:item.productUnit,
			productCount:item.productCount,
			totalPrice:item.totalPrice,
			providerId:item.providerId,
			isPayment:item.isPayment
		}
	})
}