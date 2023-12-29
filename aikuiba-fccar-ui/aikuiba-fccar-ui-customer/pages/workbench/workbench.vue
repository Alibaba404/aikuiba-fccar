<template>
	<view>
		<view v-if="inPorcessOrderId != null" class="order-process" @click="toOrder">您有进行中的订单,点击查看</view>

		<map id="map" :longitude="longitude" :latitude="latitude" :style="contentStyle" scale="15"
			:enable-traffic="false" :show-location="true" :enable-poi="true" class="map">
			<cover-image class="location" src="../../static/workbench/location.png"
				@tap="toLocationHandle()"></cover-image>
		</map>
		<view class="panel">
			<view class="from" @tap="chooseLocationHandle('from')">
				<text>{{ from.address }}</text>
			</view>
			<view class="dashed-line"></view>
			<view class="to" @tap="chooseLocationHandle('to')">
				<text>{{ to.address }}</text>
			</view>
			<button class="btn" @tap="toCreateOrderHandle">呼叫代驾</button>
		</view>
	</view>
</template>

<script>
	import GtPush from '../../lib/gtpush-min.js'
	//地图点选插件
	const chooseLocation = requirePlugin('chooseLocation');
	let QQMapWX = require('../../lib/qqmap-wx-jssdk.min.js');
	let qqmapsdk;

	export default {
		data() {
			return {
				//开始位置
				from: {
					address: '输入你的目的地',
					longitude: 0,
					latitude: 0
				},
				//结束位置
				to: {
					address: '输入你的目的地',
					longitude: 0,
					latitude: 0
				},
				//当前位置经纬度
				longitude: 104.06163,
				latitude: 30.59415,
				//窗口样式
				contentStyle: '',
				windowHeight: 0,
				//地图对象
				map: null,
				//标记:开始位置点选或者结束位置点选
				chooseFromOrToflag: null,
				inPorcessOrderId: null
			};
		},
		methods: {
			//初始化窗口样式
			initStyle() {
				let _this = this;
				//处理窗口样式
				let windowHeight = uni.getSystemInfoSync().windowHeight;
				_this.windowHeight = windowHeight;
				_this.contentStyle = `height:${_this.windowHeight}px;`;
			},
		},
		onShow: function() {
			let _this = this;
		},
		onHide: function() {},
		onLoad: function() {
			let _this = this;
			_this.initStyle();
		},
		onUnload: function() {

		}
	};
</script>

<style lang="less">
	@import url('workbench.less');
</style>