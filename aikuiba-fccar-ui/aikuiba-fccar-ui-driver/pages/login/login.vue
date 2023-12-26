<template>
	<view>
		<image src="../../static/login/top3.png" mode="widthFix" class="top"></image>
		<image src="../../static/login/logo.jpg" mode="widthFix" class="logo"></image>
		<view class="desc">
			<text class="name">专业代驾 服务第一</text>
		</view>
		<button class="btn" @tap="wxLogin()">司机登陆</button>
		<view class="register-container">
			没有账号?
			<text class="link" @tap="toRegisterPage()">立即注册</text>
		</view>
		<text class="remark">小程序仅限于飞驰专车代驾使用,登录前请先进行微信注册,注册前请消息阅读注册需求和使用流程</text>
		<u-toast ref="uToast" />
	</view>
</template>

<script>
	export default {
		data() {
			return {};
		},
		methods: {
			toRegisterPage() {
				//跳转注册页面
				uni.navigateTo({
					url: "../register/register"
				})
			},
			wxLogin() {
				let _this = this;
				uni.showLoading({
					title: "登录中..."
				});
				wx.login({
					success(res) {
						//拿到微信授权码
						let code = res.code;
						if (code) {
							console.log('CODE:', code);
							let loginParam = {
								wxCode: code,
								loginType: _this.Consts.LOGIN.TYPE_DRIVER
							}
							_this.post('/uaa/app/login/wechat', loginParam, (res) => {
								console.log('resp', res);
								let {
									success,
									data,
									message
								} = res.data;
								if (success) {
									console.log('data', data);
									// 保存相关信息到本地存储
									uni.setStorageSync('satoken', data.satoken);
									uni.setStorageSync('nickname', data.nickName);
									uni.setStorageSync('avatar', data.avatar);
									// 页面跳转
									uni.showToast({
										icon: "success",
										title: "登录成功"
									})
									//跳转工作台页面
									setTimeout(() => {
										// 切换到tab标签
										uni.switchTab({
											url: "/pages/workbench/workbench"
										})
									}, 2000)
								}
							})
						}
					}
				})
			},
		},
		onLoad: function() {}
	};
</script>

<style lang="less">
	@import url('login.less');
</style>