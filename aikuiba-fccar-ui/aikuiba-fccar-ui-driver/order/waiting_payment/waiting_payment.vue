<template>
	<view>
		<view class="waiting-container">
			<image src="../static/waiting_payment/payment.png" mode="widthFix" class="payment"></image>
			<view class="title">等待顾客付款</view>
			<view class="second">{{ i }}s</view>
			<button class="btn" @tap="checkPaymentHandle">未收到付款通知</button>
		</view>

		<view class="notice-container">
			<view class="notice-title">
				<u-icon name="error-circle-fill" top="2" color="#fea802" size="36"></u-icon>
				<text>注意事项</text>
			</view>
			<view class="desc">
				<text class="num">1.</text>
				如果顾客已经成功付款，但是司机端未能收到付款成功的通知消息，可以点击“未收到顾客付款通知”的链接。系统将立即向微信支付平台查询顾客的付款结果；
			</view>
			<view class="desc">
				<text class="num">2.</text>
				如果顾客已经成功付款，并且您的微信上面也收到了分账通知消息，但是本页面并没有接收到用户付款成功的通知消息。您可以点击已经收款按钮，系统将立即确认付款结果；
			</view>
			<view class="desc">
				<text class="num">3.</text>
				如果顾客已经成功付款，并且本页面也接收到了付款成功的通知，但是您没有收到系统分账的通知。您可以耐心等待，因为本系统将会审核您在代驾中是否存在违规，并且扣除相关罚款，再给您分账。所以在顾客付款成功之后的2小时之内，您会收到本次代驾的分账收入，请您耐心等待，如有技术问题，请拨打400-264166678
			</view>
		</view>
		<u-top-tips ref="uTips"></u-top-tips>
	</view>
</template>

<script>
export default {
	data() {
		return {
			i: 60,
			orderNo: null,
			timer: null
		};
	},
	methods: {
		checkPaymentHandle(){
			let _this = this;
			_this.get(_this.url.baseUrl+"/pay/pay/queryPayStatus/"+_this.orderNo,(res)=>{
				let {success,message} = res.data;
				if(success){
					_this.$refs.uTips.show({
						title: '支付成功',
						type: 'success',
						duration: '100000'
					})
				}else{
					_this.$refs.uTips.show({
						title: message,
						type: 'error',
						duration: '100000'
					})
				}
			});
		},
		checkPayOrderStatus(){
			let _this = this;
			_this.get(_this.url.baseUrl+"/pay/pay/checkPaySuccess/"+_this.orderNo,(res)=>{
				let {success} = res.data;
				if(success){
					_this.$refs.uTips.show({
						title: '支付成功',
						type: 'success',
						duration: '100000'
					})
				}
			});
		}
		
	},
	onLoad: function(options) {
		let _this = this;
		_this.orderNo = options.orderNo;
		
		if(_this.timer != null)return;
		_this.timer = setInterval(()=>{
			_this.i = this.i - 1;
			if(_this.i % 3 == 0){
				_this.checkPayOrderStatus();
			}
			if(_this.i <= 0){
				clearInterval(_this.timer);
				_this.timer = null;
			}
		
		},1000)
	},
	onShow: function() {
		
	},
	onHide: function() {
		if(_this.timer == null)return;
		clearInterval(_this.timer);
		_this.timer = null;
	}
};
</script>

<style lang="less">
@import url('waiting_payment.less');
</style>
