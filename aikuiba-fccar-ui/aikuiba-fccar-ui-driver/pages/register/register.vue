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
				<text class="title">入驻条件</text>
			</view>
			<view class="list">
				<view class="item">
					<text>1.</text>
					三年安全驾驶经验；
				</view>
				<view class="item">
					<text>2.</text>
					年龄23~55周岁；
				</view>
				<view class="item">
					<text>3.</text>
					无违法犯罪记录、无精神病史、无吸毒史，以及平台认为不适合代驾的其他历史证明；
				</view>
				<view class="item">
					<text>4.</text>
					有熟练驾车经验；
				</view>
				<view class="item">
					<text>5.</text>
					身体健康，无肢体残疾和大面积纹身；
				</view>
				<view class="item">
					<text>6.</text>
					需要提供身份证、驾驶证、直系亲属联系方式，并保存前述材料的真实合法性；
				</view>
			</view>
		</view>
		<view class="info-container">
			<view class="title-container">
				<image src="../../static/register/title-bg.png" mode="widthFix" class="title-bg"></image>
				<text class="title">入驻流程</text>
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
						<text class="item-title">司机信息填写</text>
						<text class="item-desc">按照流程提交本人身份证、驾驶证</text>
					</view>
				</view>
				<view class="complex-item">
					<view class="left">03</view>
					<view class="right">
						<text class="item-title">证件资料审核</text>
						<text class="item-desc">对提交证件进行审核</text>
					</view>
				</view>
				<view class="complex-item">
					<view class="left">04</view>
					<view class="right">
						<text class="item-title">签署合同</text>
						<text class="item-desc">收到邮寄的合同后必须本人签署并寄回</text>
					</view>
				</view>
			</view>
		</view>
		<button class="btn" @tap="wxRegister">司机注册</button>
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
				cityName: "未知"
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
							console.log('code', res.code);
							let param = {
								code: res.code
							}
							_this.post(_this.Consts.API.DRIVER_REGISTER, param, (res) => {
								console.log('===>', res);
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