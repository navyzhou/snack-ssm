let types = new Vue({
	el:"#search_item",
	data: {
		types: [],
		cartCount: 0,
		carts: []
	},
	mounted: function() { // 页面渲染完成后要执行的方法
		axios.post("type/finds").then(result => { // vue的axios里面默认发送请求时的参数格式为json格式，这样的后后台没法识别，需要通过qs.stringify()方法转成op=finds
			if (result.status == 200 && result.data.code == 200) { // 说明请求成功
				this.types  = result.data.data;
			}
		}) 
		
		axios.get("cart/info").then(result => {
			if (result.status == 200 && result.data.code == 200) { 
				this.cartCount = result.data.data.length;
				this.carts = result.data.data;
			}
		})
	}
})