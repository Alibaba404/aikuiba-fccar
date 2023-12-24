<template>
	<view class="page">
		<image src="../../static/register/top2.jpg" mode="widthFix" class="top" />
		<view class="location-container">
			<view class="left">
				<image src="../../static/register/location.png" mode="widthFix" class="location"></image>
				<text>代驾服务地点</text>
			</view>
			<view class="right">{{cityName}}</view>
		</view>
		<view class="info-container">
			<view class="title-container">
				<image src="../../static/register/title-bg.png" mode="widthFix" class="title-bg"></image>
				<text class="title">注册条件</text>
			</view>
			<view class="list">
				<view class="item">
					<text>1.</text>
					车辆合规合法；
				</view>
				<view class="item">
					<text>2.</text>
					年龄18岁以上；
				</view>
				<view class="item">
					<text>3.</text>
					无违法犯罪记录、无精神病史、无吸毒史，以及平台认为不适合使用代驾的其他历史证明；
				</view>


			</view>
		</view>
		<view class="info-container">
			<view class="title-container">
				<image src="../../static/register/title-bg.png" mode="widthFix" class="title-bg"></image>
				<text class="title">使用流程</text>
			</view>
			<view class="list">
				<view class="complex-item">
					<view class="left">01</view>
					<view class="right">
						<text class="item-title">在线注册账号</text>
						<text class="item-desc">在小程序完成注册</text>
					</view>
				</view>
				<view class="complex-item">
					<view class="left">02</view>
					<view class="right">
						<text class="item-title">个人信息填写</text>
						<text class="item-desc">按照流程提交本人身份证照片</text>
					</view>
				</view>
				<view class="complex-item">
					<view class="left">03</view>
					<view class="right">
						<text class="item-title">代驾下单</text>
						<text class="item-desc">通过APP下单预约代驾</text>
					</view>
				</view>
				<view class="complex-item">
					<view class="left">04</view>
					<view class="right">
						<text class="item-title">费用支付</text>
						<text class="item-desc">订单结束自动扣除费用</text>
					</view>
				</view>
			</view>
		</view>
		<button class="btn" @tap="wxRegister">乘客注册</button>
		<!-- <button class="btn" open-type="getPhoneNumber" @getphonenumber="getPhone">微信注册</button> -->
		<u-toast ref="uToast" />
	</view>
</template>

<script>
	var QQMapWX = require('../../lib/qqmap-wx-jssdk.min.js');
	var qqMapSDK
	export default {
		data() {
			return {
				code: null,
				cityName: "-未知-"
			};
		},
		onLoad() {
			this.initQQMapSDK()
			this.getLocation();
		},
		methods: {
			// 初始化腾讯地图SDK对象
			initQQMapSDK() {
				qqMapSDK = new QQMapWX({
					key: this.Consts.QQMAP_KEY // 腾讯小程序KEY
				});
			},
			//腾讯地图，地址解析
			reverseGeocoder(location) {
				let _this = this;
				qqMapSDK.reverseGeocoder({
					location: location,
					success: function(res) {
						let city = res.result.address_component.city;
						_this.cityName = city;
					}
				});
			},
			//获取坐标位子
			getLocation() {
				let _this = this;
				uni.getLocation({
					type: 'wgs84',
					success: function(res) {
						console.log('当前位置的经度：' + res.longitude);
						console.log('当前位置的纬度：' + res.latitude);
						let location = {
							latitude: res.latitude,
							longitude: res.longitude
						}
						_this.reverseGeocoder(location);
					},
					fail: function(error) {
						uni.showToast({
							icon: "error",
							title: "定位失败"
						})
					}
				});

			},
			//获取手机号
			getPhone(res) {
				console.log('===>', res);
			},
			//司机注册
			wxRegister() {
				let _this = this;
				// 获取WX code
				wx.login({
					success: (res) => {
						if (res.code) {
							let param = {
								code: res.code
							}
							_this.post(_this.Consts.API.CUSTOMER_REGISTER, param, (res) => {
								console.log('===>', res);
								let {
									data,
									message,
									code,
									success
								} = res.data;
								if (success) {
									uni.showToast({
										icon: "success",
										title: "注册成功"
									})
								}
							})
						}
					}
				})
			},
		}
	};
</script>

<style lang="less">
	@import url('register.less');
</style>