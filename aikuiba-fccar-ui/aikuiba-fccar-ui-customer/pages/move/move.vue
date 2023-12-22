<template>
	<view>
		<map
			id="map"
			scale="18"
			:longitude="from.longitude"
			:latitude="from.latitude"
			:enable-poi="true"
			class="map"
			:style="mapStyle"
			:polyline="polyline"
			:markers="markers"
			@longpress="showHandle"
		>
		<image class="location" src="../static/workbench/location.png" @tap="returnLocationHandle()" />
		</map>
		<view class="panel" v-show="infoStatus">
			<view class="info">
				<view class="label">剩余</view>
				<view class="value">{{ distance }}公里</view>
				<view class="label">，预计</view>
				<view class="value">{{ duration }}分钟</view>
				<view class="more" @tap="toOrderDetail">订单详情</view>
			</view>
			<view class="opt">
				<button class="cancel-btn" :style="cancelStyle">取消订单</button>
				<button class="confirm-btn" v-if="status == 2||status == 3" @tap="driverArriviedHandle">司机到达</button>
			</view>
		</view>
	</view>
</template>

<script>
let QQMapWX = require('../../lib/qqmap-wx-jssdk.min.js');
	
import GtPush from '../../lib/gtpush-min.js'

let qqmapsdk;
export default {
	data() {
		return {
			orderNo: null,
			status: null,
			mode: null,
			cancelStyle: '',
			map: null,
			mapStyle: '',
			from:{
				latitude:0,
				longitude:0,
			},
			to:{
				latitude:0,
				longitude:0,
			},
			distance: 0,
			duration: 0,
			polyline: [],
			markers: [],
			driverLocationTimer: null,
			infoStatus: true,
			pullBillTimer:null
		};
	},
	methods: {
		toOrderDetail(){
			let _this = this;
			uni.navigateTo({
				url:"../order/order?orderNo="+_this.orderNo
			})
		},
		initStyle(){
			let _this = this;
			//设置地图控件的高度适配屏幕高度
			let windowHeight = uni.getSystemInfoSync().windowHeight;
			_this.windowHeight = windowHeight;
			_this.mapStyle = `height:${_this.windowHeight}px;`;
		},
	},
	onLoad: function(options) {
		let _this = this;
		_this.orderNo = options.orderNo;
		_this.initStyle();
		
	},
	onShow: function() {
		let _this = this;
	},
	onHide: function() {
		let _this = this;
	}
};
</script>

<style lang="less">
@import url('move.less');
</style>
